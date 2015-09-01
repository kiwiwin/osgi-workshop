package com.thoughtworks.osgi.workshop.cms;

import com.thoughtworks.osgi.workshop.api.Node;
import com.thoughtworks.osgi.workshop.api.NodeImpl;
import com.thoughtworks.osgi.workshop.api.Render;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        final ServiceReference<Render> serviceReference = context.getServiceReference(Render.class);

        final Render render = context.getService(serviceReference);

        Node node = new NodeImpl("root");
        node.addNode(new NodeImpl("header"));
        node.addNode(new NodeImpl("body"));
        node.addNode(new NodeImpl("footer"));

        render.render(node, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
