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

package com.alibaba.nacos.lock.core.reentrant;

import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.SmartSubscriber;
import com.alibaba.nacos.lock.core.LockManager;
import com.alibaba.nacos.lock.core.event.LockEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * client manager.
 * @author 985492783@qq.com
 * @description ClientReentrantManager
 * @date 2023/7/10 10:42
 */
@Component
public class ClientReentrantManager extends SmartSubscriber {

    private LockManager lockManager;

    public ClientReentrantManager(LockManager lockManager) {
        NotifyCenter.registerSubscriber(this);
        this.lockManager = lockManager;
    }

    @Override
    public List<Class<? extends Event>> subscribeTypes() {
        List<Class<? extends Event>> result = new LinkedList<>();
        result.add(LockEvent.DisConnectLockEvent.class);
        result.add(LockEvent.AcquireLockEvent.class);
        return result;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof LockEvent.DisConnectLockEvent) {
            handlerDisConnectLock((LockEvent.DisConnectLockEvent) event);
        } else if (event instanceof LockEvent.AcquireLockEvent) {
            handlerAcquireLock((LockEvent.AcquireLockEvent) event);
        }
    }

    private void handlerDisConnectLock(LockEvent.DisConnectLockEvent event) {

    }

    private void handlerAcquireLock(LockEvent.AcquireLockEvent event) {

    }
}
