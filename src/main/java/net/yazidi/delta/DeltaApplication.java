package net.yazidi.delta;

import net.yazidi.delta.security.models.AppRole;
import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.repository.AppRoleRepository;
import net.yazidi.delta.security.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DeltaApplication {

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private AppRoleRepository appRoleRepository;

	@Autowired
	private AppUserRepository appUserRepository;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DeltaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args->{
			Optional<AppRole> role1 = appRoleRepository.findById((long)1);
			if(role1.isEmpty()) appRoleRepository.save(new AppRole((long) 1,"USER"));
			Optional<AppRole> role2 = appRoleRepository.findById((long)2);
			if(role2.isEmpty()) appRoleRepository.save(new AppRole((long) 2,"ADMIN"));
			Optional<AppUser> user1 = appUserRepository.findById((long)1);
			if(user1.isEmpty()){
				AppUser admin = new AppUser((long)1,"admin","admin", List.of(new AppRole((long) 1,"USER"),new AppRole((long) 2,"ADMIN")));
				admin.setPassword(passwordEncoder.encode(admin.getPassword()));
				appUserRepository.save(admin);
			}
			Optional<AppUser> user2 = appUserRepository.findById((long)2);
			if(user2.isEmpty()){
				AppUser user = new AppUser((long)2,"user","user", List.of(new AppRole((long) 1,"USER")));
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				appUserRepository.save(user);
			}
		};
	}
}
