package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.EmployeeRepository;
import com.example.myapp.persistence.repository.ProfilRepository;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import com.example.myapp.presentation.Utils.EmailUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Utilisateur> getListUtilisateur(){
        try {
            log.info("Fetching all users ");
            return utilisateurRepository.findAllByOrderByIdAsc();
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method getListUtilisateur " + e.toString());
        }
    }

    @Override
    public Set<Profil> getListUtilisateurProfils(Long id){
        try {
            log.info("Fetching all users ");
            Set <Profil> profils = utilisateurRepository.getById(id).getProfils();
            return profils ;
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method getListUtilisateurProfils " + e.toString());
        }
    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        try {
            if(id == null)
                return new Utilisateur();
            Utilisateur p = utilisateurRepository.findUtilisateurById(id);
            if ( p == null )
                return new Utilisateur();
            log.info("Fetching user with id :{} ",id);
            return p;
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurById :: " + e.toString());
        }
    }

    @Override
    public Set<Profil> getUtilisateurRoleByEmpId(Long id) {
        try {
            if(id == null)
                return null;
            Utilisateur p = utilisateurRepository.findUtilisateurById(id);
            if ( p == null )
                return null;
            log.info("Fetching role user with id :{} ",id);
            return p.getProfils();
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurById :: " + e.toString());
        }
    }

    @Override
    public Utilisateur getUtilisateurByuserName(String userName) {
        try {
            if(userName == null)
                return new Utilisateur();
            Utilisateur p = utilisateurRepository.findUtilisateurByuserName(userName);
            if ( p == null )
                return new Utilisateur();
            log.info("Fetching user with username :{} ",userName);
            return p;
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurByuserName :: " + e.toString());
        }
    }





    @Override
    public Utilisateur addUtilisateur(Utilisateur obj) {
        try {
            Set<Profil> userRoles = new HashSet<>();
            for(Profil profil: obj.getProfils() ){
                Profil role = profilRepository.findProfilByName(profil.getName());
                userRoles.add(role);
            }



            Utilisateur objNomUnique = utilisateurRepository.findUtilisateurByuserName(obj.getUserName());
            if (objNomUnique != null)
                throw new IllegalStateException("Utilisateur login token");
            sendEmail(obj.getEmail(), obj.getUserName(), obj.getUserPassword());

            obj.setCreationdate(new Timestamp(new Date().getTime()));
            obj.setUserPassword(getEncodedPassword(obj.getUserPassword()));
            //obj.setUserPassword(obj.getUserPassword());
            obj.setProfils(userRoles);
            obj.setEmployee(new Employee("","","",LocalDate.now(),"","","Non séléctionné","Non séléctionné","Non séléctionné","")
            );

            log.info("Saving new user {} to the databse ",obj.getUserName());

            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method addUtilisateur " + e.toString());
        }
    }

    public void sendEmail(String recipientEmail, String username, String password)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ymahfoudh55@gmail.com", "ST2i managment");
        helper.setTo(recipientEmail);

        String subject = "voici vos données de connexion";
        String link="http://localhost:4200/login";
        String content = "<p>Bonjour,</p>"
                + "<p>Pour accéder à votre compte utilisez ces identifiants</p>"
                + "<p> your login :" + username+" </p>"
                + "<p> your password :" + password+" </p>"
                 + "<p>Cliquez sur le lien ci-dessous pour changer votre mot de passe :</p>"
                + "<p><a href=\"" + link + "\">S'identifier</a></p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur,Long id) {
        try {
            Utilisateur obj = utilisateurRepository.findUtilisateurById(id);
            Set<Profil> userRoles = new HashSet<>();
            for(Profil profil: utilisateur.getProfils() ){
                Profil role = profilRepository.findProfilByName(profil.getName());
                userRoles.add(role);
            }
           obj.setUserName(utilisateur.getUserName());
         //  obj.setUserPassword(getEncodedPassword(utilisateur.getUserPassword()));
           obj.setEmail(utilisateur.getEmail());
           obj.setUpdatedate(new Timestamp(new Date().getTime()));
           obj.setProfils(userRoles);
           obj.setId(id);
            log.info("updating user {} to the database ",utilisateur.getUserName());
            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method updateUtilisateur : " + e.toString());
        }

    }
    @Override
    public void deleteUtilisateur(Long id) {
        try {
            log.info("Deleting user with id {}  ",id);
            utilisateurRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method deleteUtilisateur : " + e.toString());
        }
    }

    @Override
    public Utilisateur findUtilisateurByEmail(final String email){
        log.info("Fetching user with email :{} ",email);
        return utilisateurRepository.findUtilisateurByEmail(email);
    }



    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            Utilisateur user = utilisateurRepository.findUtilisateurByuserName(currentUserName());
            if(!user.equals(null)){
               if(passwordEncoder.matches(requestMap.get("oldPassword"), user.getUserPassword())) {
                   //if(user.getUserPassword().equals(requestMap.get("oldPassword"))){
                   if (!Objects.equals(requestMap.get("oldPassword"), requestMap.get("newPassword"))) {
                       user.setUserPassword(getEncodedPassword(requestMap.get("newPassword")));
                       utilisateurRepository.save(user);
                       log.info("User with username :{} change his password",user.getUserName());
                       return new ResponseEntity<>(HttpStatus.OK);
                   }
               }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

      public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        log.info("Get the current user username");
        return username;
    }


    //Reset Password

    @Override
    public void updateResetPasswordToken(String token, String email) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
        if (utilisateur != null) {
            utilisateur.setResetPasswordToken(token);
            utilisateurRepository.save(utilisateur);
        } else {
            throw new IllegalStateException("Could not find any user with the email " + email);
        }
    }
    @Override
    public Utilisateur getByResetPasswordToken(String token) {
        return utilisateurRepository.findByResetPasswordToken(token);
    }
    @Override
    public void updatePassword(Utilisateur utilisateur, String newPassword) {
        String encodedPassword=getEncodedPassword(newPassword);
        utilisateur.setUserPassword(encodedPassword);

        utilisateur.setResetPasswordToken(null);
        log.info("User with username :{} update his password",utilisateur.getUserName());
        utilisateurRepository.save(utilisateur);
    }

    /*@Override
    public Utilisateur connect(Utilisateur user) {
        Utilisateur dbUser = utilisateurRepository.findUtilisateurByuserName(user.getUserName());

        if (dbUser != null) {

            if (dbUser.getConnected()) {
                throw new IllegalStateException("This user is already connected: " + dbUser.getUserName());
            }
                dbUser.setConnected(true);
                return utilisateurRepository.save(dbUser);
            }

            user.setConnected(true);
            return utilisateurRepository.save(user);
        }


    @Override
    public Utilisateur disconnect(Utilisateur user) {
        if (user == null) {
            return null;
        }

            Utilisateur dbUser = utilisateurRepository.findUtilisateurByuserName(user.getUserName());
        if (dbUser == null) {
            return user;
        }

        dbUser.setConnected(false);
        return utilisateurRepository.save(dbUser);
    }
*/
}


