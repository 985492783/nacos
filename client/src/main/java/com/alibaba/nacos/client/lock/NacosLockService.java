package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;
import com.alibaba.nacos.client.lock.remote.grpc.LockGrpcClient;

import java.util.Properties;

/**
 * @author qiyue.zhang@aloudata.com
 * @description NacosLockService
 * @date 2023/6/28 17:16
 */
public class NacosLockService implements LockService {
    private AbstractLockClient abstractLockClient;
    
    public NacosLockService(Properties properties) {
        final NacosClientProperties nacosClientProperties = NacosClientProperties.PROTOTYPE.derive(properties);
        
        this.abstractLockClient = new LockGrpcClient(nacosClientProperties);
        
    }
    
    @Override
    public Boolean lock(LockInfo lockInfo) {
        return null;
    }
    
    @Override
    public Boolean waitLock(LockInfo lockInfo, long waitTime) {
        return null;
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) {
    
    }
}
