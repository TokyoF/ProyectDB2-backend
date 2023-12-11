package com.skillprint.skillprintbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentService {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @Column( nullable = false, length = 4000)
    private String commentContent;

    private Integer qualification;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfComment;


    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Employed employed;

}
