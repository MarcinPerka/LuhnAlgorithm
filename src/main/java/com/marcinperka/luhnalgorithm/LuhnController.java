package com.marcinperka.luhnalgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LuhnController {

    private final LuhnService luhnService;

    @Autowired
    public LuhnController(LuhnService luhnService) {
        this.luhnService = luhnService;
    }


    /*   @PostMapping("/result")
       public String verifyDigitSequence(Model model, @Requestparam String digitSequence) {
           int checkDigit = luhnService.getCheckDigit(digitSequence);
           model.addAttribute("checkDigit", checkDigit);
           model.addAttribute("fullCode", digitSequence + checkDigit);
           model.addAttribute("isValid", luhnService.verifyFullCode(digitSequence + checkDigit));
           return "result";
       }*/

    @GetMapping("/")
    public String showForm(Model model,Luhn luhn) {
        model.addAttribute("luhn", luhn);
        return "home";
    }

    @PostMapping("/result")
    public String verifyDigitSequence(Luhn luhn) {
        luhnService.setLuhn(luhn);
        return "redirect:/results";
    }

    @GetMapping("/results")
    public String getAlgorithmResults(Model model) {
        Luhn luhn = luhnService.getLuhn();
        model.addAttribute("checkDigit", luhn.getFullCode().charAt(luhn.getFullCode().length() - 1));
        model.addAttribute("fullCode", luhn.getFullCode());
        model.addAttribute("isValid", luhn.getValid());
        return "result";
    }
}
