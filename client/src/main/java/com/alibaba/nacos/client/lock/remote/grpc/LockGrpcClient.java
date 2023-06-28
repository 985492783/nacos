package com.alibaba.nacos.client.lock.remote.grpc;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.lock.constant.PropertyConstants;
import com.alibaba.nacos.api.lock.listen.EventListen;
import com.alibaba.nacos.api.lock.model.LockInfo;
import com.alibaba.nacos.api.lock.model.LockInstance;
import com.alibaba.nacos.api.remote.RemoteConstants;
import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.client.lock.remote.AbstractLockClient;
import com.alibaba.nacos.client.naming.cache.ServiceInfoHolder;
import com.alibaba.nacos.client.naming.remote.gprc.NamingPushRequestHandler;
import com.alibaba.nacos.client.utils.AppNameUtils;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.remote.ConnectionType;
import com.alibaba.nacos.common.remote.client.RpcClient;
import com.alibaba.nacos.common.remote.client.RpcClientFactory;
import com.alibaba.nacos.common.remote.client.RpcClientTlsConfig;
import com.alibaba.nacos.common.remote.client.ServerListFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.alibaba.nacos.client.utils.LogUtils.NAMING_LOGGER;

/**
 * @author qiyue.zhang@aloudata.com
 * @description LockGrpcClient
 * @date 2023/6/28 17:35
 */
public class LockGrpcClient extends AbstractLockClient {
    private final String uuid;
    
    private final Long requestTimeout;
    
    private final RpcClient rpcClient;
    
    public LockGrpcClient(NacosClientProperties properties,
            ServerListFactory serverListFactory) throws NacosException {
        this.uuid = UUID.randomUUID().toString();
        this.requestTimeout = Long.parseLong(properties.getProperty(PropertyConstants.LOCK_REQUEST_TIMEOUT, "-1"));
        Map<String, String> labels = new HashMap<>();
        labels.put(RemoteConstants.LABEL_SOURCE, RemoteConstants.LABEL_SOURCE_SDK);
        labels.put(RemoteConstants.LABEL_MODULE, RemoteConstants.LABEL_MODULE_NAMING);
        labels.put(Constants.APPNAME, AppNameUtils.getAppName());
        this.rpcClient = RpcClientFactory.createClient(uuid, ConnectionType.GRPC, labels,
                RpcClientTlsConfig.properties(properties.asProperties()));
        start(serverListFactory);
    }
    private void start(ServerListFactory serverListFactory) throws NacosException {
        rpcClient.serverListFactory(serverListFactory);
        rpcClient.start();
    }
    @Override
    public Boolean lock(LockInfo lockInfo) {
        return null;
    }
    
    @Override
    public Boolean waitLock(LockInfo lockInfo, long waitTime) {
        return null;
    }
    
    @Override
    public void addListener(LockInstance instance, EventListen eventListen) {
    
    }
    
    @Override
    public void shutdown() throws NacosException {
    
    }
}
