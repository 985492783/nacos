package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.constant.PropertyConstants;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.client.lock.core.LockServiceProxy;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;
import com.alibaba.nacos.client.lock.remote.grpc.LockGrpcClient;
import com.alibaba.nacos.client.naming.core.ServerListManager;
import com.alibaba.nacos.client.naming.remote.http.NamingHttpClientManager;
import com.alibaba.nacos.client.security.SecurityProxy;

import java.util.Properties;

/**
 * @author 985492783@qq.com
 * @description NacosLockService
 * @date 2023/6/28 17:16
 */
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
