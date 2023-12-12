package com.skillprint.skillprintbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    public Category(String categoryName, Double baseSalary, Double annualIncrease) {
        this.categoryName = categoryName;
        this.baseSalary = baseSalary;
        this.annualIncrease = annualIncrease;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    private String categoryName;

    private Double baseSalary;

    private Double annualIncrease;

//    Entity RelationShips
    @OneToMany(mappedBy = "category")
    private Set<Employed> employees;

}
