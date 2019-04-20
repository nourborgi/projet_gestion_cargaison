/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncargaison.webservice;

    /* implémentation d'un ws rest pour l'cargaison <br/>
 * @author INTIFORMATION
 */

import com.intiformation.gestioncargaison.entity.Cargaison;
import com.intiformation.gestioncargaison.facade.CargaisonFacade;
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

@Stateless // déclaration du ws rest comme EJB 
@Path("cargaisons")// annotation JAX_RS. permet de définir l'URL d'accès à la ressource
public class CargaisonRestService {
  
    
   
    
    // décalaration de la façade 
    @EJB // instanciation de la façade par le serveur 
    private CargaisonFacade cargaisonFacade;
    
    /*===================== Méthodes à exposer dans le ws ====================*/
    /**
     * recup de la liste des cargaisons de la bdd et les exposer 
     * dans le ws <br/> 
     * @return 
     */
    
    
    
     @GET
    @Path("listeCargaison")
    @Produces("application/json")// type MIME retourné par la méthode 
    public List<Cargaison> getAllCargaisons(){
        
        return cargaisonFacade.findAll();
        
    }// fin getAllCargaisons
    
    /**
     * recup d'un cargaison via son id <br/>
     * @param pCargaisonID
     * @return 
     */
    @GET
    @Path("getbyid/{idCargaison:[0-9]+}")
    @Produces("application/json")
    public Cargaison getCargaisonById(@PathParam("idCargaison") int pCargaisonID){
        
        return cargaisonFacade.findById(pCargaisonID);
        
    }// fin getCargaisonById
    
    /**
     * ajout d'une cargaison dans la bdd via le ws <br/>
     * @param pCargaison
     * @return 
     */
    @POST
    @Path("ajouterCargaison")
    @Consumes({"application/xml","application/json"}) // type MIME envoyé par le client WS 
    @Produces("application/json")
    public Response ajouterCargaison(Cargaison pCargaison){
        
        // 1. verif de la cargaison à ajouter 
        if (pCargaison == null) {
            
            // renvoi d'un message dans la réponse 
            return Response.status(400)
                           .entity("Veuillez ajouter les détails de la cargaison")
                           .build();
        }
        
       
        if (pCargaison.getReference()== null) {
            //renvoi d'un message dans la réponse
            return Response.status(400).entity("Veuillez indiquez le nom de l'étudiant").build();
        }
        
        
        // 2. ajout de la cargaison 
        cargaisonFacade.add(pCargaison);
        
        // 3. renvoi de la réponse avec un message + status 200 
        Response reponse = Response.status(200)
                                   .entity("Cargaison Ajouté avec succès")
                                   .build();
        
        return reponse;
        
    }// fin ajouterCargaison
    
    /**
     * permet de modifier un cargaison dans la bdd <br/>
     * @param cargaisonID
     * @param pCargaison
     * @return 
     */
    @PUT // requete HTTP en PUT 
    @Path("modifier/{idCargaison}")
    @Consumes({"application/xml","application/json"}) // type MIME envoyé par le client WS 
    @Produces({"application/xml","application/json"})
    public Response modifierCargaison(@PathParam("idCargaison") int cargaisonID, 
                                     Cargaison pCargaison){
        
        // 1. verif de la cargaison à modifier  
        if (pCargaison == null) {
            
            // renvoi d'un message dans la réponse 
            return Response.status(400)
                           .entity("Veuillez ajouter les détails de la cargaison")
                           .build();
        }
        
        // 2. Modif de la cargaison dans la bdd via la façade 
        cargaisonFacade.update(pCargaison);
        
        // 3. renvoi de la réponse : statu 200 + la cargaison modifié 
        return Response.ok()
                       .entity(cargaisonFacade.findById(cargaisonID))
                       .build();
        
    }// fin modifierCargaison
    
    /**
     * suppression d'une cargaison dans la bdd <br/>
     * méthode appelée avec une requête HTTP DELETE <br/> 
     * @param pCargaisonID
     * @return 
     */
    @DELETE
    @Path("supprimer/{idCargaison:[0-9]+}")
    @Produces("text/plain") // type MIME renvoyé au client : text/plain =  texte brut 
    public Response supprimerCargaison(@PathParam("idCargaison") int pCargaisonID){
        
        // 1. suppression via la façade 
        cargaisonFacade.delete(cargaisonFacade.findById(pCargaisonID));
        
        // 2. renvoi de la réponse au client ws 
        return Response.status(202)
                       .entity("Cargaison supprimé avec succès")
                       .build();
        
    }// fin supprimerCargaison
    
    
}
