package com.theironyard.controllers;

import com.theironyard.services.ImageRepository;
import com.theironyard.services.RecipientRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zach on 10/28/16.
 */
@Controller
public class IronGramController {
    @Autowired
    UserRepository users;

    @Autowired
    ImageRepository images;

    @Autowired
    RecipientRepository recipients;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload() {
        return "redirect:/";
    }
}
