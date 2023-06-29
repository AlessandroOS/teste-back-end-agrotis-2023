package com.alessandro.testeagrotis.dto;

import java.time.Instant;

public class RequisicaoDTO {

    private String nome;
    private Instant dataInicial;
    private Instant dataFinal;
    private PropriedadeDTO infosPropriedade;
    private String cnpj;
    private LaboratorioDTO laboratorio;
    private String observacoes;

    public RequisicaoDTO() {
    }

    public RequisicaoDTO(String nome, Instant dataInicial, Instant dataFinal, PropriedadeDTO infosPropriedade, String cnpj, LaboratorioDTO laboratorio, String observacoes) {
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.infosPropriedade = infosPropriedade;
        this.cnpj = cnpj;
        this.laboratorio = laboratorio;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Instant dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Instant getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Instant dataFinal) {
        this.dataFinal = dataFinal;
    }

    public PropriedadeDTO getInfosPropriedade() {
        return infosPropriedade;
    }

    public void setInfosPropriedade(PropriedadeDTO infosPropriedade) {
        this.infosPropriedade = infosPropriedade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
