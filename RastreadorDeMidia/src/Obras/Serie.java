package Obras;

public class Serie extends Obra {
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
}
