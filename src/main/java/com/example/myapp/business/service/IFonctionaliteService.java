package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Fonctionalite;

import java.util.List;

public interface IFonctionaliteService {
    public List<Fonctionalite> getListFonctionalite();
    public Fonctionalite addFonctionalite(Fonctionalite fonctionalite);
    public void deleteFonctionalite(Long id);
    public Fonctionalite updateFonctionalite(Fonctionalite fonctionalite,Long id);
}
