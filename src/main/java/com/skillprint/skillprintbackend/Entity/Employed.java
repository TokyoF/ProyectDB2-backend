package com.skillprint.skillprintbackend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Employed {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long idEmployed;

        @Column( unique = true, nullable = false, length = 50)
        private String username;

        @Column( nullable = false)
        private String password;

        @Column( unique = true, nullable = false, length = 100)
        @Email
        private String email;

        @Column( length = 20)
        private String dni;

        @Column( length = 100)
        private String name;

        @Column( length = 100)
        private String lastname;

        @Column( length = 20)
        private String phone;

        private String address;


        @Temporal(TemporalType.DATE)
        private Date admissionDate;


        @Temporal(TemporalType.DATE)
        private Date terminationDate;


        private String photoUrl;

//      RelationShips

        @ManyToOne
        @JoinColumn(name = "idCategory")
        private Category category;

        @OneToOne(mappedBy = "employed")
        private BankAccount bankAccount;

        @OneToMany(mappedBy = "employed")
        private Set<Payroll> payrolls;

        @OneToMany(mappedBy = "employed")
        private Set<Service> services;

        @ManyToMany
        @JoinTable(
                name = "Employed_Profile",
                joinColumns = @JoinColumn(name = "idEmployed"),
                inverseJoinColumns = @JoinColumn(name = "idProfile")
        )
        private Set<Profile> profiles;

    }


