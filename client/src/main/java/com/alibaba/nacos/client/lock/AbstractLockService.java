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

package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;

/**
 * AbstractLockService.
 * @author 985492783@qq.com
 * @description AbstractLockService
 * @date 2023/6/29 10:38
 */
public abstract class AbstractLockService implements LockService {
    private AbstractLockClient lockClient;
    
    public AbstractLockService(AbstractLockClient lockClient) throws NacosException {
        this.lockClient = lockClient;
    }
    
    @Override
    public final Boolean lock(LockInfo lockInfo) throws NacosException {
        throw new NacosException();
    }
    
    protected Boolean lockInner(LockInfo info) throws NacosException {
        return lockClient.lock(info);
    }
    
}
