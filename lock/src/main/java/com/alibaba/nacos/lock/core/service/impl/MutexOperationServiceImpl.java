package com.alibaba.nacos.lock.core.service.impl;

import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.consistency.cp.RequestProcessor4CP;
import com.alibaba.nacos.consistency.entity.ReadRequest;
import com.alibaba.nacos.consistency.entity.Response;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.lock.core.service.LockOperationService;

/**
 * @author qiyue.zhang@aloudata.com
 * @description MutexOperationServiceImpl
 * @date 2023/7/7 15:22
 */
public class MutexOperationServiceImpl extends RequestProcessor4CP implements LockOperationService {
    
    @Override
    public Boolean lock(LockInfo lockInfo) {
        return null;
    }
    
    @Override
    public Response onRequest(ReadRequest request) {
        return null;
    }
    
    @Override
    public Response onApply(WriteRequest log) {
        return null;
    }
    
    @Override
    public String group() {
        return null;
    }
}
