package com.thoughtworks.osgi.workshop.cms;

import com.thoughtworks.osgi.workshop.api.Layout;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        final ServiceReference<Layout> serviceReference = context.getServiceReference(Layout.class);

        final Layout layout = context.getService(serviceReference);
        layout.addComponent("header");
        layout.addComponent("body");
        layout.addComponent("footer");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
