package com.alibaba.nacos.client.lock.remote;

import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.client.security.SecurityProxy;
import com.alibaba.nacos.client.utils.AppNameUtils;
import com.alibaba.nacos.plugin.auth.api.RequestResource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 985492783@qq.com
 * @description AbstractLockClient
 * @date 2023/6/28 17:19
 */
public abstract class AbstractLockClient implements LockClient {
    private final SecurityProxy securityProxy;
    
    private static final String APP_FILED = "app";
    
    protected AbstractLockClient(SecurityProxy securityProxy) {
        this.securityProxy = securityProxy;
    }
    
    
    
    protected Map<String, String> getSecurityHeaders(String namespace, String group, String serviceName) {
        RequestResource resource = RequestResource.namingBuilder().setNamespace(namespace).setGroup(group)
                .setResource(serviceName).build();
        Map<String, String> result = this.securityProxy.getIdentityContext(resource);
        result.putAll(getAppHeaders());
        return result;
    }
    
    protected Map<String, String> getAppHeaders() {
        Map<String, String> result = new HashMap<>(1);
        result.put(APP_FILED, AppNameUtils.getAppName());
        return result;
    }
}
