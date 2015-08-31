package com.thoughtworks.osgi.workshop.consumer;

import com.thoughtworks.osgi.workshop.definition.HelloWorld;
import com.thoughtworks.osgi.workshop.definition.HelloWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorldConsumer implements ActionListener {
    private final Timer timer;
    private HelloWorld helloWorld;

    public HelloWorldConsumer(HelloWorld helloWorld) {
        super();

        this.helloWorld = helloWorld;
        timer = new Timer(1000, this);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        helloWorld.sayHello();
    }
}
