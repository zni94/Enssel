package com.enssel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("/enssel")
public class Controller {

    // 메인
    @GetMapping("/{lang}")
    public ModelAndView getMain(@PathVariable(name = "lang") String lang,
                                @RequestParam(required = false, defaultValue = "") String page,
                                @RequestParam(required = false, defaultValue = "") String display) {
        ModelAndView mav = getViewName(new ModelAndView(), page, display);

        mav.addObject("lang", lang);
        mav.addObject("display", display);
        return mav;
    }

    public ModelAndView getViewName(ModelAndView mav, String page, String display) {
        String viewName = "view/";
        if(!page.equalsIgnoreCase("") && !display.equalsIgnoreCase("") )
            viewName += page + "/" + display;
        else
            viewName += "index";

        mav.setViewName(viewName);
        return mav;
    }
}
