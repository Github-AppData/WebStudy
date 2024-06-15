package com.example.churchFucTest.controller;

import com.example.churchFucTest.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MailController {

	MailService mailService;
	
	@Autowired
    public MailController(MailService mailService) {
		this.mailService = mailService;
	}

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }
    
    

    @PostMapping("/forgetPW2")
    public String execMail(@RequestParam String mail, RedirectAttributes rttr, Model model)
    {
    	System.out.println("mail : "+ mail);
    	int number = mailService.sendMail(mail);

        String num = null;
		num = "" + number;
        
        System.out.println("controller num : "+ num);
        model.addAttribute("num", number);

        return "redirect:/login/forgetPW2";
    }
}
