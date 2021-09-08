package com.example.myapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	//create map to store product records
	public Map<String, Product> products = new Hashmap<>();

	@GetMapping
	public String getProducts(Model model){

	}
}
