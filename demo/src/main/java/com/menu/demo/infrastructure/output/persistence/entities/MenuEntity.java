package com.menu.demo.infrastructure.output.persistence.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "menus")
@Data
@AllArgsConstructor
public class MenuEntity {
    @Id
    @Column(length = 200, name = "id_menu")
    private String id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "objMenu")
    @JoinColumn(name = "id_date")
    private DateMenuEntity ObjDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "menu")
    private List<DishEntity> dishes;

    public MenuEntity() {
        this.dishes = new ArrayList<>();
    }
}