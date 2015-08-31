package com.thoughtworks.osgi.workshop.app;

import com.thoughtworks.osgi.workshop.definition.HelloWorld;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

//public class ExtensionPoint {
public class ExtensionPoint implements ServiceTrackerCustomizer<HelloWorld, HelloWorld> {
    @Override
    public HelloWorld addingService(ServiceReference<HelloWorld> serviceReference) {


        BundleContext context = serviceReference.getBundle().getBundleContext();

//        TextProvider service = context.getService(serviceReference);
//        activity.addButton(serviceReference.hashCode(), service.text());

        final HelloWorld service = context.getService(serviceReference);
        return service;
    }

    @Override
    public void modifiedService(ServiceReference<HelloWorld> serviceReference, HelloWorld textProvider) {

    }

    @Override
    public void removedService(ServiceReference<HelloWorld> serviceReference, HelloWorld textProvider) {

    }
}

