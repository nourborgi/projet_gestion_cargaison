/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.webservice;

import com.intiformation.gestioncargaison.entity.Marchandises;
import com.intiformation.gestioncargaison.facade.MarchandisesFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author IN-MP-007
 */
@Stateless // déclaration du ws rest comme EJB 
@Path("marchandises")// annotation JAX_RS. permet de définir l'URL d'accès à la ressource
public class MarchandisesRestService {

    // décalaration de la façade 
    @EJB // instanciation de la façade par le serveur 
    private MarchandisesFacade marchandisesFacade;

    /*===================== Méthodes à exposer dans le ws ====================*/
    /**
     * recup de la liste des marchandises de la bdd et les exposer dans le ws
     * <br/>
     *
     * @return
     */
    @GET
    @Path("liste")
    @Produces("application/json")// type MIME retourné par la méthode 
    public List<Marchandises> getAllMarchandise() {

        return marchandisesFacade.findAll();

    }// fin getAllMarchandises

    /**
     * recup d'une marchandise via son id <br/>
     *
     * @param pMarchandiseID
     * @return
     */
    @GET
    @Path("getbyid/{idMarchandise:[0-9]+}")
    @Produces("application/json")
    public Marchandises getMarchandiseById(@PathParam("idMarchandise") int pMarchandiseID) {

        return marchandisesFacade.findById(pMarchandiseID);

    }// fin getMarchandiseById

    /**
     * ajout d'une marchandise dans la bdd via le ws <br/>
     *
     * @param pMarchandise
     * @return
     */
    @POST
    @Path("ajouter")
    @Consumes({"application/xml", "application/json"}) // type MIME envoyé par le client WS 
    @Produces("application/json")
    public Response ajouterMarchandise(Marchandises pMarchandise) {

        // 1. verif de la marchandise à ajouter 
        if (pMarchandise == null) {

            // renvoi d'un message dans la réponse 
            return Response.status(400)
                    .entity("Veuillez ajouter les détails de la marchandise")
                    .build();
        }
if (pMarchandise.getNom() == null) {
            //renvoi d'un message dans la réponse
            return Response.status(400).entity("Veuillez indiquez le nom de l'étudiant").build();
        }
        // 2. ajout de la marchandise 
        marchandisesFacade.add(pMarchandise);

        // 3. renvoi de la réponse avec un message + status 200 
        Response reponse = Response.status(200)
                .entity("Marchandise Ajouté avec succès")
                .build();

        return reponse;

    }// fin ajouterMarchandise

    /**
     * permet de modifier une marchandise dans la bdd <br/>
     *
     * @param marchandiseID
     * @param pMarchandise
     * @return
     */
    @PUT // requete HTTP en PUT 
    @Path("modifier/{idMarchandise}")
    @Consumes({"application/xml", "application/json"}) // type MIME envoyé par le client WS 
    @Produces({"application/xml", "application/json"})
    public Response modifierMarchandise(@PathParam("idMarchandise") int marchandiseID,
            Marchandises pMarchandise) {

        // 1. verif de la marchandise à modifier  
        if (pMarchandise == null) {

            // renvoi d'un message dans la réponse 
            return Response.status(400)
                    .entity("Veuillez ajouter les détails de la marchandise")
                    .build();
        }

        // 2. Modif de la marchandise dans la bdd via la façade 
        marchandisesFacade.update(pMarchandise);

        // 3. renvoi de la réponse : statu 200 + la marchandise modifiée 
        return Response.ok()
                .entity(marchandisesFacade.findById(marchandiseID))
                .build();

    }// fin modifierMarchandise

    /**
     * suppression d'une marchandise dans la bdd <br/>
     * méthode appelée avec une requête HTTP DELETE <br/>
     *
     * @param pMarchandiseID
     * @return
     */
    @DELETE
    @Path("supprimer/{idMarchandise:[0-9]+}")
    @Produces("text/plain") // type MIME renvoyé au client : text/plain =  texte brut 
    public Response supprimerMarchandises(@PathParam("idMarchandise") int pMarchandiseID) {

        // 1. suppression via la façade 
        marchandisesFacade.delete(marchandisesFacade.findById(pMarchandiseID));

        // 2. renvoi de la réponse au client ws 
        return Response.status(202)
                .entity("Marchandise supprimé avec succès")
                .build();
    }
}
