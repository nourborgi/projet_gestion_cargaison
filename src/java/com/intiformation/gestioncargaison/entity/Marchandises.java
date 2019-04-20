/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author IN-MP-007
 */
@Entity
public class Marchandises implements Serializable{
    
    /*=======================================================================*/
    /*======================== Attributs ====================================*/
    /*=======================================================================*/
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "numero")
    private int numero;
     @Column(name = "nom")
    private String nom;
     @Column(name = "poids(Tonne)")
    private double poids;
     @Column(name = "volume")
    private double volume;

     
    /*========================================================================*/
    /*================ association avec la classe Cargaison ===============*/
    /*========================================================================*/
      @ManyToOne
    // coté porter de la clé FK => @JoinColumn
    @JoinColumn(name = "cargaison_id", referencedColumnName = "reference")
    private Cargaison cargaison;
      

    public Cargaison getCargaison() {
        return cargaison;
    }

    public void setCargaison(Cargaison cargaison) {
        this.cargaison = cargaison;
    }
    
    /*========================================================================*/
    /*========================== Constructeurs ===============================*/
    /*========================================================================*/

    public Marchandises() {
    }

    public Marchandises(int numero, String nom, double poids, double volume) {
        this.numero = numero;
        this.nom = nom;
        this.poids = poids;
        this.volume = volume;
    }

    public Marchandises(String nom, double poids, double volume) {
        this.nom = nom;
        this.poids = poids;
        this.volume = volume;
    }
    
    /*========================================================================*/
    /*========================== Encapsulation ===============================*/
    /*========================================================================*/

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
     
    
}
