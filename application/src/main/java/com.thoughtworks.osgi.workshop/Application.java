package com.thoughtworks.osgi.workshop;

import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.osgi.util.tracker.ServiceTracker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class Application {
    private static Framework framework;

    public static void main(String[] args) {
        launchFramework();
        init();

        startBundle("helloworld-1.0.jar");

        startBundle("helloworld-provider-1.0.jar");
        startBundle("helloworld-consumer-1.0.jar");
    }

    private static void startBundle(String bundle) {
        InputStream stream = null;
        try {
            stream = getBundle(bundle);
            org.osgi.framework.Bundle installed = framework.getBundleContext().installBundle(bundle, stream);
            installed.start();
        } catch (BundleException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static InputStream getBundle(String bundle) {
        return ExtensionPoint.class.getResourceAsStream("/bundles/" + bundle);
    }

    private static void init() {

        ServiceTracker<HelloWorld, HelloWorld> extensions
                = new ServiceTracker<HelloWorld, HelloWorld>(framework.getBundleContext(), HelloWorld.class, null);
        extensions.open();
    }

    private static void launchFramework() {
        ServiceLoader<FrameworkFactory> frameworkFactories = ServiceLoader.load(FrameworkFactory.class);
        Iterator<FrameworkFactory> iterator = frameworkFactories.iterator();
        if (iterator.hasNext()) {
            framework = iterator.next().newFramework(new HashMap<String, String>());
//            framework = iterator.next().newFramework(getFrameworkConfig());
            try {
                framework.init();
                framework.start();
//                framework.waitForStop(0);
            } catch (BundleException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, String> getFrameworkConfig() {
        Map<String, String> config = new HashMap<String, String>();
        config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "com.thoughtworks.osgi.workshop.helloworld");
        try {
            config.put(Constants.FRAMEWORK_STORAGE, File.createTempFile("osgi", "launcher").getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

}
