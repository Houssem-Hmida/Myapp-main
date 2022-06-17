package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.*;
import com.example.myapp.presentation.Utils.Utility;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping(path = "/utilisateur")
@AllArgsConstructor
public class UtilisateurController {


    @Autowired
    private final IUtilisateurService iUtilisateurService;
    @Autowired
    private final ILogDataService iLogDataService;
    @Autowired
    private JavaMailSender mailSender;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping
    public List<Utilisateur> getListUtilisateur() {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter la liste des utilisateurs");
            return iUtilisateurService.getListUtilisateur();
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getListUtilisateur:" +e.toString());
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/profils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public Set<Profil> getListUtilisateurProfils(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter la liste des profils  de l'utilisateur "+id);
            return iUtilisateurService.getListUtilisateurProfils(id);
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getListUtilisateurProfils:" +e.toString());
        }

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/role/emp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Profil>> getUtilisateurRoleByEmpId (@PathVariable("id") Long id) {
        try{ Set<Profil> roles = iUtilisateurService.getUtilisateurRoleByEmpId(id);
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter l'utilisateur numéro : "+id);
            return new ResponseEntity<Set<Profil>>(roles, HttpStatus.OK);}
        catch (Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getUtilisateurById:" +e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurById (@PathVariable("id") Long id) {
       try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurById(id);
           iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter l'utilisateur numéro : "+id);
           return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
       catch (Exception e){
           throw new IllegalStateException("Error UtilisateurController in method getUtilisateurById:" +e.toString());
       }
    }

    @GetMapping(value = "/find/name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurByuserName (@PathVariable("userName") String userName) {
        try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurByuserName(userName);
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter l'utilisateur  : "+userName);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
        catch (Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getUtilisateurByuserName:" +e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur addUtilisateur(@RequestBody Utilisateur obj){
       try{
           iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Ajouter un nouveau utilisateur");
           return iUtilisateurService.addUtilisateur(obj);
       }catch(Exception e){
           throw new IllegalStateException("Error UtilisateurController in method addUtilisateur:" +e.toString());
       }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUtilisateur(@PathVariable("id") Long id) {
        try{
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Supprimer l'utilisateur numéro : "+id);
            iUtilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method deleteUtilisateur:" +e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur obj,  @PathVariable("id")  Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Mettre à jour l'utilisateur numéro : "+id);
            Utilisateur updateUtilisateur = iUtilisateurService.updateUtilisateur(obj, id);
        return new ResponseEntity<>(updateUtilisateur, HttpStatus.OK);
    }
        catch(Exception e){
        throw new IllegalStateException("Error UtilisateurController in method updateUtilisateur:" +e.toString());
        }
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Obtenir le nom de l'utilisateur actuel");

            return iUtilisateurService.currentUserName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    }

@PostMapping("/forgotPassword")
public  ResponseEntity<String> processForgotPassword(HttpServletRequest request,@RequestBody Map<String,String> requestMap) {
    String email = requestMap.get("email");
    String token = RandomString.make(30);

    try {
        iUtilisateurService.updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
        sendEmail(email, resetPasswordLink);
        return new ResponseEntity<>(HttpStatus.OK);


    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}


    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ymahfoudh55@gmail.com", "ST2i managment");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Bonjour,</p>"
                + "<p>Vous avez demandé la réinitialisation de votre mot de passe.</p>"
                + "<p>Cliquez sur le lien ci-dessous pour changer votre mot de passe :</p>"
                + "<p><a href=\"" + link + "\">Réinitialiser mon mot de passe</a></p>"
                + "<br>"
                + "<p>Ignorez cet e-mail si vous vous souvenez de votre mot de passe, "
                + "ou vous n'avez pas fait la demande.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap){
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Changer son mot de passe ");
            return iUtilisateurService.changePassword(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
