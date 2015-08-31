package com.thoughtworks.osgi.workshop.consumer;

import com.thoughtworks.osgi.workshop.HelloWorld;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private HelloWorldConsumer consumer;

    @Override
    public void start(BundleContext context) throws Exception {
        final ServiceReference<HelloWorld> serviceReference = context.getServiceReference(HelloWorld.class);

        consumer = new HelloWorldConsumer(context.getService(serviceReference));
        consumer.startTimer();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        consumer.stopTimer();
    }
}
