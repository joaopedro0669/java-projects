package Obras;

import java.util.ArrayList;

public class Obra {
    private long id;
    private static long lastID = 0;
    private String titulo;
    private long somaNotas = 0;
    private long totalAvaliadores = 0;
    private String descricao = "";
    private ArrayList<String> generos = new ArrayList<>();

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
        if (totalAvaliadores != 0)
            return somaNotas / totalAvaliadores;
        else
            return 0.0;
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
