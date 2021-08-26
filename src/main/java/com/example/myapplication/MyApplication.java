package com.example.myapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping; // added
import org.springframework.web.bind.annotation.RestController; // added

@SpringBootApplication
@RestController // added
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @GetMapping("/hello") // added
    public String hello() { // added
        return "Hello world!"; // added
    } // added
}
