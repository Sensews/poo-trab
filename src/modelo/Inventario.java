package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventario implements Serializable {
    private List<ItemComida> comidas;
    
    public Inventario() {
        comidas = new ArrayList<>();
    }
    
    public void adicionarComida(ItemComida comida) {
        comidas.add(comida);
    }
    
    public boolean temComidas() {
        return !comidas.isEmpty();
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
