package com.marcinperka.luhnalgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LuhnController {

    private final LuhnService luhnService;

    @Autowired
    public LuhnController(LuhnService luhnService) {
        this.luhnService = luhnService;
    }

    /**
     * @param digitSequence - Sequence of digits without space and decimal places. The pattern of number is checked by input data type on view page.
     *                      The parameter is of the string type because it is not known how large the number will be provided. BigInteger/Long  would be cast
     *                      in the service layer to the string anyway to get to the given digit.
     */
    @RequestMapping(value = "/result", method = {RequestMethod.GET, RequestMethod.POST})
    public String processLuhnAlgorithm(Model model, @RequestParam String digitSequence) {
        int checkDigit = luhnService.getCheckDigit(digitSequence);
        model.addAttribute("digitSequence", digitSequence);
        model.addAttribute("checkDigit", checkDigit);
        model.addAttribute("isValid", luhnService.validateTheNumber(digitSequence));
        return "result";
    }
}
