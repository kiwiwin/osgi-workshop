package com.thoughtworks.osgi.workshop.api.rowlayout;

import com.thoughtworks.osgi.workshop.api.Node;
import com.thoughtworks.osgi.workshop.api.Render;

public class RowLayout implements Render {

    @Override
    public void render(Node node, String selector) {
        System.out.println("you are rendering: " + node.getName());
        for (int row = 0; row < node.getNodes().size(); row++) {
            System.out.println("rendering row@ " + (row + 1) + ":" + node.getNodes().get(row).getName());
        }
    }
}
