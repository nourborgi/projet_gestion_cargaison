/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.facade;

import com.intiformation.gestioncargaison.entity.Cargaison;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IN-MP-007
 */
public class CargaisonFacade extends AbstractFacade<Cargaison>{

    
    @PersistenceContext(unitName = "pu_db_gestion_cargaison")
    private EntityManager entityManager;

    public CargaisonFacade() {
        super(Cargaison.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}
