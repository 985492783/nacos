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

package com.alibaba.nacos.lock.core.connect;

import com.alibaba.nacos.consistency.LockOperation;
import com.alibaba.nacos.consistency.SerializeFactory;
import com.alibaba.nacos.consistency.Serializer;
import com.alibaba.nacos.consistency.cp.CPProtocol;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.core.distributed.ProtocolManager;
import com.alibaba.nacos.core.remote.ClientConnectionEventListener;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.lock.core.service.impl.MutexOperationServiceImpl;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


/**
 * lock connection manager.
 * @author 985492783@qq.com
 * @description LockConnectionManager
 * @date 2023/7/10 10:47
 */
@Component("lockConnectionManager")
public class LockConnectionManager extends ClientConnectionEventListener {
    private final Logger log = LoggerFactory.getLogger(LockConnectionManager.class);
    private final ConcurrentHashMap<String, Connection> connectionMap;
    
    private final CPProtocol protocol;
    
    private final Serializer serializer = SerializeFactory.getDefault();

    public LockConnectionManager() {
        connectionMap = new ConcurrentHashMap<>();
        this.protocol = ApplicationUtils.getBean(ProtocolManager.class).getCpProtocol();
    }

    @Override
    public void clientConnected(Connection connect) {
        MutexOperationServiceImpl.ConnectedLockRequest request =
                new MutexOperationServiceImpl.ConnectedLockRequest();
        request.setConnectionId(connect.getMetaInfo().getConnectionId());
        WriteRequest writeRequest = WriteRequest.newBuilder()
                .setData(ByteString.copyFrom(serializer.serialize(request)))
                .setOperation(LockOperation.CONNECTED.name()).build();
        try {
            protocol.write(writeRequest);
        } catch (Exception e) {
            log.error("");
        }
    }

    @Override
    public void clientDisConnected(Connection connect) {
    
    }

    public boolean isAlive(String connectionId) {
        return connectionMap.containsKey(connectionId);
    }
}
