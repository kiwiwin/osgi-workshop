package com.thoughtworks.osgi.workshop.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeImpl implements Node {
    protected final String name;
    protected Map<String, Node> nodes = new HashMap<String, Node>();
    protected Map<String, String> properties = new HashMap<String, String>();

    public NodeImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Node addNode(Node node) {
        return nodes.put(node.getName(), node);
    }

    @Override
    public Node getNode(String nodeName) {
        return nodes.get(nodeName);
    }

    @Override
    public List<Node> getNodes() {
        return new ArrayList<Node>(nodes.values());
    }

    @Override
    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String setProperty(String propertyName, String propertyValue) {
        return properties.get(propertyName);
    }
}
