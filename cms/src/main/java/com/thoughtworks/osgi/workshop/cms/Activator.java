package com.thoughtworks.osgi.workshop.cms;

import com.thoughtworks.osgi.workshop.api.Node;
import com.thoughtworks.osgi.workshop.api.NodeImpl;
import com.thoughtworks.osgi.workshop.api.Render;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        final ServiceTracker<Render, Render> tracker = new ServiceTracker<Render, Render>(context, Render.class, new ServiceTrackerCustomizer<Render, Render>() {
            @Override
            public Render addingService(ServiceReference<Render> reference) {
                // fake data
                final Node root = new NodeImpl("root");
                root.addNode(new NodeImpl("header"));
                root.addNode(new NodeImpl("body"));
                root.addNode(new NodeImpl("footer"));

                // render
                final Render render = reference.getBundle().getBundleContext().getService(reference);
                render.render(root, null);
                return render;
            }

            @Override
            public void modifiedService(ServiceReference<Render> reference, Render service) {

            }

            @Override
            public void removedService(ServiceReference<Render> reference, Render service) {

            }
        });
        tracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
