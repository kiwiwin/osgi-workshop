package com.thoughtworks.osgi.workshop.provider;

import com.thoughtworks.osgi.workshop.definition.HelloWorld;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<HelloWorld> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        serviceRegistration = context.registerService(HelloWorld.class, new DefaultHelloWorldService(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
