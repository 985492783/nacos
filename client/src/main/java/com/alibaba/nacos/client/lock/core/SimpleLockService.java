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

package com.alibaba.nacos.client.lock.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.client.lock.AbstractLockService;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

/**
 * simple lock service.
 * @author 985492783@qq.com
 * @description SimpleLockService
 * @date 2023/6/29 10:59
 */
public class SimpleLockService extends AbstractLockService {
    
    public SimpleLockService(AbstractLockClient lockClient) throws NacosException {
        super(lockClient);
    }
    
    @Override
    public Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException {
        return lockInner(lockInfo);
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) throws NacosException {
    
    }
}
