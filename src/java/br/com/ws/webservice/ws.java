/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.webservice;

import br.com.ws.dao.usingDAO;
import br.com.ws.domain.usingDomain;
import br.com.ws.domain.usingLojaProdutosDomain;
import br.com.ws.domain.usingMapsDomain;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Web People
 */
@Path("generic")
public class ws {
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of get
     */
    public ws() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws SQLException {
        //TODO return proper representation object
     
        ArrayList<usingDomain> lista = null;
        usingDAO dao = new usingDAO();
        
        lista = dao.listar();
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
      @GET
      @Path("lojas")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLojas() throws SQLException {
        //TODO return proper representation object
     
        ArrayList<usingDomain> lista = null;
        usingDAO dao = new usingDAO();
        
        lista = dao.listarLojas();
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    @GET
    @Path("categoria/{cat}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoria(@PathParam("cat") String cat){
        
        System.out.println(cat);
    
     List<usingMapsDomain> lista = null;
        usingDAO dao = new usingDAO();
        
        lista = dao.newGetLojas(cat);
        Gson g = new Gson();
        System.out.println(g.toJson(lista));
        return g.toJson(lista);
    }
    
    
    @GET
    @Path("loja_produto/{cod_produto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getProdutos(@PathParam("cod_produto") String cod_produto){
        
        System.out.println(cod_produto);
    
     List<usingLojaProdutosDomain> lista = null;
        usingDAO dao = new usingDAO();
        
        lista = dao.getProdutos(cod_produto);
        Gson g = new Gson();
        System.out.println(g.toJson(lista));
        return g.toJson(lista);
    }
    
    @GET
    @Path("loja_info/{cod_produto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getLojaInfo(@PathParam("cod_produto") String cod_produto){
        System.out.println(cod_produto);
        List<usingLojaProdutosDomain> lista = null;
        usingDAO dao = new usingDAO();
        
        lista = dao.getLojaInfo(cod_produto);
        Gson g = new Gson();
        System.out.println(g.toJson(lista));
        return g.toJson(lista);
    }
    
     @GET
    @Path("btLocal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String btLocal(){
        List<usingMapsDomain> lista = null;
        usingDAO dao = new usingDAO();
        lista = dao.btLocal();
        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastro(String listaCadastro){
        
        
        
    
        return listaCadastro;    
    }
    
    
    
    
}
