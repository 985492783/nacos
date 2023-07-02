package com.alibaba.nacos.client.lock.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.api.lock.model.LockType;
import com.alibaba.nacos.client.lock.AbstractLockService;
import com.alibaba.nacos.client.lock.NacosLockService;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiyue.zhang@aloudata.com
 * @description LockServiceProxy
 * @date 2023/6/29 10:59
 */
public class LockServiceProxy extends AbstractLockService {
    private final Map<LockType, LockService> lockServiceMap;
    public LockServiceProxy(AbstractLockClient lockClient) throws NacosException {
        super(lockClient);
        this.lockServiceMap = new HashMap<>();
        this.lockServiceMap.put(LockType.PRIMARY, new SimpleLockService(lockClient));
        this.lockServiceMap.put(LockType.SPIN, new SpinLockService(lockClient));
    }
    
    @Override
    public Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException {
        return getLockProxyService(lockInfo.getLockType()).tryLock(lockInfo, waitTime);
    }
    
    private LockService getLockProxyService(LockType lockType) throws NacosException {
        if (lockServiceMap.containsKey(lockType)) {
            return lockServiceMap.get(lockType);
        }
        throw new NacosException();
    }
    
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) {
    
    }
}
