package cn.sqh.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.ApplicationPath;

/**
 * 启动配置入口
 * Created by Administrator on 2017/2/17.
 */
@ApplicationPath("/*")
public class DemoApiApplication extends ResourceConfig {
    public DemoApiApplication() {
        packages("cn.sqh.resource");
        register(JacksonFeature.class);
        register(new ContextLoaderListener());
        register(new RequestContextListener());
        property("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }

}
