package com.alibaba.nacos.client.lock.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.lock.AbstractLockService;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

/**
 * spin lock service.
 * @author 985492783@qq.com
 * @description SpinLockService
 * @date 2023/6/29 10:58
 */
public class SpinLockService extends AbstractLockService {
    
    public SpinLockService(AbstractLockClient lockClient) throws NacosException {
        super(lockClient);
    }
    
    @Override
    public Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException {
        return null;
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) throws NacosException {
    
    }
}
