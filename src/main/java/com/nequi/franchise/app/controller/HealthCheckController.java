package com.nequi.franchise.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Void> healthCheck() {
        // Si la aplicación está funcionando correctamente, devuelve un 200 OK
        return ResponseEntity.ok().build();
    }
}
