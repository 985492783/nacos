package com.alibaba.nacos.api.lock.remote.request;

import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.remote.LockRequest;

/**
 * @author qiyue.zhang@aloudata.com
 * @description AcquireLockRequest
 * @date 2023/6/29 12:01
 */
public class AcquireLockRequest extends LockRequest {

    private LockInfo lockInfo;
    
    public LockInfo getLockInfo() {
        return lockInfo;
    }
    
    public void setLockInfo(LockInfo lockInfo) {
        this.lockInfo = lockInfo;
    }
}
