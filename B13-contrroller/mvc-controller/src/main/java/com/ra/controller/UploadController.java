package com.ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller

public class UploadController {
    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/save-file")
    public void save(@RequestParam("img") MultipartFile fileImage, HttpServletRequest request){
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileNane = fileImage.getOriginalFilename();
        File destination = new File(path+"/"+fileNane);
        try {
            fileImage.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
