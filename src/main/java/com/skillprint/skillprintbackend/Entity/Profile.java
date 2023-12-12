package com.skillprint.skillprintbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    public Profile(ProfileType profileType, String privilegesDescription) {
        this.profileType = profileType;
        this.privilegesDescription = privilegesDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProfileType profileType;



    private String privilegesDescription;

    @ManyToMany(mappedBy = "profiles")
    private Set<Employed> employees = new HashSet<>();


}
