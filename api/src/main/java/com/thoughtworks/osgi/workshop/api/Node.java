package com.thoughtworks.osgi.workshop.api;

import java.util.List;

public interface Node {
    String getName();

    Node addNode(Node node);

    Node getNode(String nodeName);

    List<Node> getNodes();

    String getProperty(String propertyName);

    String setProperty(String propertyName, String propertyValue);
}
