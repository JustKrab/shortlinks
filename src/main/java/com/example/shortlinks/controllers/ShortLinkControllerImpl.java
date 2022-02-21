package com.example.shortlinks.controllers;


import com.example.shortlinks.services.URLService;
import com.example.shortlinks.services.impl.ShortLinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Tag(name="ShortLinkControllerIMP", description="Do all jobs")
@RestController
public class ShortLinkControllerImpl implements ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @Autowired
    private URLService urlService;

    @Operation(
            summary = "Main page",
            description = "Open the main page."
    )
    @Override
    public ModelAndView greeting() {
        return new ModelAndView("index");
    }

    @Operation(
            summary = "Create.",
            description = "Create a short link."
    )
    @Override
    public ModelAndView createShortLink(String originalLink, int liveCycle, Model model) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView("index");
        if (originalLink != null && originalLink.startsWith("https")) {
            modelAndView.addObject("response", "http://localhost:8080/" + shortLinkService.create(originalLink, liveCycle));
        } else {
            modelAndView.addObject("response", "The field can't be empty. And starts with https...");
        }
        return modelAndView;
    }

    @Operation(
            summary = "Redirect",
            description = "Redirect to original link."
    )
    @Override
    public ResponseEntity<?> redirect(String u) throws Exception {
        return shortLinkService.redirect(u);
    }



    @Operation(
            summary = "Remove",
            description = "Remove url from bd and delete a redirect to original link."
    )
    @Override
    public ModelAndView removeUrl(String u) throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/all");
        shortLinkService.removeUrl(u);
        return modelAndView;
    }

    @Operation(
            summary = "Show all",
            description = "Show all information about shorten links without sign."
    )
    @Override
    public ModelAndView showAll(Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView("showall");
        modelAndView.addObject("links", urlService.findAll());

        return modelAndView;
    }


}
