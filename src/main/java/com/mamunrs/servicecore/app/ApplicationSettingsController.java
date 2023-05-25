package com.mamunrs.servicecore.app;

import com.mamunrs.servicecore.app.entity.ApplicationSettings;
import com.mamunrs.servicecore.app.repository.ApplicationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/settings")
public class ApplicationSettingsController {


    @Autowired
    ApplicationSettingsRepository applicationSettingsRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllData(){
        List<ApplicationSettings> listData = applicationSettingsRepository.findAll();
        return ResponseEntity.ok(listData);
    }

}
