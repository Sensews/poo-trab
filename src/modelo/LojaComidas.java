package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaComidas {
    private List<ItemComida> comidasDisponiveis;
    
    public LojaComidas() {
        inicializarComidas();
    }
    
    private void inicializarComidas() {
        comidasDisponiveis = new ArrayList<>();
        
        // Comidas básicas
        comidasDisponiveis.add(new ItemComida("Ração Comum", 15, 5, 0, 2, 0, 
            "Ração básica que mata a fome"));
        comidasDisponiveis.add(new ItemComida("Água Fresca", 5, 0, 40, 0, 3, 
            "Água limpa e refrescante"));
        comidasDisponiveis.add(new ItemComida("Biscoito", 10, 10, 0, 8, 0, 
            "Biscoito crocante que traz alegria"));
            
        // Comidas premium
        comidasDisponiveis.add(new ItemComida("Ração Premium", 25, 25, 5, 15, 5, 
            "Ração de alta qualidade com vitaminas"));
        comidasDisponiveis.add(new ItemComida("Petisco Gourmet", 30, 15, 0, 25, 10, 
            "Petisco especial que deixa muito feliz"));
        comidasDisponiveis.add(new ItemComida("Salmão Fresco", 40, 30, 10, 20, 15, 
            "Salmão nutritivo e delicioso"));
            
        // Comidas especiais/medicinais
        comidasDisponiveis.add(new ItemComida("Vitamina Especial", 50, 5, 5, 10, 40, 
            "Suplemento que restaura a saúde"));
        comidasDisponiveis.add(new ItemComida("Remédio Natural", 35, 0, 0, 5, 50, 
            "Ervas medicinais que curam doenças"));
        comidasDisponiveis.add(new ItemComida("Energético", 45, 20, 20, 30, 20, 
            "Bebida energética que revitaliza"));
            
        // Comidas raras
        comidasDisponiveis.add(new ItemComida("Trufa Especial", 60, 25, 0, 40, 25, 
            "Iguaria rara que proporciona extrema felicidade"));
        comidasDisponiveis.add(new ItemComida("Elixir da Vida", 80, 15, 15, 35, 60, 
            "Poção mágica que restaura completamente a saúde"));
        comidasDisponiveis.add(new ItemComida("Banquete Real", 100, 40, 25, 50, 30, 
            "Refeição completa digna de realeza"));
    }
    
    public void mostrarLoja() {
        System.out.println("\n🍽️  === LOJA DE COMIDAS === 🍽️");
        System.out.println("Escolha uma comida para comprar:\n");
        
        for (int i = 0; i < comidasDisponiveis.size(); i++) {
            ItemComida comida = comidasDisponiveis.get(i);
            System.out.printf("%d. %s - %d pontos\n", (i + 1), comida.getNome(), comida.getPreco());
            System.out.printf("   %s\n", comida.getDescricao());
            System.out.printf("   Efeitos: Fome +%d, Sede +%d, Felicidade +%d, Saúde +%d\n", 
                comida.getEfeitoFome(), comida.getEfeitoSede(), 
                comida.getEfeitoFelicidade(), comida.getEfeitoSaude());
            System.out.println();
        }
        System.out.println("0. Voltar ao menu principal");
    }
    
    public void abrirLoja(CriaturaVirtual criatura, Scanner scanner) {
        while (true) {
            System.out.println("\n💰 Seus pontos: " + criatura.getPontos());
            System.out.println("🎒 Comidas no inventário: " + criatura.getInventario().getQuantidadeTotal());
            mostrarLoja();
            
            System.out.print("Escolha uma opção: ");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                
                if (escolha == 0) {
                    System.out.println("Saindo da loja de comidas...");
                    break;
                }
                
                if (escolha >= 1 && escolha <= comidasDisponiveis.size()) {
                    ItemComida comidaSelecionada = comidasDisponiveis.get(escolha - 1);
                    comprarComida(criatura, comidaSelecionada, scanner);
                } else {
                    System.out.println("Opção inválida!");
                }
                
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // limpar buffer
            }
        }
    }
    
    private void comprarComida(CriaturaVirtual criatura, ItemComida comida, Scanner scanner) {
        System.out.println("\n--- COMPRAR " + comida.getNome().toUpperCase() + " ---");
        System.out.println("Preço: " + comida.getPreco() + " pontos");
        System.out.println("Seus pontos: " + criatura.getPontos());
        
        if (criatura.getPontos() < comida.getPreco()) {
            System.out.println("❌ Você não tem pontos suficientes!");
            System.out.println("Jogue o minigame para ganhar mais pontos.");
            return;
        }
        
        System.out.print("Quantas unidades deseja comprar? (0 para cancelar): ");
        try {
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            if (quantidade == 0) {
                System.out.println("Compra cancelada.");
                return;
            }
            
            if (quantidade < 0) {
                System.out.println("Quantidade inválida!");
                return;
            }
            
            int custoTotal = comida.getPreco() * quantidade;
            
            if (criatura.getPontos() < custoTotal) {
                System.out.println("❌ Você não tem pontos suficientes para comprar " + quantidade + " unidades!");
                System.out.printf("Custo total: %d pontos (você tem: %d pontos)\n", custoTotal, criatura.getPontos());
                return;
            }
            
            // Confirmar compra
            System.out.printf("Confirmar compra de %d x %s por %d pontos? (s/n): ", 
                quantidade, comida.getNome(), custoTotal);
            String confirmacao = scanner.nextLine().toLowerCase();
            
            if (confirmacao.equals("s") || confirmacao.equals("sim")) {
                // Realizar compra
                criatura.gastarPontos(custoTotal);
                
                for (int i = 0; i < quantidade; i++) {
                    criatura.getInventario().adicionarComida(new ItemComida(
                        comida.getNome(), comida.getPreco(), comida.getEfeitoFome(),
                        comida.getEfeitoSede(), comida.getEfeitoFelicidade(), comida.getEfeitoSaude(),
                        comida.getDescricao()
                    ));
                }
                
                System.out.println("✅ Compra realizada com sucesso!");
                System.out.printf("Você comprou %d x %s\n", quantidade, comida.getNome());
                System.out.println("Pontos restantes: " + criatura.getPontos());
                System.out.println("As comidas foram adicionadas ao seu inventário!");
                
            } else {
                System.out.println("Compra cancelada.");
            }
            
        } catch (Exception e) {
            System.out.println("Entrada inválida! Digite um número.");
            scanner.nextLine(); // limpar buffer
        }
    }
    
    // Método para obter comida específica (usado por outras classes)
    public ItemComida getComida(int indice) {
        if (indice >= 0 && indice < comidasDisponiveis.size()) {
            return comidasDisponiveis.get(indice);
        }
        return null;
    }
    
    public int getQuantidadeComidas() {
        return comidasDisponiveis.size();
    }
    
    // Método para adicionar novas comidas (se necessário)
    public void adicionarComida(ItemComida comida) {
        comidasDisponiveis.add(comida);
    }
}
