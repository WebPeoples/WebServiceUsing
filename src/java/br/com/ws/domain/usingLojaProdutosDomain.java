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
public class usingLojaProdutosDomain {
    
    private String path;
    private String cod_produtos;
    private double preco;
    private String nome;
    private String desc_produto;

    public String getDesc_produto() {
        return desc_produto;
    }

    public void setDesc_produto(String desc_produto) {
        this.desc_produto = desc_produto;
    }
    
    
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCod_produtos() {
        return cod_produtos;
    }

    public void setCod_produtos(String cod_produtos) {
        this.cod_produtos = cod_produtos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
