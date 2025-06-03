package modelo;

import java.util.Scanner;

public class Loja {
    
    public static void abrirLoja(CriaturaVirtual criatura, Scanner scanner) {
        boolean continuarNaLoja = true;
        
        while (continuarNaLoja && criatura.isVivo()) {
            System.out.println("\n=== LOJA DO TAMAGOCHI ===");
            System.out.println("Seus pontos: " + criatura.getPontos());
            System.out.println("\nItens disponíveis:");
            System.out.println("1. Comida Premium (20 pontos) - Reduz fome em 40 e aumenta energia em 10");
            System.out.println("2. Remédio (30 pontos) - Cura doenças e aumenta saúde em 20");
            System.out.println("3. Brinquedo Especial (25 pontos) - Aumenta felicidade em 30");
            System.out.println("4. Vitamina (15 pontos) - Aumenta energia em 25");
            System.out.println("5. Shampoo Premium (10 pontos) - Higiene máxima + felicidade");
            System.out.println("6. Poção do Amor (50 pontos) - Aumenta afeto em 20");
            System.out.println("0. Sair da loja");
            
            System.out.print("\nEscolha um item (0-6): ");
            
            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                
                switch (escolha) {
                    case 1:
                        comprarComidaPremium(criatura);
                        break;
                    case 2:
                        comprarRemedio(criatura);
                        break;
                    case 3:
                        comprarBrinquedoEspecial(criatura);
                        break;
                    case 4:
                        comprarVitamina(criatura);
                        break;
                    case 5:
                        comprarShampooPremium(criatura);
                        break;
                    case 6:
                        comprarPocaoAmor(criatura);
                        break;
                    case 0:
                        continuarNaLoja = false;
                        System.out.println("Saindo da loja...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }    private static void comprarComidaPremium(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(20)) {
            criatura.reduzirFome(40);
            criatura.adicionarEnergia(10);
            System.out.println(criatura.getNome() + " comeu comida premium! Que delícia!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 20 pontos.");
        }
    }
    
    private static void comprarRemedio(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(30)) {
            criatura.curarDoenca();
            criatura.adicionarSaude(20);
            System.out.println(criatura.getNome() + " tomou remédio e está se sentindo melhor!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 30 pontos.");
        }
    }
    
    private static void comprarBrinquedoEspecial(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(25)) {
            criatura.adicionarFelicidade(30);
            criatura.adicionarAfeto(5);
            System.out.println(criatura.getNome() + " adorou o brinquedo especial!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 25 pontos.");
        }
    }
    
    private static void comprarVitamina(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(15)) {
            criatura.adicionarEnergia(25);
            System.out.println(criatura.getNome() + " tomou vitamina e está cheio de energia!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 15 pontos.");
        }
    }
    
    private static void comprarShampooPremium(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(10)) {
            criatura.limparTotalmente();
            criatura.adicionarFelicidade(15);
            System.out.println(criatura.getNome() + " tomou um banho premium e está radiante!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 10 pontos.");
        }
    }
    
    private static void comprarPocaoAmor(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(50)) {
            criatura.adicionarAfeto(20);
            criatura.adicionarFelicidade(10);
            System.out.println(criatura.getNome() + " bebeu a poção do amor e está te amando ainda mais!");
        } else {
            System.out.println("Pontos insuficientes! Você precisa de 50 pontos.");
        }
    }
}
