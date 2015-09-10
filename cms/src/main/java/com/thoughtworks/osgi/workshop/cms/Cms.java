package com.thoughtworks.osgi.workshop.cms;

import com.thoughtworks.osgi.workshop.api.NodeImpl;
import com.thoughtworks.osgi.workshop.api.Render;
import org.osgi.service.component.ComponentContext;

public class Cms {

    private NodeImpl root;

    protected void activate(ComponentContext context) {
        System.out.println("activing CMS...");

        root = new NodeImpl("root");
        root.addNode(new NodeImpl("header"));
        root.addNode(new NodeImpl("body"));
        root.addNode(new NodeImpl("footer"));
    }

    protected void deactivate(ComponentContext context) {

    }

    protected void addRender(Render render) {
        render.render(root, null);
    }
}
