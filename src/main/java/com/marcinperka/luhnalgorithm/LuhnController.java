package com.marcinperka.luhnalgorithm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LuhnController {

    private final LuhnService luhnService;

    @Autowired
    public LuhnController(LuhnService luhnService) {
        this.luhnService = luhnService;
    }

    @PostMapping("/result")
    public String verifyDigitSequence(Model model, @RequestParam String digitSequence) {
        int checkDigit = luhnService.getCheckDigit(digitSequence);
        model.addAttribute("checkDigit", checkDigit);
        model.addAttribute("fullCode", digitSequence + checkDigit);
        model.addAttribute("isValid", luhnService.verifyFullCode(digitSequence + checkDigit));
        return "result";
    }
}
