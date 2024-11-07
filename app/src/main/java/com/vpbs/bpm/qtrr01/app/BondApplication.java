package com.vpbs.bpm.qtrr01.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vpbs.bpm.*")
@RequiredArgsConstructor
public class BondApplication {
    public static void main(String[] args) {
        SpringApplication.run(BondApplication.class, args);
    }
}
