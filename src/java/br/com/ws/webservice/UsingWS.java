/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.webservice;

import br.com.ws.dao.UsingWsDao_rating;
import br.com.ws.dao.UsingWsDao_favorito;
import br.com.ws.dao.UsingWsDao_lojas;
import br.com.ws.dao.UsingWsDao_maps;
import br.com.ws.dao.UsingWsDao_promocoes;
import br.com.ws.dao.UsingWsDao_user;
import br.com.ws.domain.UsingWsDomain;
import com.google.gson.Gson;
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
import org.json.JSONArray;

/**
 *
 * @author Web People
 */
@Path("usingws")
public class UsingWS {
    
      @Context
    private UriInfo context;

    /**
     * Creates a new instance of get
     */
    public UsingWS() {
    }
    
    // --- TOKEN FOR NOTIFICATION --
    @POST
    @Path("registerToken")
    @Produces(MediaType.APPLICATION_JSON)
    public String regiterToken (String token){
        
        return new UsingWsDao_user().regiterToken(token);
    }
    
    
    // -----USING DAO MAPS------    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getValuesToMap(String subcat) throws SQLException {
        //TODO return proper representation object
     
        ArrayList<UsingWsDomain> lista;
        UsingWsDao_maps dao = new UsingWsDao_maps();
        JSONArray js = new JSONArray(subcat);
        
