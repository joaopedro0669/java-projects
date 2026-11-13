package Obras;

import Usuarios.Usuario;

public class Jogo extends Obra {
    private String desenvolvedora = "";
    private long somaDuracao;
    private long totalJogadores;
    private Usuario postadoPor = null;
    
    public Jogo(String titulo, Usuario postadoPor){
        super(titulo, postadoPor);
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

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }
}
