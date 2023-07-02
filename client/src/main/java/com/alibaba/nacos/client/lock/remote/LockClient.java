package com.alibaba.nacos.client.lock.remote;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.common.lifecycle.Closeable;

/**
 * @author zhanghong
 * @description LockClient
 * @date 2023/6/28 17:19
 */
public interface LockClient extends Closeable {
    
    Boolean lock(LockInfo lockInfo) throws NacosException;
    
    void addListener(LockInstance instance, EventListen eventListen);
}