        lista = dao.getValuesToMap(subcat);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    @POST
    @Path("nomap")
    @Produces(MediaType.APPLICATION_JSON)
    public String PesquisaSemMapa(String subcat) throws SQLException {
        //TODO return proper representation object
     
        ArrayList<UsingWsDomain> lista;
        UsingWsDao_maps dao = new UsingWsDao_maps();
        JSONArray js = new JSONArray(subcat);
        
        lista = dao.PesquisaSemMapa(subcat);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
   
    @GET
    @Path("lojaslocalac")
    @Produces(MediaType.APPLICATION_JSON)
    public String getValuesToMapLocalActivity() throws SQLException {
        //TODO return proper representation object
     
        ArrayList<UsingWsDomain> lista;
        UsingWsDao_maps dao = new UsingWsDao_maps();
        
        lista = dao.getValuesToMapLocalActivity();
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    
     @GET
    @Path("especialidades/{subcat}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEspecialidades(@PathParam("subcat") String subcat) throws SQLException {
        //TODO return proper representation object
     
        List<UsingWsDomain> lista;
        UsingWsDao_maps dao = new UsingWsDao_maps();
        
        lista = dao.getEspecialidades(subcat);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
   
    
    
    // ===== END OF USING DAO MAPS =========
    
    
    
    
    
    
    
    
    
    
    
    // ---------- USING DAO LOJAS ----------------
    @POST
    @Path("informacoes_loja/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getInfoLojas(String subcat) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getInfoLojas(subcat);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    @POST
    @Path("info_filial")
    @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
    public String getInfoFilial(String cnpj) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getInfoFilial(cnpj);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }     
    @POST
    @Path("lista_produto")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaProduto(String cpf_cnpj) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getListaProduto(cpf_cnpj);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    @POST
    @Path("filial_lista_produto")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFilialListaProduto(String cpf_cnpj) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getFilialListaProduto(cpf_cnpj);
        Gson g = new Gson();
        System.out.println(g.toJson(lista));
        return g.toJson(lista);
    }
    @POST
    @Path("getlista_local_filial")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getLocalListaProdutoFilial(String cpf_cnpj) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getLocalListaProdutoFilial(cpf_cnpj);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
     @POST
    @Path("getlista_local")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLocalListaProduto(String cpf_cnpj) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_lojas dao = new UsingWsDao_lojas();
        
        lista = dao.getLocalListaProduto(cpf_cnpj);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    // ========== END OF USING DAO LOJAS ==========
    
    
    
    
    
    
    
    
    // --------- USING DAO RATING ------------
    @GET
    @Path("fotos_produtos/{cd_link_foto_1}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFotosProdutos(@PathParam("cd_link_foto_1") String cd_link_foto_1) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_rating dao = new UsingWsDao_rating();
        
        lista = dao.getFotoProdutoActivity(cd_link_foto_1);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }  
    @POST
    @Path("rating")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRating(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_rating dao = new UsingWsDao_rating();       
        Gson g = new Gson();
        return g.toJson(dao.getRating(dataUser));
    }   
    @POST
    @Path("setrating")
    @Produces(MediaType.APPLICATION_JSON)
    public String setRating(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_rating dao = new UsingWsDao_rating();       
        Gson g = new Gson();
        return g.toJson(dao.setRating(dataUser));
    }

// ============ END OF USING DAO RATING ==============
    
    
    
    
    
    
    
    // ========= USING DAO PROMOCOES ============
    
    @GET
    @Path("promo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromoList(String cd_link_foto_1) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_promocoes dao = new UsingWsDao_promocoes();
        
        lista = dao.getPromoList("S");
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    @GET
    @Path("promo_fil")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromoFilialList(String cd_link_foto_1) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_promocoes dao = new UsingWsDao_promocoes();
        
        lista = dao.getPromoListFilial("S");
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
     @GET
    @Path("promo/{cat}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromoListFilter(@PathParam("cat")String categoria) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_promocoes dao = new UsingWsDao_promocoes();
        
        lista = dao.getPromoListFilter(categoria);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    @GET
    @Path("promo_fil/{cat}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPromoListFilterFilial(@PathParam("cat")String categoria) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_promocoes dao = new UsingWsDao_promocoes();
        
        lista = dao.getPromoListFilterFilial(categoria);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    @POST
    @Path("espec_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLojasFromEspec(String categoria) throws SQLException {
        //TODO return proper representation object
        List<UsingWsDomain> lista;
        UsingWsDao_maps dao = new UsingWsDao_maps();
        
        lista = dao.getLojasByEspec(categoria);
        Gson g = new Gson();
        
        return g.toJson(lista);
    }
    
    // ======= END OF USING DAO PROMOCOES
    
    
    
    
    
    
    
    
    // ------ USING DAO USER ---------
    
    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastroUser(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_user dao = new UsingWsDao_user();       
        return dao.cadastroUser(dataUser);
    }
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_user dao = new UsingWsDao_user();       
        return dao.login(dataUser);
    }
    
    // ========== END OF USING DAO USER ==========
    
    //  ----------- USING DAO FAVORITO ----------
    @POST
    @Path("fav")
    @Produces(MediaType.APPLICATION_JSON)
    public String cliente_favorito(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        return dao.clienteFavorito(dataUser);
    }
     @POST
    @Path("favoritar")
    @Produces(MediaType.APPLICATION_JSON)
    public String favoritar(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        return dao.favoritar(dataUser);
    }
    
    @POST
    @Path("desfavoritar")
    @Produces(MediaType.APPLICATION_JSON)
    public String desfavoritar(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        return dao.deletarFavorito(dataUser);
    }
    
    @GET
    @Path("selectClientesFavoritos/{cd_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selectClienteFavorito(@PathParam("cd_user") String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        Gson g = new Gson();
        return g.toJson(dao.selectClientesFavoritos(dataUser));
    }
    
    @GET
    @Path("selectFiliaisFavoritos/{cd_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selectFilialFavorito(@PathParam("cd_user") String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        Gson g = new Gson();
        return g.toJson(dao.selectFiliaisFavoritos(dataUser));
    }
    
    @POST
    @Path("favFilter")
    @Produces(MediaType.APPLICATION_JSON)
    public String favFilter(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        Gson g = new Gson();
        return g.toJson(dao.favFilter(dataUser));
    }
    @POST
    @Path("favFilialFilter")
    @Produces(MediaType.APPLICATION_JSON)
    public String favFilialFilter(String dataUser) throws SQLException {
        //TODO return proper representation object
        UsingWsDao_favorito dao = new UsingWsDao_favorito();       
        Gson g = new Gson();
        return g.toJson(dao.favFilialFilter(dataUser));
    }
    // ========= END OF USING DAO FAVORITO ===========
    
    
}
