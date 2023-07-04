/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.lock.remote.rpc.handler;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.remote.request.AcquireAbstractLockRequest;
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
public class LockRequestHandler extends RequestHandler<AcquireAbstractLockRequest, AcquireLockResponse> {
    
    @Override
    @Secured(action = ActionTypes.READ)
    public AcquireLockResponse handle(AcquireAbstractLockRequest request, RequestMeta meta) throws NacosException {
        System.out.println(request.getLockInfo());
        return new AcquireLockResponse(Boolean.TRUE);
    }
}
