package net.yazidi.delta.security.service;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import net.yazidi.delta.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class JwtService {
    private static String secretKey="9faa372517ac1d389758d3750f09876f00f542277f26fec1ce4593e93f64e338";
    private static JwtEncoder jwtEncoder=new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));;
    private static AuthenticationManager authenticationManager;


    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static String generateToken(String username, String password,AppUserRepository userRepository){

        UserDetailsService userDetailsService = new MyUserDetailsService(userRepository);
         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        authenticationManager = new ProviderManager(daoAuthenticationProvider);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Instant now=Instant.now();
        String scope= authentication.getAuthorities()
                .stream().map(auth->auth.getAuthority())
                .collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuedAt(now)
                .subject(authentication.getName())
                .expiresAt(now.plus(60*24*30, ChronoUnit.MINUTES))
                .claim("scope",scope)
                .build();
        JwtEncoderParameters jwtEncoderParameters=
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
        return jwt.getTokenValue();
    }
}
