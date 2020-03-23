package com.ericsson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MdmsSettingsMockApplication {
    public static String primaryIpPort;
    public static String standbyPort;

    public static void main(String[] args) {
        primaryIpPort = args[0];
        standbyPort = args[1];
        System.out.println("Primary Address is : "+primaryIpPort);
        System.out.println("Standby Address is : "+standbyPort);
        SpringApplication.run(MdmsSettingsMockApplication.class, args);
    }

}
