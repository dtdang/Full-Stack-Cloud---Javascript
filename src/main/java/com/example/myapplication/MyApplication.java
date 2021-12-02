package com.example.myapplication;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
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
    //     Email from = new Email("kitsune_akuma@yahoo.com");
    //     String subject = "Company Registration Discount";
    //     Email to = new Email(email);
    //     Content content = new Content("text/plain", "You've been added to the list! Here is the code for your 10% discount.");
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

    @RequestMapping("/emails")
    public String showEmails(Model model) throws IOException, InterruptedException, ExecutionException{
        model.addAttribute("emails", getEmails());
        return "emails";
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

    public List<Map<String, Object>> getProducts() throws IOException, InterruptedException, ExecutionException {
        List<Map<String, Object>> prodMap = new ArrayList<>();
        Firestore db = getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("products").get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        for (QueryDocumentSnapshot document: documents){
            prodMap.add(document.getData());
        }
        return prodMap;
    }

    @RequestMapping("/products")
    public String showProducts(Model model) throws IOException, InterruptedException, ExecutionException{
        model.addAttribute("products", getProducts());
        return "products";
    }

    @GetMapping("/product/edit/{id}")
    public String getProducts(Model model, @PathVariable("id") String prodID) throws InterruptedException, ExecutionException, IOException{
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("products").document(prodID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        System.out.println("\nDocument data: " + document.getData());

        Map<String, Product> data = new HashMap<>();
        data.put(prodID, new Product(document.getString("ProductID"), document.getString("Name"),  document.getString("Stock"),  document.getString("Price")));
        model.addAttribute("editProduct", data.get(prodID));

        return "product-edit";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@RequestParam Map<String, Object> body, Model model)
        throws IOException, InterruptedException, ExecutionException {
    Firestore db = getFirestore();
    System.out.println("\nUpdate Data: " + body + "\n");

    Map<String, Object> updated_data = new HashMap<>();
    updated_data.put("ProductID", body.get("id"));
    updated_data.put("Name", body.get("name"));
    updated_data.put("Price", body.get("price"));
    updated_data.put("Stock", body.get("stock"));

    DocumentReference document = db.collection("products").document((String) body.get("id"));

    ApiFuture<WriteResult> result = document.update(updated_data);
    System.out.println("\nUpdate Data: " + updated_data + "\n");
    System.out.println("Update time : " + result.get().getUpdateTime());
    return "redirect:/products";
}

    @RequestMapping("/shop")
    public String shop(){
        return "shop";
    }

    private Firestore getFirestore() throws IOException {
        FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("astral-altar-333819")
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        return firestoreOptions.getService();
    }
}
