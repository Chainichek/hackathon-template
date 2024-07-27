package ru.chainichek.hackathon.template.activity;

import org.springframework.boot.SpringApplication;

public class TestActivityApplication {

    public static void main(String[] args) {
        SpringApplication.from(ActivityApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
