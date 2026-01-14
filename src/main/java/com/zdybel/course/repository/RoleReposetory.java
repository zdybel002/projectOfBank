package com.zdybel.course.repository;


import com.zdybel.course.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleReposetory extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
