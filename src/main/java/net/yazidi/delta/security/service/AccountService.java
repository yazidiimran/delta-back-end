package net.yazidi.delta.security.service;

import net.yazidi.delta.security.repository.AppRoleRepository;
import net.yazidi.delta.security.repository.AppUserRepository;
import net.yazidi.delta.security.models.AppRole;
import net.yazidi.delta.security.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;
    public AppUser addUser(AppUser user){
        AppUser appUser = appUserRepository.findByUsername(user.getUsername());
        if(appUser!=null)
            return appUser;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    public AppRole addRole(AppRole role){
        return  appRoleRepository.save(role);
    }

    public List<AppUser> allUsers() {
        return appUserRepository.findAll();
    }

    public void removUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser findUserById(Long id) {
        return appUserRepository.findById(id).get();
    }

    public AppUser updateUser(AppUser user) {
        return appUserRepository.save(user);
    }
}
