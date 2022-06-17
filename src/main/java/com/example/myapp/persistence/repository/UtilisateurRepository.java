package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    @Query("SELECT s FROM Utilisateur s where s.id=:id")
    public Utilisateur findUtilisateurById(@Param("id")Long id);



    @Query("SELECT s FROM Utilisateur s where s.email=:email")
    public Utilisateur findUtilisateurByEmail(@Param("email")String email);

    @Query("SELECT u FROM Utilisateur u where u.userName =:userName")
    public  Utilisateur findUtilisateurByuserName(@Param("userName") String userName);

    @Query("SELECT u FROM Utilisateur u where u.userName =:userName")
    public  Utilisateur findUtilisateurProfils(@Param("userName") String userName);

    @Query("SELECT u FROM Utilisateur u where u.resetPasswordToken =:resetPasswordToken")
    public Utilisateur findByResetPasswordToken(String resetPasswordToken);

    public List<Utilisateur> findAllByOrderByIdAsc();




}
