package com.thoughtworks.osgi.workshop.provider;

import com.thoughtworks.osgi.workshop.HelloWorld;

public class DefaultHelloWorldService implements HelloWorld {
    @Override
    public String sayHello() {
        System.out.println("I am the default hello world service");
        return "I am the default hello world service";
    }
}
