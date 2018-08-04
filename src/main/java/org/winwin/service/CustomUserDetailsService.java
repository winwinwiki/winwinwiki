package org.winwin.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.winwin.model.ApplicationUser;
import org.winwin.repository.ApplicationUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private ApplicationUserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
    	log.error("username = " + username);
        ApplicationUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authoritySet =  new HashSet<GrantedAuthority>();
        authoritySet.add(new SimpleGrantedAuthority(user.getRole()));
        User retUser = new User(user.getUsername(), user.getPassword(), authoritySet);
        return retUser; 
    }
}