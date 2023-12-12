package com.skillprint.skillprintbackend.Service;


import com.skillprint.skillprintbackend.Entity.Employed;
import com.skillprint.skillprintbackend.Repository.EmployedRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final EmployedRepository employedRepository;

    public UserDetailsServiceImpl(EmployedRepository employedRepository) {
        this.employedRepository = employedRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employed employed = employedRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("user " + username + " does not exist"));

        Collection< ? extends GrantedAuthority> authorities = employed.getProfiles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getProfileType().name())))
                .collect(Collectors.toSet());

        return new User(employed.getUsername(),
                        employed.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        authorities);
    }
}
