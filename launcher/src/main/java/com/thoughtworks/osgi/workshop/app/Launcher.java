package com.thoughtworks.osgi.workshop.app;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class Launcher {
    private static Framework framework;

    public static void main(String[] args) {
        launchFramework();
        startBundles("bundles");
    }

    private static void startBundles(String directory) {
        List<Bundle> installedBundles = new ArrayList<Bundle>();
        List<File> jarFiles = new ArrayList<File>();

        for (File file : new File(directory).listFiles()) {
            if (file.getAbsolutePath().endsWith(".jar")) {
                jarFiles.add(file);
            }
        }

        for (File bundleFile : jarFiles) {
            try {
                installedBundles.add(framework.getBundleContext().installBundle(bundleFile.toURI().toString()));
            } catch (BundleException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        for (Bundle installedBundle : installedBundles) {
            try {
                installedBundle.start();
            } catch (BundleException e) {
                e.printStackTrace();
            }
        }
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
