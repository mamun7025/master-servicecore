package com.mamunrs.servicecore.cache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cache")
public class CacheController {


    @GetMapping("/force-update/{jobName}")
    public ResponseEntity<?> forceCacheUpdate(@PathVariable final String jobName){
        return ResponseEntity.ok("OK");
    }

}
