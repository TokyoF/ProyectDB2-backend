package com.skillprint.skillprintbackend.Service;

import com.skillprint.skillprintbackend.DTO.EmployedCreationDto;
import com.skillprint.skillprintbackend.Entity.Employed;
import com.skillprint.skillprintbackend.Entity.Profile;
import com.skillprint.skillprintbackend.Entity.ProfileType;
import com.skillprint.skillprintbackend.Repository.EmployedRepository;
import com.skillprint.skillprintbackend.Repository.ProfileRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployedService {

    private final PasswordEncoder passwordEncoder;
    private final EmployedRepository employedRepository;

    private final ProfileRepository profileRepository;
    public EmployedService(EmployedRepository employedRepository , PasswordEncoder passwordEncoder,ProfileRepository profileRepository) {
        this.employedRepository = employedRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
    }

    public Employed registerEmployed(EmployedCreationDto employedCreationDto){
        Profile profileSeller = profileRepository.findByProfileType(ProfileType.SELLER);

        Set<Profile> profiles = new HashSet<>(Collections.singletonList(profileSeller));


        Employed employed = Employed.builder()
                .username(employedCreationDto.getUsername())
                .password(passwordEncoder.encode(employedCreationDto.getPassword()))
                .email(employedCreationDto.getEmail())
                .profiles(profiles)
                .build();

        return employedRepository.save(employed);
    }

    public Employed  getEmployedByUsername(String username){
        Employed employed= employedRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user " + username + " does not exist"));
        employed.setPassword("");
        return employed;
    }

}
