package com.link.demopac.cloudstroredemo.controller;

import com.link.demopac.cloudstroredemo.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
    public String uploadFile(
            HttpServletRequest request, @RequestParam("file") MultipartFile file
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
}
