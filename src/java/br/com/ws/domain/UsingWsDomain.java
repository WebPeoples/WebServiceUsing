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
public class UsingWsDomain {
    
    // TABELA: especialidade_sub_cat
        private String cd_especialidade_sub_cat;
        private String nm_especialidade_sub_cat;
        
        private String icon_logo_maps;
        private double latitude_cli, longitu_cli;
        private String nr_cpf_cnpj;

        public String getIcon_logo_maps() {
            return icon_logo_maps;
        }
        public void setIcon_logo_maps(String icon_logo_maps) {
            this.icon_logo_maps = icon_logo_maps;
        }

        public double getLatitude_cli() {
            return latitude_cli;
        }
        public void setLatitude_cli(double latitude_cli) {
            this.latitude_cli = latitude_cli;
        }

        public double getLongitu_cli() {
            return longitu_cli;
        }
        public void setLongitu_cli(double longitu_cli) {
            this.longitu_cli = longitu_cli;
        }

        public String getNr_cpf_cnpj() {
            return nr_cpf_cnpj;
        }
        public void setNr_cpf_cnpj(String nr_cpf_cnpj) {
            this.nr_cpf_cnpj = nr_cpf_cnpj;
        }

        public String getCd_especialidade_sub_cat() {
            return cd_especialidade_sub_cat;
        }
        public void setCd_especialidade_sub_cat(String cd_especialidade_sub_cat) {
            this.cd_especialidade_sub_cat = cd_especialidade_sub_cat;
        }
        
         public String getNm_especialidade_sub_cat() {
        return nm_especialidade_sub_cat;
    }

    public void setNm_especialidade_sub_cat(String nm_especialidade_sub_cat) {
        this.nm_especialidade_sub_cat = nm_especialidade_sub_cat;
    }
    // ----------------------------------
        
    // TABELA: cliente
        private String nr_telefone1_cli;
        private String nm_email;
        private String cd_cep_cli;
        private String cd_estado_cli;
        private String cd_cidade_cli;
        private String nm_razao_social;
        private String nm_rua;
        private int nr_numero;
        private String link_logotipo_cliente;
        private String link_web;
        private String dias_abertura_cli;

        public String getDias_abertura_cli() {
            return dias_abertura_cli;
        }

        public void setDias_abertura_cli(String dias_abertura_cli) {
            this.dias_abertura_cli = dias_abertura_cli;
        }

        public String getCd_cidade_cli() {
            return cd_cidade_cli;
        }

        public void setCd_cidade_cli(String cd_cidade_cli) {
            this.cd_cidade_cli = cd_cidade_cli;
        }

        public String getLink_web() {
            return link_web;
        }

        public void setLink_web(String link_web) {
            this.link_web = link_web;
        }

        public String getNr_telefone1_cli() {
            return nr_telefone1_cli;
        }

        public void setNr_telefone1_cli(String nr_telefone1_cli) {
            this.nr_telefone1_cli = nr_telefone1_cli;
        }

        public String getNm_email() {
            return nm_email;
        }

        public void setNm_email(String nm_email) {
            this.nm_email = nm_email;
        }

        public String getCd_cep_cli() {
            return cd_cep_cli;
        }

        public void setCd_cep_cli(String cd_cep_cli) {
            this.cd_cep_cli = cd_cep_cli;
        }

        public String getCd_estado_cli() {
            return cd_estado_cli;
        }

        public void setCd_estado_cli(String cd_estado_cli) {
            this.cd_estado_cli = cd_estado_cli;
        }

        public String getNm_razao_social() {
            return nm_razao_social;
        }

        public void setNm_razao_social(String nm_razao_social) {
            this.nm_razao_social = nm_razao_social;
        }

        public String getNm_rua() {
            return nm_rua;
        }

        public void setNm_rua(String nm_rua) {
            this.nm_rua = nm_rua;
        }

        public int getNr_numero() {
            return nr_numero;
        }

        public void setNr_numero(int nr_numero) {
            this.nr_numero = nr_numero;
        }

        public String getLink_logotipo_cliente() {
            return link_logotipo_cliente;
        }

        public void setLink_logotipo_cliente(String link_logotipo_cliente) {
            this.link_logotipo_cliente = link_logotipo_cliente;
        }
    //=====================================

   //TABLE: produto
        private String nm_produto;
        private String cd_link_foto_1;
        private String cd_link_foto_2;
        private String cd_link_foto_3;
        private String cd_link_foto_4;
        private String cd_link_foto_5;    
        private String vl_preco_cli;
        private String if_promocao;
        private String precoStrike;
        private String porcentagemPromo;

    public String getPrecoStrike() {
        return precoStrike;
    }

    public void setPrecoStrike(String precoStrike) {
        this.precoStrike = precoStrike;
    }

    public String getPorcentagemPromo() {
        return porcentagemPromo;
    }

    public void setPorcentagemPromo(String porcetagemPromo) {
        this.porcentagemPromo = porcetagemPromo;
    }

    public String getIf_promocao() {
        return if_promocao;
    }

    public void setIf_promocao(String if_promocao) {
        this.if_promocao = if_promocao;
    }

    public String getVl_preco_cli() {
        return vl_preco_cli;
    }

    public void setVl_preco_cli(String vl_preco_cli) {
        this.vl_preco_cli = vl_preco_cli;
    }
        private String dc_completa_prod;
        

        public String getDc_completa_prod() {
            return dc_completa_prod;
        }

        public void setDc_completa_prod(String dc_completa_prod) {
            this.dc_completa_prod = dc_completa_prod;
        }

        public String getCd_link_foto_2() {
            return cd_link_foto_2;
        }

        public void setCd_link_foto_2(String cd_link_foto_2) {
            this.cd_link_foto_2 = cd_link_foto_2;
        }

        public String getCd_link_foto_3() {
            return cd_link_foto_3;
        }

        public void setCd_link_foto_3(String cd_link_foto_3) {
            this.cd_link_foto_3 = cd_link_foto_3;
        }

        public String getCd_link_foto_4() {
            return cd_link_foto_4;
        }

        public void setCd_link_foto_4(String cd_link_foto_4) {
            this.cd_link_foto_4 = cd_link_foto_4;
        }

        public String getCd_link_foto_5() {
            return cd_link_foto_5;
        }

        public void setCd_link_foto_5(String cd_link_foto_5) {
            this.cd_link_foto_5 = cd_link_foto_5;
        }
        
        public String getNm_produto() {
            return nm_produto;
        }

        public void setNm_produto(String nm_produto) {
            this.nm_produto = nm_produto;
        }

        public String getCd_link_foto_1() {
            return cd_link_foto_1;
        }

        public void setCd_link_foto_1(String cd_link_foto_1) {
            this.cd_link_foto_1 = cd_link_foto_1;
        }

       

       
    
    //======================
        
    
    
    private String cnpj;
    private String razao_social;
    private double distanceInKm;

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }
    
    /* CADASTRO usuário */
    
    
}
