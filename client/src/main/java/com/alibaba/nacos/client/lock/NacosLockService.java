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

package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.constant.PropertyConstants;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.client.lock.core.LockServiceProxy;
import com.alibaba.nacos.client.lock.remote.grpc.LockGrpcClient;
import com.alibaba.nacos.client.naming.core.ServerListManager;
import com.alibaba.nacos.client.naming.remote.http.NamingHttpClientManager;
import com.alibaba.nacos.client.security.SecurityProxy;

import java.util.Properties;

/**
 * client nacos lock serviceImpl.
 * @author 985492783@qq.com
 * @description NacosLockService
 * @date 2023/6/28 17:16
 */
@SuppressWarnings("PMD.ServiceOrDaoClassShouldEndWithImplRule")
public class NacosLockService implements LockService {
    
    private final LockService lockService;
    
    private long defaultWaitTime;
    
    public NacosLockService(Properties properties) throws NacosException {
        final NacosClientProperties nacosClientProperties = NacosClientProperties.PROTOTYPE.derive(properties);
        this.defaultWaitTime = nacosClientProperties.getLong(PropertyConstants.LOCK_DEFAULT_WAIT_TIME, PropertyConstants.LOCK_DEFAULT_WAIT_SECOND);
        ServerListManager serverListManager = new ServerListManager(nacosClientProperties);
        SecurityProxy securityProxy = new SecurityProxy(serverListManager.getServerList(),
                NamingHttpClientManager.getInstance().getNacosRestTemplate());
        LockGrpcClient lockGrpcClient = new LockGrpcClient(nacosClientProperties, serverListManager,
                securityProxy);
        this.lockService = new LockServiceProxy(lockGrpcClient);
    }
    
    @Override
    public Boolean lock(LockInfo lockInfo) throws NacosException {
        return this.tryLock(lockInfo, defaultWaitTime);
    }
    
    @Override
    public Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException {
        return lockService.tryLock(lockInfo, waitTime);
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) {
    
    }
}
