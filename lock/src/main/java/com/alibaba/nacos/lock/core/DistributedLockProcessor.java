package com.alibaba.nacos.lock.core;

import com.alibaba.nacos.consistency.cp.RequestProcessor4CP;
import com.alibaba.nacos.consistency.entity.ReadRequest;
import com.alibaba.nacos.consistency.entity.Response;
import com.alibaba.nacos.consistency.entity.WriteRequest;

/**
 * @author 985492783@qq.com
 * @date 2023/7/6 1:37
 */
public class DistributedLockProcessor extends RequestProcessor4CP {
    
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
