package Obras;

public class Filme extends Obra {
    private String diretor = "";
    private String produtora = "";
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