package com.mamunrs.servicecore.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ui/v2/")
public class Web2Controller {


    // Not working
    @GetMapping("/home2")
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    // working
    @GetMapping("/home")
    public String indexPage2(){
        System.out.println("rendering home page");
        return "home";
    }


}
