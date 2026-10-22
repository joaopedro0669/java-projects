package Usuarios;

import java.util.LinkedList;
import Obras.*;
import Rastreios.Rastreio;
import Rastreios.RastreioAvancavel;
import Rastreios.RastreioProgressivo;

public class Usuario {
    private String nome;
    private String senha;
    private boolean loggedIn = false;
    private LinkedList<Rastreio> obrasConsumidas;

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    public void setNome(String nome){
        if(loggedIn){
            this.nome = nome;
        }
    }

    public String getNome(){
        return nome;
    }

    public void setSenha(String senhaAntiga, String senhaNova) {
        if(senhaAntiga.equals(senha) && loggedIn){
            this.senha = senhaNova;
        }
    }

    public void login(String nome, String senha){
        if(nome.equalsIgnoreCase(this.nome) && senha.equals(this.senha)){
            loggedIn = true;
        }
    }

    public void logout(){
        loggedIn = false;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void adicionarObra(Obra obra){
        boolean add = true;
        for(Rastreio o : obrasConsumidas){
            if (o.getObra().equals(obra)){
                add = false;
                break; 
            }
        }
        if(add){
            if(obra instanceof Filme){
                obrasConsumidas.add(new Rastreio(obra));
            }
            else if(obra instanceof Serie){
                obrasConsumidas.add(new RastreioAvancavel(obra));
            }
            else if(obra instanceof Jogo){
                obrasConsumidas.add(new RastreioProgressivo(obra));
            }
        }
    }
}
