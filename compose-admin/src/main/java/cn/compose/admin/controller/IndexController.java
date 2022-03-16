package cn.compose.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@CrossOrigin
@RequestMapping
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/codeLogin")
    public String codeLogin() {
        return "codeLogin";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "loginSuccess";
    }

    @GetMapping("/groovy")
    public String groovy() {
        return "groovy";
    }


    @GetMapping("/groovyNew")
    public String groovyNew() {
        return "groovyNew";
    }

    @GetMapping("/listTemplate")
    public String listTemplate() {
        return "listTemplate";
    }

    @GetMapping("/groovyBatch")
    public String groovyBatch() {
        return "groovyBatch";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }
}
