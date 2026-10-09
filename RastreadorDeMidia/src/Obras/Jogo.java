package Obras;

public class Jogo extends Obra {
    private long somaDuracao;
    private long totalJogadores;
    
    public Jogo(String titulo){
        super(titulo);
    }

    public long getDuracaoMedia() {
        return somaDuracao / totalJogadores;
    }

    public void adicionarJogador(long duracao){
        totalJogadores++;
        somaDuracao += duracao;
    }

    public void adicionarDuracao(long ganhoDuracao){
        somaDuracao += ganhoDuracao;
    }
}
