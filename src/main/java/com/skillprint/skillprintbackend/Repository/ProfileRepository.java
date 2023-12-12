package com.skillprint.skillprintbackend.Repository;

import com.skillprint.skillprintbackend.Entity.Profile;
import com.skillprint.skillprintbackend.Entity.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Profile findByProfileType(ProfileType profileType);
}
