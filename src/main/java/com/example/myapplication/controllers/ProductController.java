package com.example.myapplication.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	//create map to store product records
	// public Map<String, Object> prodMap = new HashMap<>();

	// @GetMapping("/products-list")
	// public String getProducts(Model model){
	// 	model.addAttribute("products", prodMap.values());
	// 	return "product-list";
	// }
}
