/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.facade;

import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IN-MP-007
 */
public abstract class AbstractFacade<T> {

    private Class<T> entity;

    /*__________________________ctor_____________________________*/
    /**
     * ctor chargé pour initialiser l'entité
     *
     * @param entity
     */
    public AbstractFacade(Class<T> entity) {
        this.entity = entity;
    }

    /*___________________méthodes_____________________*/
    // méthodes abstraites
    /**
     * permet de récupérer une EntityManager on reposrte l'initialisation de
     * l'EM a la classe concrète
     */
    protected abstract EntityManager getEntityManager();

    // méthodes concrètes
    /**
     * ajout d'une entité dans la BDD
     *
     * @param t
     */
    public void add(T t) {
        getEntityManager().persist(t);

    }

    public void update(T t) {
        getEntityManager().merge(t);
    }

    public void delete(T t) {
        getEntityManager().remove(t);
    }

    /**
     * recup d'une entité via son id (PK) dans la bdd
     *
     * @param id : PK de l'entité
     * @return
     */
    public T findById(int id) {
        return getEntityManager().find(entity, id);
    }

    /**
     * recup de la liste des entités dans la bdd inmplementation avec l'API
     * Criteria
     *
     * @return
     */
    public List<T> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root from = cq.from(entity);
        CriteriaQuery select = cq.select(from);
        return getEntityManager().createQuery(select).getResultList();
    }

}
