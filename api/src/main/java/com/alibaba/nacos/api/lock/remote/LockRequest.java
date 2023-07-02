package com.alibaba.nacos.api.lock.remote;

import com.alibaba.nacos.api.remote.request.Request;

import static com.alibaba.nacos.api.common.Constants.Lock.LOCK_MODULE;
/**
 * @author qiyue.zhang@aloudata.com
 * @description LockRequest
 * @date 2023/6/29 12:00
 */
public abstract class LockRequest extends Request {
    
    @Override
    public String getModule() {
        return LOCK_MODULE;
    }
}
