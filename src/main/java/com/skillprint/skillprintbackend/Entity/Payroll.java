package com.skillprint.skillprintbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payroll {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPayroll;

    // Comentado para añadir relación con Empleado más tarde
    // @Column(name = "ID_Empleado", nullable = false)
    // private Long idEmpleado;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date broadcastDate;

    private Double grossTotal;

    private Double totalDeductions;

    private Double netTotal;

    @ManyToOne
    @JoinColumn(name = "idEmployed")
    private Employed employed;

    @OneToMany(mappedBy = "payroll")
    private Set<PayrollDetail> payrollDetails;

}
