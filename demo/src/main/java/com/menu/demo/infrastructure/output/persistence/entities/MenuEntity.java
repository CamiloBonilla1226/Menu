package com.menu.demo.infrastructure.output.persistence.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 200)
    private String id_menu; 

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_date")
    private DateMenuEntity date; 

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "menu_dish",
        joinColumns = @JoinColumn(name = "id_menu"),
        inverseJoinColumns = @JoinColumn(name = "id_dish")
    )
    private List<DishEntity> dishes; 

    public MenuEntity() {
        this.dishes = new ArrayList<>(); 
    }
}