package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * REQUISITO 1: Programa estruturado em classes (Encapsulamento)
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 9: Classe que participa de relação de associação com CriaturaVirtual
 * REQUISITO 10: Contém coleção de objetos (ArrayList)
 */
public class Inventario implements Serializable {
    // REQUISITO 10: Coleção de objetos - ArrayList de ItemComida
    private List<ItemComida> comidas; // Atributo que é uma coleção
    
    public Inventario() {
        comidas = new ArrayList<>(); // Inicialização da coleção
    }
    
    public void adicionarComida(ItemComida comida) {
        comidas.add(comida); // Uso da coleção
    }
    
    public boolean temComidas() {
        return !comidas.isEmpty(); // Operação na coleção
    }
    
    public void mostrarComidas() {
        System.out.println("\n=== SEU INVENTÁRIO DE COMIDAS ===");
        if (comidas.isEmpty()) {
            System.out.println("Inventário vazio! Você precisa comprar comida na loja.");
        } else {
            for (int i = 0; i < comidas.size(); i++) {
                ItemComida comida = comidas.get(i);
                System.out.printf("%d. %s - %s\n", (i + 1), comida.getNome(), comida.getDescricao());
                System.out.printf("   Efeitos: Fome +%d, Sede +%d, Felicidade +%d, Saúde +%d\n", 
                    comida.getEfeitoFome(), comida.getEfeitoSede(), 
                    comida.getEfeitoFelicidade(), comida.getEfeitoSaude());
            }
        }
    }
    
    public ItemComida usarComida(int indice) {
        if (indice >= 1 && indice <= comidas.size()) {
            return comidas.remove(indice - 1);
        }
        return null;
    }
    public void removerComida(ItemComida comida) {
        comidas.remove(comida);
    }
    
    public int getQuantidadeTotal() {
        return comidas.size();
    }
    
    public List<ItemComida> getComidas() {
        return new ArrayList<>(comidas);
    }
}
