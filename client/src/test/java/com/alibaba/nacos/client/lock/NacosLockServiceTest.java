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
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * nacos lock service test.
 * @author 985492783@qq.com
 * @description NacosLockServiceTest
 * @date 2023/7/4 13:35
 */

public class NacosLockServiceTest {

    private LockService lockService;
    
    @Before
    public void before() throws NacosException {
        Properties prop = new Properties();
        prop.setProperty("serverAddr", "localhost");
        this.lockService = new NacosLockService(prop);
    }
    
    @Test
    public void test() throws NacosException {
        this.lockService.lock(new LockInfo());
    }
}
