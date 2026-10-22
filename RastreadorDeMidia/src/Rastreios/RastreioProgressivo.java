package Rastreios;

import Obras.Obra;

public class RastreioProgressivo extends Rastreio implements Progressivo {
    private int minutosJogados = 0;

    public RastreioProgressivo(Obra obra) {
        super(obra);
    }

    public int getMinutosJogados() {
        return minutosJogados;
    }

    @Override
    public void progredir(int minutos) {
        minutosJogados += minutos;
    }

    @Override
    public void setMinutosJogados(int minutos) {
        minutosJogados = minutos;
    }
}
