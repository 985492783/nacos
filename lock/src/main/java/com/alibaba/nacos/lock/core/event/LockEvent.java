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

package com.alibaba.nacos.lock.core.event;

import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.lock.core.client.LockClient;

/**
 * lock event.
 * @author 985492783@qq.com
 * @date 2023/6/28 2:36
 */
public class LockEvent extends Event {
    private static final long serialVersionUID = -3411818115593181708L;

    private LockClient client;

    private LockEvent(LockClient client) {
        this.client = client;
    }

    public LockClient getClient() {
        return client;
    }

    public void setClient(LockClient client) {
        this.client = client;
    }

    public static class AcquireLockEvent extends LockEvent {

        private AcquireLockEvent(LockClient client) {
            super(client);
        }
    }

    public static class DisConnectLockEvent extends LockEvent {

        public DisConnectLockEvent(LockClient client) {
            super(client);
        }
    }
}
