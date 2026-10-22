package Rastreios;

import Obras.Obra;

public class Rastreio {
    private Obra obra;
    private int avaliacao = -1;
    private Status status = Status.NAO_ASSISTIDO;
    
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Obra getObra() {
        return obra;
    }

    public Status getStatus() {
        return status;
    }
}
