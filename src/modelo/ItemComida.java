package modelo;

import java.io.Serializable;

/**
 * REQUISITO 1: Programa estruturado em classes (Encapsulamento)
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 9: Classe que participa de relação de associação (é usada pelo Inventario)
 * REQUISITO 10: Objetos desta classe são armazenados em coleções
 */
public class ItemComida implements Serializable {
    // REQUISITO 5: Mais atributos do programa
    private String nome; // Atributo 13
    private int preco; // Atributo 14
    private int efeitoFome; // Atributo 15
    private int efeitoSede; // Atributo 16
    private int efeitoFelicidade; // Atributo 17
    private int efeitoSaude; // Atributo 18
    private String descricao; // Atributo 19
    
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
