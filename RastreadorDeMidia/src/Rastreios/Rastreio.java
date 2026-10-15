package Rastreios;

import Obras.Obra;

public class Rastreio {
    private Obra obra;
    private int avaliacao = -1;
    
    public Rastreio(Obra obra){
        this.obra = obra;
    }

    public void setAvaliacao(int avaliacao) {
        if(this.avaliacao == -1){
            obra.adicionarAvaliacao(avaliacao);
        }
        else{
            obra.editarAvalicao(this.avaliacao - avaliacao);
        }
        this.avaliacao = avaliacao;
    }
}
