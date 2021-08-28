package com.example.myapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping; // added
import org.springframework.web.bind.annotation.RestController; // added
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/notify-me")
    public String notifyMe() {
        return "{\"message\": \"notify-me\"}"; // message back from server
    }
}
// feature commit
