package com.codingapi.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

/**
 * create by lorne on 2017/12/24
 */
@Controller
public class UeditorController {

    private Logger logger = LoggerFactory.getLogger(UeditorController.class);

    private String rootPath;

    public UeditorController() {
        String path  = UeditorController.class.getClassLoader().getResource("config.json").getPath();
        logger.info("path->"+path);
        File file =  new File(path);
        if(file.getParentFile().isDirectory()) {
            rootPath = new File(path).getParent()+"/";
        }else{
            rootPath = new File(path).getParentFile().getParent()+"/";
            rootPath = rootPath.replace("file:","");
        }
    }

    @RequestMapping("/exec")
    public void exec(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        response.setHeader("Content-Type" , "text/html");
        logger.info("rootPath->"+rootPath);
        out.write( new ActionEnter( request, rootPath).exec());
    }

}
