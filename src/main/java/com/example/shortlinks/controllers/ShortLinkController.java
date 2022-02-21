package com.example.shortlinks.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Tag(name="ShortLinkController", description="Interface for controller implements")
@RequestMapping("/")
public interface ShortLinkController {

    @GetMapping
    ModelAndView greeting();

    @PostMapping("/create")
    ModelAndView createShortLink(@RequestParam String originalLink, @RequestParam int liveCycle,Model model) throws NoSuchAlgorithmException;

    @GetMapping("/redirect/{code}")
    ResponseEntity<?> redirect(@PathVariable("code") String u) throws Exception;

    @GetMapping("/remove/{code}")
    ModelAndView removeUrl(@PathVariable("code") String u) throws Exception ;

    @GetMapping("/all")
    ModelAndView showAll(Model model) throws Exception ;

}
