package com.menu.demo.infrastructure.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_dates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 200)
    private String id_date;
    private String date;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu")
    private MenuEntity objMenu;

}
