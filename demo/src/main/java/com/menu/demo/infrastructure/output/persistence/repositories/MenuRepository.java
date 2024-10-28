package com.menu.demo.infrastructure.output.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.demo.infrastructure.output.persistence.entities.MenuEntity;


@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String>{
    
}
