package com.example.shortlinks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @Operation(
            summary = "Error",
            description = "Redirect to custom error page controller."
    )
    @GetMapping
    public String handleError() {

        return "errorpage";
    }
}