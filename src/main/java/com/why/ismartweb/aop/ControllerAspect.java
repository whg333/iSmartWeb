package com.why.ismartweb.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.why.ismart.framework.aop.Aspect;
import com.why.ismart.framework.aop.AspectProxy;
import com.why.ismart.framework.aop.Proxy;
import com.why.ismart.framework.aop.ProxyManager;
import com.why.ismart.framework.mvc.Controller;
import com.why.ismart.framework.mvc.Param;
import com.why.ismartweb.web.controller.CustomerController;

//觉得应该把@Aspect注解直接添加到具体的Controller上，然后value指定处理的AspectProxy
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    
    private long beginTime;
    
    @Override
    public void before(Class<?> clazz, Method method, Object[] params) {
        LOGGER.info("------------- ControllerAspect before -------------");
        LOGGER.info(String.format("class: %s", clazz.getName()));
        LOGGER.info(String.format("method: %s", method.getName()));
        beginTime = System.currentTimeMillis();
    }
    
    @Override
    public void after(Class<?> clazz, Method method, Object[] params, Object result) {
        LOGGER.info(String.format("time elapsed: %dms", System.currentTimeMillis()-beginTime));
        LOGGER.info("------------- ControllerAspect after -------------");
    }
    
    public static void main(String[] args) {
        Class<?> clazz = ControllerAspect.class;
        System.out.println(AspectProxy.class.isAssignableFrom(clazz) && !AspectProxy.class.equals(clazz));
        
        List<Proxy> proxyList = new ArrayList<Proxy>();
        proxyList.add(new ControllerAspect());
        CustomerController proxy = ProxyManager.createProxy(CustomerController.class, proxyList);
        proxy.customer(new Param(null));
    }
    
}
