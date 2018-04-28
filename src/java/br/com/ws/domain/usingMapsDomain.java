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
public class usingMapsDomain {    
     private String nome;
     private String icone;
     private String cod_busca;
     private double lat;
     private double longi;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getCod_busca() {
        return cod_busca;
    }

    public void setCod_busca(String cod_busca) {
        this.cod_busca = cod_busca;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
