package com.thoughtworks.osgi.workshop.cms;

import com.thoughtworks.osgi.workshop.api.Node;
import com.thoughtworks.osgi.workshop.api.NodeImpl;
import com.thoughtworks.osgi.workshop.api.Render;
import org.osgi.service.component.ComponentContext;

public class Cms {
    protected void activate(ComponentContext context) {
        System.out.println("activing CMS...");
        final Render render = (Render) context.locateService("render");

        final Node root = new NodeImpl("root");
        root.addNode(new NodeImpl("header"));
        root.addNode(new NodeImpl("body"));
        root.addNode(new NodeImpl("footer"));

        // render
        render.render(root, null);
    }

    protected void deactivate(ComponentContext context) {

    }
}
