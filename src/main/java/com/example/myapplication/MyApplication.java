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
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
// import com.sendgrid.Method;
// import com.sendgrid.Request;
// import com.sendgrid.SendGrid;
// import com.sendgrid.helpers.mail.Mail;
// import com.sendgrid.helpers.mail.objects.Content;
// import com.sendgrid.helpers.mail.objects.Email;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @PostMapping("/notify-me")
    public String notifyMe(@RequestBody Map<String, String> body)
            throws IOException, InterruptedException, ExecutionException {
        Firestore db = getFirestore();
        String email = body.get("email");
        DocumentReference document = db.collection("emails").document(email);
        ApiFuture<WriteResult> data = document.set(body);
        System.out.println(data.get().getUpdateTime());

        //sendEmail(email);
        return new ObjectMapper().writeValueAsString(body);
    }

    // private void sendEmail(String email) throws IOException {
    //     Email from = new Email("mimidalena@gmail.com");
    //     String subject = "Full Stack Cloud Developer Course Registration";
    //     Email to = new Email(email);
    //     Content content = new Content("text/plain", "You've been added to the list! We'll notify you when registration begins.");
    //     Mail mail = new Mail(from, subject, to, content);

    //     SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    //     Request request = new Request();
    //     try {
    //         request.setMethod(Method.POST);
    //         request.setEndpoint("mail/send");
    //         request.setBody(mail.build());
    //         Response response = sg.api(request);
    //         System.out.println(response.getStatusCode());
    //         System.out.println(response.getBody());
    //         System.out.println(response.getHeaders());
    //     } catch (IOException ex) {
    //         throw ex;
    //     }
    // }

    @GetMapping("/email-list")
    public List<Map<String, Object>> getEmails() throws IOException, InterruptedException, ExecutionException{
        List<Map<String, Object>> list = new ArrayList<>();
        Firestore db = getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("emails").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            list.add(document.getData());
        }
        return list;
    }

    @PostMapping("/product-post")
    public String productList(@RequestBody Map<String, Object> body)
        throws IOException, InterruptedException, ExecutionException {
        Firestore db = getFirestore();


        Map<String, Object> data = new HashMap<>();
        data.put("ProductID", body.get("id"));
        data.put("Name", body.get("name"));
        data.put("Price", body.get("price"));
        data.put("Stock", body.get("stock"));

        DocumentReference document = db.collection("products").document((String) body.get("id"));

        ApiFuture<WriteResult> result = document.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());
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
