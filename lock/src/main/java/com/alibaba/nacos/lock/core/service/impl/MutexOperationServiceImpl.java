/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.lock.core.service.impl;

import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.consistency.LockOperation;
import com.alibaba.nacos.consistency.SerializeFactory;
import com.alibaba.nacos.consistency.Serializer;
import com.alibaba.nacos.consistency.cp.CPProtocol;
import com.alibaba.nacos.consistency.cp.RequestProcessor4CP;
import com.alibaba.nacos.consistency.entity.ReadRequest;
import com.alibaba.nacos.consistency.entity.Response;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.core.distributed.ProtocolManager;
import com.alibaba.nacos.lock.constants.Constants;
import com.alibaba.nacos.lock.core.LockManager;
import com.alibaba.nacos.lock.core.connect.LockConnectionManager;
import com.alibaba.nacos.lock.core.reentrant.AbstractAtomicLock;
import com.alibaba.nacos.lock.core.service.LockOperationService;
import com.alibaba.nacos.lock.model.Service;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;

/**
 * mutex operation.
 * @author 985492783@qq.com
 * @description MutexOperationServiceImpl
 * @date 2023/7/7 15:22
 */
@Component
public class MutexOperationServiceImpl extends RequestProcessor4CP implements LockOperationService {

    private final LockManager lockManager;

    private LockConnectionManager lockConnectionManager;

    private final Serializer serializer = SerializeFactory.getDefault();

    private final CPProtocol protocol;


    public MutexOperationServiceImpl(LockManager lockManager, LockConnectionManager lockConnectionManager) {
        this.lockManager = lockManager;
        this.lockConnectionManager = lockConnectionManager;
        this.protocol = ApplicationUtils.getBean(ProtocolManager.class).getCpProtocol();
        this.protocol.addRequestProcessors(Collections.singletonList(this));
    }

    @Override
    public Boolean lock(LockInfo lockInfo, String connectionId) {
        MutexLockRequest request = new MutexLockRequest();
        request.setLockInfo(lockInfo);
        request.setConnectionId(connectionId);
        WriteRequest writeRequest = WriteRequest.newBuilder().setGroup(group())
                .setData(ByteString.copyFrom(serializer.serialize(request))).setOperation(LockOperation.ACQUIRE.name())
                .build();
        try {
            Response response = protocol.write(writeRequest);
            return serializer.deserialize(response.getData().toByteArray());
        } catch (Exception e) {
            return false;
        }
    }
    
    public void disConnect(String connectionId) {
        DisconnectedLockRequest request = new DisconnectedLockRequest();
        request.setConnectionId(connectionId);
    }
    
    public void connect(String connectionId) {
    
    }
    
    private Service buildLockService(LockInstance lockInstance) {
        Service service = new Service(lockInstance.getIp(), lockInstance.getPort());
        Service singleton = lockManager.getSingletonService(service);
        return singleton;
    }

    @Override
    public Response onRequest(ReadRequest request) {
        return null;
    }

    @Override
    public Response onApply(WriteRequest request) {
        try {
            final MutexLockRequest mutexLockRequest = serializer.deserialize(request.getData().toByteArray());
            LockOperation lockOperation = LockOperation.valueOf(request.getOperation());
            Boolean data = null;
            if (lockOperation == LockOperation.ACQUIRE) {
                data = acquireLock(mutexLockRequest.getLockInfo(), mutexLockRequest.getConnectionId());
            }
            ByteString bytes = ByteString.copyFrom(serializer.serialize(data));
            return Response.newBuilder().setSuccess(true).setData(bytes).build();
        } catch (Exception e) {
            return Response.newBuilder().setSuccess(false).build();
        }
    }

    private Boolean acquireLock(LockInfo lockInfo, String connectionId) {
        if (!lockConnectionManager.isAlive(connectionId)) {
            return false;
        }
        String key = lockInfo.getKey();
        AbstractAtomicLock mutexLock = lockManager.getMutexLock(key);
        Service service = buildLockService(lockInfo.getLockInstance());
        Boolean lock = mutexLock.tryLock(service);
        if (lock) {
            lockManager.acquireLock(connectionId, service);
        }
        return lock;
    }

    @Override
    public String group() {
        return Constants.LOCK_ACQUIRE_SERVICE_GROUP_V2;
    }

    public static class MutexLockRequest implements Serializable {

        private static final long serialVersionUID = -925543547156890549L;

        private LockInfo lockInfo;

        private String connectionId;

        public String getConnectionId() {
            return connectionId;
        }

        public void setConnectionId(String connectionId) {
            this.connectionId = connectionId;
        }

        public LockInfo getLockInfo() {
            return lockInfo;
        }

        public void setLockInfo(LockInfo lockInfo) {
            this.lockInfo = lockInfo;
        }
    }
    
    public static class DisconnectedLockRequest implements Serializable {
        
        private static final long serialVersionUID = -925333547156890549L;
        
        private String connectionId;
        
        public String getConnectionId() {
            return connectionId;
        }
        
        public void setConnectionId(String connectionId) {
            this.connectionId = connectionId;
        }
    }
    
    public static class ConnectedLockRequest implements Serializable {
        
        private static final long serialVersionUID = -925333547156890549L;
        
        private String connectionId;
        
        public String getConnectionId() {
            return connectionId;
        }
        
        public void setConnectionId(String connectionId) {
            this.connectionId = connectionId;
        }
    }
}
