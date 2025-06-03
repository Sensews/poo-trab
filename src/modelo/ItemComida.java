package modelo;

import java.io.Serializable;

public class ItemComida implements Serializable {
    private String nome;
    private int preco;
    private int efeitoFome;
    private int efeitoSede;
    private int efeitoFelicidade;
    private int efeitoSaude;
    private String descricao;
    
    public ItemComida(String nome, int preco, int efeitoFome, int efeitoSede, int efeitoFelicidade, int efeitoSaude, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.efeitoFome = efeitoFome;
        this.efeitoSede = efeitoSede;
        this.efeitoFelicidade = efeitoFelicidade;
        this.efeitoSaude = efeitoSaude;
        this.descricao = descricao;
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getEfeitoFome() { return efeitoFome; }
    public int getEfeitoSede() { return efeitoSede; }
    public int getEfeitoFelicidade() { return efeitoFelicidade; }
    public int getEfeitoSaude() { return efeitoSaude; }
    public String getDescricao() { return descricao; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (Preço: %d pontos)", nome, descricao, preco);
    }
}
