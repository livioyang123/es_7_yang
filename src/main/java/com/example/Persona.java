package com.example;

public class Persona {
    String nome;
    String cognome;
    int eta;

    public Persona(String nome,String cognome,int eta){
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }
    public Persona(){

    }
    public String getCognome() {
        return cognome;
    }
    public int getEta() {
        return eta;
    }
    public String getNome() {
        return nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "nome:"+this.getNome()+",cognome:"+this.getCognome()+",eta:"+this.getEta();
    }
}
