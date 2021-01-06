package main.controllers;

import main.api.response.AllCurStatResponse;
import main.service.StatService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@EnableAutoConfiguration
@Controller
public class DefaultController {


    @RequestMapping("/")
    public String index(Model model) {
        return "MoneyExchangeService";
    }

    @RequestMapping("/MoneyExchangeServiceStats")
    public String  stat(Model model){
        return "MoneyExchangeServiceStats";

    }

}
