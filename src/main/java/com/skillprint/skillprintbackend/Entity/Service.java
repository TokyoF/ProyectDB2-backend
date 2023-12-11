package com.skillprint.skillprintbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    // Comentado para añadir relación con Empleado más tarde
    // @Column(name = "ID_Empleado", nullable = false)
    // private Long idEmpleado;

    @Column(nullable = false, length = 100)
    private String serviceTitle;

    @Column( length = 4000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "idEmployed")
    private Employed employed;

    @OneToMany(mappedBy = "service")
    private Set<CommentService> commentServices;


}
