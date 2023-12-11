package com.skillprint.skillprintbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PayrollDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayrollDetail;

    // Comentado para añadir relación con Nomina más tarde
    // @Column(name = "ID_Nomina", nullable = false)
    // private Long idNomina;

    private String description;

    private Double amount;

    @Column( length = 50)
    private String type;

    @ManyToOne
    @JoinColumn(name = "idPayroll")
    private Payroll payroll;

}
