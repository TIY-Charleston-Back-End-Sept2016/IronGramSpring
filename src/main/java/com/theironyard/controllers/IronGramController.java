package com.theironyard.controllers;

import com.theironyard.entities.Image;
import com.theironyard.entities.Recipient;
import com.theironyard.entities.User;
import com.theironyard.services.ImageRepository;
import com.theironyard.services.RecipientRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

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
    public String upload(MultipartFile image, String recs, HttpSession session) throws Exception {
        File dir = new File("public/images");
        dir.mkdirs();
        File f = File.createTempFile("image", image.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(image.getBytes());

        User user = users.findFirstByName((String) session.getAttribute("username"));
        if (user == null) {
            throw new Exception("Not logged in.");
        }
        Image img = new Image(f.getName(), user);
        images.save(img);

        for (String rec : recs.split(",")) {
            User recUser = users.findFirstByName(rec);
            if (recUser != null) {
                Recipient recipient = new Recipient(img, recUser);
                recipients.save(recipient);
            }
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        User user = users.findFirstByName(username);
        if (user == null) {
            user = new User(username);
            users.save(user);
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
