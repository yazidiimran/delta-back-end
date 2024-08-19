package net.yazidi.delta.security.web;

import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/admin/users")
    public AppUser create(@RequestBody AppUser appUser) {
        return accountService.addUser(appUser);
    }

    @PutMapping("/admin/users/{id}")
    public AppUser update(@PathVariable Long id,@RequestBody AppUser appUser) {
        return accountService.updateUser(appUser);
    }

    @DeleteMapping("/admin/users/{id}")
    public void remove(@PathVariable Long id) {
        accountService.removUser(id);
    }

    @GetMapping("/users/{id}")
    public AppUser findById(@PathVariable Long id) {
        return accountService.findUserById(id);
    }
    @GetMapping("/admin/users")
    public List<AppUser> findAll() {
        return accountService.allUsers();
    }

}
