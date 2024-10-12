package net.yazidi.delta.security.service;

import net.yazidi.delta.security.repository.AppUserRepository;
import net.yazidi.delta.security.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    public MyUserDetailsService() {}

    public MyUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String[] roles = user.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
        return userDetails;
    }
}
