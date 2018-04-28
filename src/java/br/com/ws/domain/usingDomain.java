/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.domain;

/**
 *
 * @author Web People
 */
public class usingDomain {
    
    
    private String foto;
    
    private String cod_cat;
    
    private String cod_busca;

    public String getCod_busca() {
        return cod_busca;
    }

    public void setCod_busca(String cod_busca) {
        this.cod_busca = cod_busca;
    }

    public String getCod_cat() {
        return cod_cat;
    }

    public void setCod_cat(String cod_cat) {
        this.cod_cat = cod_cat;
    }    

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
}
