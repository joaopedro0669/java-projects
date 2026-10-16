package Rastreios;

import Obras.Obra;

public class RastreioAvancavel extends Rastreio implements Avancavel {
    int episodios = 0;

    public RastreioAvancavel(Obra obra){
        super(obra);
    }

    @Override
    public void avancar(int episodios) {
        this.episodios += episodios;
    }
    
    @Override
    public void setEpisodio(int episodio) {
        this.episodios = episodio;
        
    }
}
