package com.alibaba.nacos.api.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;

/**
 * @author 985492783@qq.com
 * @date 2023/6/28 2:47
 */
public interface LockService {

    
    Boolean lock(LockInfo lockInfo) throws NacosException;
    
    Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException;
    
    void addListener(LockInstance instance, EventListen eventListen) throws NacosException;
}
