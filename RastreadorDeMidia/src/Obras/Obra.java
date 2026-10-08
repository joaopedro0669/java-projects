package Obras;

import java.util.ArrayList;

public class Obra {
    private long id;
    private static long lastID = 0;
    private String titulo;
    private long somaNotas;
    private long totalAvaliadores;
    private String descricao;
    private ArrayList<String> generos;

    public Obra(String titulo){
        this.id = ++lastID;
        this.titulo = titulo;
    }

    public long getID(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public double getNota(){
        return somaNotas / totalAvaliadores;
    }

    public void adicionarAvaliacao(int nota){
        totalAvaliadores++;
        somaNotas += nota;
    }

    public void editarAvalicao(int ganhoNota){
        somaNotas += ganhoNota;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public ArrayList<String> getGeneros(){
        return generos;
    }

    public void setGeneros(ArrayList<String> generos){
        this.generos = generos;
    }

    public void adicionarGenero(String genero){
        generos.add(genero);
    }
}
