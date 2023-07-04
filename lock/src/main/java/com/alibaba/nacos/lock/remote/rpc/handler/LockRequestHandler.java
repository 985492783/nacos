package com.alibaba.nacos.lock.remote.rpc.handler;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.remote.request.AcquireLockRequest;
import com.alibaba.nacos.api.lock.remote.response.AcquireLockResponse;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.core.remote.RequestHandler;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import org.springframework.stereotype.Component;

/**
 * lock grpc handler.
 * @author 985492783@qq.com
 * @description LockRequestHandler
 * @date 2023/6/29 14:00
 */
@Component
public class LockRequestHandler extends RequestHandler<AcquireLockRequest, AcquireLockResponse> {
    
    @Override
    @Secured(action = ActionTypes.READ)
    public AcquireLockResponse handle(AcquireLockRequest request, RequestMeta meta) throws NacosException {
        System.out.println(request.getLockInfo());
        return new AcquireLockResponse(Boolean.TRUE);
    }
}
