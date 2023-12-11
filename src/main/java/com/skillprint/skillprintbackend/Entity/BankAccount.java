package com.skillprint.skillprintbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBankAccount;

    // Comentado para añadir relación con Empleado más tarde
    // @Column(name = "ID_Empleado", unique = true, nullable = false)
    // private Long idEmpleado;

    @Column( nullable = false, length = 20)
    private String accountNumber;

    @Column( length = 100)
    private String bankName;

    @Column( length = 100)
    private String branch;

    @OneToOne
    @JoinColumn(name = "idEmployed",referencedColumnName = "idEmployed")
    private Employed employed;
}
