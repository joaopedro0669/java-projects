package Obras;

public class Filme extends Obra {
    private long duracaoMinutos;
    public Filme(String titulo, long duracaoMinutos){
        super(titulo);
        this.duracaoMinutos = duracaoMinutos;
    }
    public long getDuracaoMinutos() {
        return duracaoMinutos;
    }
    public void setDuracaoMinutos(long duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}