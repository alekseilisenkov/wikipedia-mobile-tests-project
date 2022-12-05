package com.alexlis.helpers;

import com.alexlis.config.RunConfig;
import com.alexlis.drivers.BrowserstackMobileDriver;
import com.alexlis.drivers.LocalAndroidDriver;
import com.alexlis.drivers.RealMobileDriver;
import com.alexlis.drivers.SelenoidAndroidDriver;
import org.aeonbits.owner.ConfigFactory;

public class RunHelper {

    private final RunConfig config = ConfigFactory.create(RunConfig.class, System.getProperties());

    private RunHelper() {
    }

    public static RunHelper runHelper() {
        return new RunHelper();
    }

    public Class<?> getDriverClass() {
        switch (config.deviceHost()) {
            case "browserstack":
                return BrowserstackMobileDriver.class;
            case "local":
                return LocalAndroidDriver.class;
            case "real":
                return RealMobileDriver.class;
            case "selenoid":
                return SelenoidAndroidDriver.class;
            case "null":
                throw new NullPointerException("Device host is null");
            default:
                throw new RuntimeException("Incorrect device host");
        }
    }

}
