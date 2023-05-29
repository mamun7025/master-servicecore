package com.mamunrs.servicecore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/ui/v2/")
public class Web2Controller {

    private final Logger LOGGER = LoggerFactory.getLogger(Web2Controller.class);
    @Autowired
    Environment env;
    @Autowired
    ResourceLoader resourceLoader;


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
        System.out.println("sadsadasdsdsa");
        return "home";
    }



}
