package app.livro;

import core.util.CrudId;

public class Livro implements CrudId<Long>, Cloneable {
    Long id;
    String nome;
    Double volume;
    Double quantidadeT;
    Double quantidadeD;
    String editora;
    Integer corredor;
    Integer prateleira;
    Integer estante;

    public Livro(Long id, String nome, Double volume, Double quantidadeT,  String editora, Integer corredor, Integer prateleira, Integer estante) {
        this.id = id;
        this.nome = nome;
        this.volume = volume;
        this.quantidadeT = quantidadeT;
        this.quantidadeD = quantidadeT;
        this.editora = editora;
        this.corredor = corredor;
        this.prateleira = prateleira;
        this.estante = estante;
    }

    public Livro clone() throws CloneNotSupportedException {

        return (Livro) super.clone();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getVolume() {
        return volume;
    }


    public String getEditora() {
        return editora;
    }

    public Integer getCorredor() {
        return corredor;
    }

    public Integer getPrateleira() {
        return prateleira;
    }

    public Integer getEstante() {
        return estante;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }


    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCorredor(Integer corredor) {
        this.corredor = corredor;
    }

    public void setPrateleira(Integer prateleira) {
        this.prateleira = prateleira;
    }

    public void setEstante(Integer estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", volume=" + volume +
                ", quantidadeT=" + quantidadeT +
                ", quantidadeD=" + quantidadeD +
                ", editora='" + editora + '\'' +
                ", corredor=" + corredor +
                ", prateleira=" + prateleira +
                ", estante=" + estante +
                '}';
    }

    public Double getQuantidadeT() {
        return quantidadeT;
    }

    public void setQuantidadeT(Double quantidadeT) {
        this.quantidadeT = quantidadeT;
    }

    public Double getQuantidadeD() {
        return quantidadeD;
    }

    public void setQuantidadeD(Double quantidadeD) {
        this.quantidadeD = quantidadeD;
    }
}
