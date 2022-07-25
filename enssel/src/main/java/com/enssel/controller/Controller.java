package com.enssel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

@RestController("/enssel")
public class Controller {
    @GetMapping
    public ModelAndView getMain(HttpServletRequest request, HttpServletResponse response) {
        String lang = getCookie(request, response);

        ModelAndView mav = new ModelAndView("view/main");

        mav.addObject("lang", lang);

        return mav;
    }

    @GetMapping("/{page}")
    public ModelAndView getPage(@PathVariable(required = false) String page,
                                @RequestParam(required = false, defaultValue = "") String display,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        String lang = getCookie(request, response);

        ModelAndView mav = getViewName(new ModelAndView(), page, display);

        mav.addObject("lang", lang);
        mav.addObject("display", display);

        return mav;
    }

    public ModelAndView getViewName(ModelAndView mav, String page, String display) {
        String viewName = "view/" + page + "/" + display;

        mav.setViewName(viewName);
        return mav;
    }

    public String getCookie(HttpServletRequest request, HttpServletResponse response) {
        // Default Lang : Korea
        String lang = "ko";

        // If cookies are 'NULL'. And then return default lang.
        Cookie[] cookies = request.getCookies();

        if (!Objects.isNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("lang")) {
                    lang = cookie.getValue();
                }
            }
        } else {
            Cookie cookie = new Cookie("lang", lang);
            response.addCookie(cookie);
        }

        return lang;
    }
}
