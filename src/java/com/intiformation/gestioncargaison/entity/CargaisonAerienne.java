/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IN-MP-007
 */
@Entity
@DiscriminatorValue("aerienne")
@XmlRootElement
public class CargaisonAerienne extends Cargaison{
    @Column(name = "Poids_max")
    private double poids_max;

    public CargaisonAerienne() {
    }

    public CargaisonAerienne(double poids_max, String reference, long distance, Date date_livraison) {
        super(reference, distance, date_livraison);
        this.poids_max = poids_max;
    }

    public CargaisonAerienne(double poids_max, int idCargaison, String reference, long distance, Date date_livraison) {
        super(idCargaison, reference, distance, date_livraison);
        this.poids_max = poids_max;
    }

   

   

    public double getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(double poids_max) {
        this.poids_max = poids_max;
    }

    @Override
    public String toString() {
        return "CargaisonAerienne{" + "poids_max=" + poids_max + '}';
    }
    
}
