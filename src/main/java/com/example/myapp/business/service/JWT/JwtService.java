package com.example.myapp.business.service.JWT;

import com.example.myapp.business.service.ILogAccessService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.JWT.JwtRequest;
import com.example.myapp.persistence.JWT.JwtResponse;
import com.example.myapp.persistence.model.LogAccess;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ILogAccessService iLogAccessService;

    @Autowired
    private UtilisateurService utilisateurService;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByuserName(userName);
        return new JwtResponse(utilisateur, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findUtilisateurByuserName(userName);

        if (user != null) {
            log.info("user found in the databse : {}",userName);
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }

    private Set getAuthority(Utilisateur user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getProfils().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
            String code_success="Authentification succeded";

            iLogAccessService.saveLogAccess(code_success,userName);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            String code_erreur="Authentification failed";
            iLogAccessService.saveLogAccess(code_erreur,userName);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}