package net.yazidi.delta;

import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Client;
import net.yazidi.delta.entity.Entreprise;
import net.yazidi.delta.entity.Fournisseur;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.entity.Unite;
import net.yazidi.delta.repository.CategoryRepository;
import net.yazidi.delta.repository.ClientRepository;
import net.yazidi.delta.repository.EntrepriseRepository;
import net.yazidi.delta.repository.FournisseurRepository;
import net.yazidi.delta.repository.ProduitRepository;
import net.yazidi.delta.repository.UniteRepository;
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
	private CategoryRepository  categoryRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private UniteRepository uniteRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

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

			if(categoryRepository.count()==0){
				Categorie categorie1 = Categorie.builder().libelle("Medicaments").build();
				categorie1 = categoryRepository.save(categorie1);
				Categorie categorie2 = Categorie.builder().libelle("Fourniture").build();
				categorie2 = categoryRepository.save(categorie2);
				Unite unite = Unite.builder().libelle("unité").build();
				unite = uniteRepository.save(unite);
				Produit produit1 = Produit.builder().libelle("Doliprane").unite(unite).categorie(categorie1).build();
				produitRepository.save(produit1);
				Produit produit2 = Produit.builder().libelle("Fouteuille").unite(unite).categorie(categorie2).build();
				produitRepository.save(produit2);
			}

			if(clientRepository.count()==0){
				Client client1 = Client.builder().nom("Yazidi Client 1").build();
				clientRepository.save(client1);
				Client client2 = Client.builder().nom("Simo Client 2").build();
				clientRepository.save(client2);
			}

			if(fournisseurRepository.count()==0){
				Fournisseur fournisseu1 = Fournisseur.builder().nom("Atmani Fournisseur 1").build();
				fournisseurRepository.save(fournisseu1);
				Fournisseur fournisseur2 = Fournisseur.builder().nom("Majer Fournisseur 2").build();
				fournisseurRepository.save(fournisseur2);
			}

			/*
			 * I  E T I C
			Formations – Développement informatique
			Tél : 06 44 84 66 27 / 05 36 61 70 90
			Email : contact@ieticberkane.com
			Site web : www.ieticberkane.com
			RIB Banque populaire: 157575212110822616000120 
			RC: 5525  
			IF: 20722450  
			Siège social : 62, rue fes hay hassani berkane - Maroc
			 */

			if(entrepriseRepository.count()==0){
				Entreprise entreprise = Entreprise.builder()
					.raisonSociale("I  E T I C")
					.description("Formations – Développement informatique")
					.CNSS("432432424324")
					.tel("+212 6 44 84 66 27")
					.fax("+212 5 36 61 70 90")
					.adresse("null")
					.email("contact@ietic.com")
					.siteweb("www.ietic.com")
					.ICE("000156644000006")
					.adresse("86 RUE SIDI AHMED ABERKANE HAY DAKHLA")
					.RC("5525")
					.IFF("20722450")
					.ville("Berkane")
					.pays("Maroc")
					.build();
				entrepriseRepository.save(entreprise);
			}
		};
	}
}
