package com.example.italac.model;

public class Fazenda {
    private String nomeFazenda, NomeDono,  qtdAnimais, telefone;

    public Fazenda() {
    }

    public String getNomeFazenda() {
        return nomeFazenda;
    }

    public void setNomeFazenda(String nomeFazenda) {
        this.nomeFazenda = nomeFazenda;
    }

    public String getNomeDono() {
        return NomeDono;
    }

    public void setNomeDono(String nomeDono) {
        NomeDono = nomeDono;
    }

    public String getQtdAnimais() {
        return qtdAnimais;
    }

    public void setQtdAnimais(String qtdAnimais) {
        this.qtdAnimais = qtdAnimais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {return nomeFazenda;}
}
