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

/**
 *
 * @author IN-MP-007
 */
@Entity
@DiscriminatorValue("routiere")
public class CargaisonRoutiere extends Cargaison{
    @Column(name = "T_conservation(°C)")
    private double temperature_conservation;

    public CargaisonRoutiere() {
    }

    public CargaisonRoutiere(double temperature_conservation, long distance, Date date_livraison) {
        super(distance, date_livraison);
        this.temperature_conservation = temperature_conservation;
    }

    public double getTemperature_conservation() {
        return temperature_conservation;
    }

    public void setTemperature_conservation(double temperature_conservation) {
        this.temperature_conservation = temperature_conservation;
    }

    @Override
    public String toString() {
        return "CargaisonRoutiere{" + "temperature_conservation=" + temperature_conservation + '}';
    }
    
}
