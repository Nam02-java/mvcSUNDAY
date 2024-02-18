package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.Package02.Selenium;
import com.example.Selenium.SpeechToText.Model.DataStoreModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Collections;

public abstract class InitializationDriverController implements Runnable {

    DataStoreModel dataStoreModel;

    public InitializationDriverController(DataStoreModel dataStoreModel) {
        this.dataStoreModel = dataStoreModel;
    }

    public void initializationDriver() {
//        System.out.println("Params text : " + dataStoreModel.getParams().get("Text") + "\n"
//                + "Params voice : " + dataStoreModel.getParams().get("Voice") + "\n"
//                + "Params file name : " + dataStoreModel.getParams().get("FileName"));

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "E:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false); // Disable chrome running as automation
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // Disable chrome running as automation

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // The number of seconds that a driver waits to load an element without the wait setting

        driver.manage().window().maximize();
    }

    public abstract void configureDriverOnTheWebsite();

    @Override
    public void run() {
        initializationDriver();
    }
}

class testInitializationDriver extends InitializationDriverController{

    public testInitializationDriver(DataStoreModel dataStoreModel) {
        super(dataStoreModel);
    }

    @Override
    public void configureDriverOnTheWebsite() {

    }

    @Override
    public void run() {
        initializationDriver();
    }
}

class testToRun{
    public static void main(String[] args) {
        DataStoreModel dataStoreModel = new DataStoreModel();
//        testInitializationDriver t = new testInitializationDriver(dataStoreModel);
//        t.run();

        Thread thread1 = new Thread(new InitializationDriverController(dataStoreModel) {
            @Override
            public void configureDriverOnTheWebsite() {
                System.out.println("oke");
            }
        });
        thread1.start();
    }
}