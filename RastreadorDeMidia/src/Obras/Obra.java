package Obras;

import java.util.ArrayList;

public abstract class Obra {
    private String nome;
    private int nota;
    private String descricao;
    private ArrayList<String> Generos;

    public abstract String getNome();

    public abstract void setNome(String nome);

    public abstract int getNota();
    
    public abstract void setNota(int nota);

    public abstract String getDescricao();

    public abstract void setDescricao(String descricao);

    public abstract ArrayList<String> getGeneros();

    public abstract void setGeneros(ArrayList<String> generos);
}
