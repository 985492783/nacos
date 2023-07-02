package com.alibaba.nacos.client.lock.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.lock.AbstractLockService;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

/**
 * @author qiyue.zhang@aloudata.com
 * @description SimpleLockService
 * @date 2023/6/29 10:59
 */
public class SimpleLockService extends AbstractLockService {
    
    public SimpleLockService(AbstractLockClient lockClient) throws NacosException {
        super(lockClient);
    }
    
    @Override
    public Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException {
        return lockInner(lockInfo);
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) throws NacosException {
    
    }
}
