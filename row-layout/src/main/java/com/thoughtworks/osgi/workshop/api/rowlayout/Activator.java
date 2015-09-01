package com.thoughtworks.osgi.workshop.api.rowlayout;

import com.thoughtworks.osgi.workshop.api.Render;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<Render> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        serviceRegistration = context.registerService(Render.class, new RowLayout(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
