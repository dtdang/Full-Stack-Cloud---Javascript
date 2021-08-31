package com.example.myapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
	// @RequestMapping("/")
	// @ResponseBody
	// public String index(){
	// 	return "hello";
	// }

	@RequestMapping("/app")
	@ResponseBody
	public String app(){
		return "app";
	}

	@RequestMapping("/app/something")
    @ResponseBody
    public String something() {
        return "something";
    }

	@RequestMapping("/app/something/else")
    @ResponseBody
    public String somethingElse() {
        return "something else";
    }

	@RequestMapping("/login")
	public String login(){
		return "login.html";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("error", "Username or password incorrect");
		return "login.html";
	}
}
