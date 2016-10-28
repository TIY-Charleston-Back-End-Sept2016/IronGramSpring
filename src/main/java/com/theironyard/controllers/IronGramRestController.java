package com.theironyard.controllers;

import com.theironyard.entities.Image;
import com.theironyard.entities.Recipient;
import com.theironyard.entities.User;
import com.theironyard.services.ImageRepository;
import com.theironyard.services.RecipientRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zach on 10/28/16.
 */
@RestController
public class IronGramRestController {
    @Autowired
    UserRepository users;

    @Autowired
    ImageRepository images;

    @Autowired
    RecipientRepository recipients;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String name = (String) session.getAttribute("username");
        return users.findFirstByName(name);
    }

    @RequestMapping(path = "/images", method = RequestMethod.GET)
    public List<Image> getImages(HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = users.findFirstByName(name);

        ArrayList<Image> recImages = new ArrayList<>();
        for (Recipient rec : recipients.findByUser(user)) {
            recImages.add(rec.getImage());

            if (rec.getViewed() != null) {
                if (rec.getViewed().isBefore(LocalDateTime.now().minusSeconds(10))) {
                    recipients.delete(rec);
                }
            }
            else {
                rec.setViewed(LocalDateTime.now());
                recipients.save(rec);
            }
        }
        return recImages;
    }
}
