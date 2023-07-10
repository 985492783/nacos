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

package com.alibaba.nacos.lock.core.client;

import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.core.remote.ClientConnectionEventListener;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.lock.core.event.LockEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * lock connection manager.
 * @author 985492783@qq.com
 * @description LockConnectionManager
 * @date 2023/7/10 10:47
 */
@Component("lockConnectionManager")
public class LockConnectionManager extends ClientConnectionEventListener {
    private ConcurrentHashMap<String, Connection> connectionMap;

    public ReentrantReadWriteLock.ReadLock readLock;

    private ReentrantReadWriteLock.WriteLock writeLock;

    public LockConnectionManager() {
        connectionMap = new ConcurrentHashMap<>();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    @Override
    public void clientConnected(Connection connect) {
        try {
            writeLock.lock();
            connectionMap.put(connect.getMetaInfo().getConnectionId(), connect);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clientDisConnected(Connection connect) {
        try {
            writeLock.lock();
            connectionMap.remove(connect.getMetaInfo().getConnectionId());
        } finally {
            writeLock.unlock();
        }
        LockClient client = new NacosLockClient(connect);
        LockEvent.DisConnectLockEvent lockEvent = new LockEvent.DisConnectLockEvent(client);
        NotifyCenter.publishEvent(lockEvent);
    }

    public boolean isAlive(String connectionId) {
        return connectionMap.containsKey(connectionId);
    }
}
