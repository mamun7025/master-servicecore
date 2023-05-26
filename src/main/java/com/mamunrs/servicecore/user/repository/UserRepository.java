package com.mamunrs.servicecore.user.repository;

import com.mamunrs.servicecore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
