package com.thoughtworks.osgi.workshop.app;

import com.thoughtworks.osgi.workshop.definition.HelloWorld;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.osgi.util.tracker.ServiceTracker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class Application {
    private static Framework framework;

    public static void main(String[] args) {
        launchFramework();
        init();

        startBundles(
                "helloworld-1.0.jar",
                "helloworld-provider-1.0.jar",
                "helloworld-consumer-1.0.jar"
                );
    }

    private static void startBundles(String... bundles) {
        List<Bundle> installedBundles = new ArrayList<Bundle>();
        for (String bundle : bundles) {
            installedBundles.add(installBundle(bundle));
        }
        for (Bundle installedBundle : installedBundles) {
            try {
                installedBundle.start();
            } catch (BundleException e) {
                e.printStackTrace();
            }
        }
    }

    private static Bundle installBundle(String bundle) {
        InputStream stream = null;
        try {
            stream = getBundle(bundle);
            return framework.getBundleContext().installBundle(bundle, stream);
        } catch (BundleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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
                = new ServiceTracker<HelloWorld, HelloWorld>(framework.getBundleContext(), HelloWorld.class, new ExtensionPoint());
        extensions.open();
    }

    private static void launchFramework() {
        ServiceLoader<FrameworkFactory> frameworkFactories = ServiceLoader.load(FrameworkFactory.class);
        Iterator<FrameworkFactory> iterator = frameworkFactories.iterator();
        if (iterator.hasNext()) {
            framework = iterator.next().newFramework(getFrameworkConfig());
            try {
                framework.init();
                framework.start();
            } catch (BundleException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, String> getFrameworkConfig() {
        Map<String, String> config = new HashMap<String, String>();
        config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "com.thoughtworks.osgi.workshop.definition");
        config.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);
        try {
//            config.put(Constants.FRAMEWORK_STORAGE, File.createTempFile("osgi", "launcher").getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

}
