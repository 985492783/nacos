package com.alibaba.nacos.client.lock;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.LockService;
import com.alibaba.nacos.api.lock.model.LockInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * @author qiyue.zhang@aloudata.com
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
