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

package com.alibaba.nacos.api.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;

/**
 * lock operator service.
 * @author 985492783@qq.com
 * @date 2023/6/28 2:47
 */
public interface LockService {
    
    /**
     * try to get lock with default waitTime.
     * @param lockInfo contains key and instance.
     * @return true if success.
     * @throws NacosException nacos Exception.
     */
    Boolean lock(LockInfo lockInfo) throws NacosException;
    
    /**
     * try lock until waitTime.
     * @param lockInfo contains key and instance.
     * @param waitTime waitTime.
     * @return true if success.
     * @throws NacosException nacos Exception.
     */
    Boolean tryLock(LockInfo lockInfo, long waitTime) throws NacosException;
    
    /**
     * add listener.
     * @param instance instance.
     * @param eventListen event listen.
     * @throws NacosException nacos Exception.
     */
    void addListener(LockInstance instance, EventListen eventListen) throws NacosException;
}
