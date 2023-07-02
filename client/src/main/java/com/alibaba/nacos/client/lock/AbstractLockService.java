package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

/**
 * @author qiyue.zhang@aloudata.com
 * @description AbstractLockService
 * @date 2023/6/29 10:38
 */
public abstract class AbstractLockService implements LockService {
    private AbstractLockClient lockClient;
    
    public AbstractLockService(AbstractLockClient lockClient) throws NacosException {
        this.lockClient = lockClient;
    }
    
    @Override
    public final Boolean lock(LockInfo lockInfo) throws NacosException {
        throw new NacosException();
    }
    
    protected Boolean lockInner(LockInfo info) throws NacosException {
        return lockClient.lock(info);
    }
    
    
}
