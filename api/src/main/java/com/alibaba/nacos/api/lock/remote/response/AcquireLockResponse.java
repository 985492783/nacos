package com.alibaba.nacos.api.lock.remote.response;

import com.alibaba.nacos.api.remote.response.Response;

/**
 * @author qiyue.zhang@aloudata.com
 * @description AcquireLockResponse
 * @date 2023/6/29 13:51
 */
public class AcquireLockResponse extends Response {
    
    private Boolean result;
    
    public AcquireLockResponse() {
    
    }
    
    public AcquireLockResponse(Boolean result) {
        this.result = result;
    }
    
    public Boolean getResult() {
        return result;
    }
    
    public void setResult(Boolean result) {
        this.result = result;
    }
}
