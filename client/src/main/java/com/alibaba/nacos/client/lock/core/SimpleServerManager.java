package com.alibaba.nacos.client.lock.core;

import com.alibaba.nacos.common.remote.client.ServerListFactory;

import java.util.List;

/**
 * @author qiyue.zhang@aloudata.com
 * @description SimpleServerManager
 * @date 2023/6/29 10:42
 */
public class SimpleServerManager implements ServerListFactory {
    
    @Override
    public String genNextServer() {
        return null;
    }
    
    @Override
    public String getCurrentServer() {
        return null;
    }
    
    @Override
    public List<String> getServerList() {
        return null;
    }
}
