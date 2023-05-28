package com.mamunrs.servicecore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;

//@Controller
@RestController
@RequestMapping("/ui/")
public class WebController {

    private final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    @Autowired
    Environment env;
    @Autowired
    ResourceLoader resourceLoader;


    // Not working
    @GetMapping("/index")
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    // Not working
    @GetMapping("/index2")
    public String indexPage2(){
        return "index";
    }

    @GetMapping("/home")
    public String loadHomePage() {
        Resource resource = new ClassPathResource("templates/html/home.html");
        try {
            InputStream inputStream = resource.getInputStream();
            byte[] byteData = FileCopyUtils.copyToByteArray(inputStream);
            String content = new String(byteData, StandardCharsets.UTF_8);
            LOGGER.info(content);
            return content;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return null;
    }

    @GetMapping("/home2")
    public String loadHomePage2() {
        try {
            File file = new ClassPathResource("templates/html/home.html").getFile();

            System.out.println("Filename: " + file.getName());
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Relative path: " + file.getPath());

            String content = new String(Files.readAllBytes(file.toPath()));
            LOGGER.info(content);
            return content;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return null;
    }

    @GetMapping("/home3")
    public String loadHomePage3() {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/html/home.html");
            InputStream inputStream = resource.getInputStream();
            byte[] byteData = FileCopyUtils.copyToByteArray(inputStream);
            String content = new String(byteData, StandardCharsets.UTF_8);
            LOGGER.info(content);
            return content;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return null;
    }


    @GetMapping("/c/{uri}")
    public void getCategoryHtml(@PathVariable("uri") String uri, HttpServletRequest req, HttpServletResponse res){
        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            String rs=this.getCategoryHtml();
            out.println(rs);rs=null;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public String getCategoryHtml() throws Exception {

        File file = new ClassPathResource("templates/html/home.html").getFile();
        String fileRelPath = file.getPath();
        System.out.println("Relative path: " + fileRelPath);

        String logo = "";
        FileInputStream fis = new FileInputStream(fileRelPath);
        logo = IOUtils.toString(fis, "UTF-8");
        fis.close();
        fis=null;
        return logo;
    }


}
