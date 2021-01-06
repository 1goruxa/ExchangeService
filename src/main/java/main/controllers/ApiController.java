package main.controllers;

import main.api.request.ConvertRequest;
import main.api.response.AllCurStatResponse;
import main.api.response.ConvertResponse;
import main.api.response.FilterResponse;
import main.service.ExchangeService;
import main.service.StatService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController {

    private final ExchangeService exchangeService;
    private final StatService statService;

    public ApiController(ExchangeService exchangeService, StatService statService) {
        this.exchangeService = exchangeService;
        this.statService = statService;
    }

    @PostMapping("/exchange")
    public ConvertResponse exchange(@RequestBody ConvertRequest convertRequest){
        return exchangeService.exchange(convertRequest);
    }

    @RequestMapping("/MoneyExchangeServiceStat")
    public ArrayList<AllCurStatResponse> stat(Model model){
        return statService.curStat();

    }

    @GetMapping("/filter")
    public ArrayList<FilterResponse> filter(@RequestParam(required = false, defaultValue = "oneoper") String type,
                                 @RequestParam(required = false, defaultValue = "0") String amount){
        return statService.filterStat(type, amount);
    }

}
