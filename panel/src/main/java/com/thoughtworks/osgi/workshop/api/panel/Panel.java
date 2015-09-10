package com.thoughtworks.osgi.workshop.api.panel;

import com.thoughtworks.osgi.workshop.api.Node;
import com.thoughtworks.osgi.workshop.api.Render;

public class Panel implements Render {
    @Override
    public void render(Node node, String selector) {
        System.out.println("you are rendering: " + node.getName());

        System.out.println("rendering panel@ " + node.getNodes().get(0).getName());
    }
}
