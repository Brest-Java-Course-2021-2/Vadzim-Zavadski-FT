package com.zavadski.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController {

    private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @RequestMapping("/errors")
    public ModelAndView handleError(HttpServletRequest request,
                                    @RequestParam(required = false) String errorMessage) {
        ModelAndView errorPage = new ModelAndView("errors");
            errorPage.addObject("errorMessage", errorMessage);
            return errorPage;
    }
}
