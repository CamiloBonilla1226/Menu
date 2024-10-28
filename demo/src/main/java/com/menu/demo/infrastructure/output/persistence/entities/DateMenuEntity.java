package com.menu.demo.infrastructure.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_dates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateMenuEntity {
    @Id
    @Column(length = 200)
    private String id_date;
    private String date;
    private String hour;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_order")
    private MenuEntity objOrder;
}