package Obras;

public class Serie extends Obra {
    private String diretor = "";
    private String produtora = "";
    private int episodios;

    public Serie(String titulo, int episodios){
        super(titulo);
        this.episodios = episodios;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }
}
