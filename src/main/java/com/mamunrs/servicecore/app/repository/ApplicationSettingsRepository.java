package com.mamunrs.servicecore.app.repository;

import com.mamunrs.servicecore.app.entity.ApplicationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationSettingsRepository extends JpaRepository<ApplicationSettings, Long> {
}
