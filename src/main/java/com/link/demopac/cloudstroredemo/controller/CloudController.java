package com.link.demopac.cloudstroredemo.controller;

import com.link.demopac.cloudstroredemo.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CloudController {

    @Autowired
    private CloudService cloudServiceImpl;

    @GetMapping("/upload")
    public String uploadFile(){
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file
    ){
        String resp = "fail";
        try {
            cloudServiceImpl.uploadFileToCloud(file);
            resp = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @GetMapping("/query")
    @ResponseBody
    public String queryFile(){
        String url = "";
        try {
            url = cloudServiceImpl.queryFile("26e3f8fc-0706-4ed8-b046-aefcfdb3684a");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
