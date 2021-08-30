package com.example.myapplication;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String notifyMe(@RequestBody Map<String, String> body)
            throws IOException, InterruptedException, ExecutionException {
        Firestore db = getFirestore();
        DocumentReference document = db.collection("emails").document(body.get("email"));
        ApiFuture<WriteResult> data = document.set(body);
        System.out.println(data.get().getUpdateTime());
        return new ObjectMapper().writeValueAsString(body);
    }

    private Firestore getFirestore() throws IOException {
        FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("landing-project-dd")
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        return firestoreOptions.getService();
    }
}
// feature commit
