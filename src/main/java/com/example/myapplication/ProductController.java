package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	//create map to store product records
	public Map<String, Product> prodMap = new HashMap<>();

	// create product objects
	//controller constructor
	public ProductController(){
		super();
	}

	@GetMapping
	public String getProducts(Model model){
		model.addAttribute("products", prodMap.values());
		return "product-list";
	}
}
