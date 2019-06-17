package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/two")
public class ControllerTwo {

    @RequestMapping("/url-one/{urlparameter}")
    public ResponseEntity<LargeObject> getResponse(
            @PathVariable(value = "urlparameter", required = false) String fromUrl
    ) {
        return ResponseEntity.ok(new LargeObject());
    }
}
