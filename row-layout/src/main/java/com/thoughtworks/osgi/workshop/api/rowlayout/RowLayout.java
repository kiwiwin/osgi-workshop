package com.thoughtworks.osgi.workshop.api.rowlayout;

import com.thoughtworks.osgi.workshop.api.Layout;

public class RowLayout implements Layout {
    @Override
    public void addComponent(String componentName) {
        System.out.println("adding component: " + componentName);
    }
}
