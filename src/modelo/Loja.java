package modelo;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Loja {
    // Mapa para armazenar upgrades permanentes comprados por criatura
    private static Map<CriaturaVirtual, UpgradesPermanentes> upgradesComprados = new HashMap<>();
    
    // Classe interna para gerenciar upgrades permanentes
    private static class UpgradesPermanentes {
        boolean multiplicadorPontos = false;
        boolean eficienciaComida = false;
        boolean resistenciaDoenca = false;
        boolean energiaMaxima = false;
        boolean felicidadeEterna = false;
        boolean superRegeneracao = false;
        boolean masterpet = false;
    }
    
    public static void abrirLoja(CriaturaVirtual criatura, Scanner scanner) {
        boolean continuarNaLoja = true;
        
        // Inicializar upgrades se não existir
        if (!upgradesComprados.containsKey(criatura)) {
            upgradesComprados.put(criatura, new UpgradesPermanentes());
        }
        
        while (continuarNaLoja && criatura.isVivo()) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║        LOJA DE MELHORIAS PREMIUM     ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("💰 Seus pontos: " + criatura.getPontos());
            
            UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
            
            System.out.println("\n🛒 UPGRADES PERMANENTES DISPONÍVEIS:");
            
            // Upgrades básicos
            if (!upgrades.multiplicadorPontos) {
                System.out.println("1. 🎯 Multiplicador de Pontos 2x (200 pontos)");
                System.out.println("   → Dobra os pontos ganhos em minigames!");
            }
            
            if (!upgrades.eficienciaComida) {
                System.out.println("2. 🍎 Eficiência Alimentar (150 pontos)");
                System.out.println("   → Comidas têm 50% mais efeito!");
            }
            
            if (!upgrades.resistenciaDoenca) {
                System.out.println("3. 🛡️ Resistência a Doenças (300 pontos)");
                System.out.println("   → 80% menos chance de ficar doente!");
            }
            
            if (!upgrades.energiaMaxima) {
                System.out.println("4. ⚡ Energia Máxima Plus (250 pontos)");
                System.out.println("   → Sono diminui 50% mais devagar!");
            }
            
            // Upgrades premium
            if (!upgrades.felicidadeEterna) {
                System.out.println("5. 😊 Felicidade Eterna (400 pontos)");
                System.out.println("   → Felicidade nunca fica abaixo de 30!");
            }
            
            if (!upgrades.superRegeneracao) {
                System.out.println("6. 💚 Super Regeneração (500 pontos)");
                System.out.println("   → Saúde se regenera automaticamente!");
            }
            
            // Upgrade supremo
            if (!upgrades.masterpet && 
                upgrades.multiplicadorPontos && upgrades.eficienciaComida && 
                upgrades.resistenciaDoenca && upgrades.energiaMaxima && 
                upgrades.felicidadeEterna && upgrades.superRegeneracao) {
                System.out.println("7. 👑 MASTERPET SUPREME (1000 pontos)");
                System.out.println("   → Seu pet se torna IMORTAL e perfeito!");
            }
            
            System.out.println("\n🎁 ITENS TEMPORÁRIOS:");
            System.out.println("8. Comida Premium (20 pontos) - Reduz fome em 40");
            System.out.println("9. Remédio (30 pontos) - Cura doenças");
            System.out.println("10. Brinquedo Especial (25 pontos) - +30 felicidade");
            System.out.println("11. Vitamina (15 pontos) - +25 energia");
            System.out.println("12. Shampoo Premium (10 pontos) - Higiene + felicidade");
            System.out.println("13. Poção do Amor (50 pontos) - +30 felicidade +10 saúde");
            System.out.println("0. Sair da loja");
            
            System.out.print("\n💳 Escolha um item (0-13): ");
            
            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                
                switch (escolha) {
                    case 1:
                        comprarMultiplicadorPontos(criatura, upgrades);
                        break;
                    case 2:
                        comprarEficienciaComida(criatura, upgrades);
                        break;
                    case 3:
                        comprarResistenciaDoenca(criatura, upgrades);
                        break;
                    case 4:
                        comprarEnergiaMaxima(criatura, upgrades);
                        break;
                    case 5:
                        comprarFelicidadeEterna(criatura, upgrades);
                        break;
                    case 6:
                        comprarSuperRegeneracao(criatura, upgrades);
                        break;
                    case 7:
                        comprarMasterpet(criatura, upgrades);
                        break;
                    case 8:
                        comprarComidaPremium(criatura);
                        break;
                    case 9:
                        comprarRemedio(criatura);
                        break;
                    case 10:
                        comprarBrinquedoEspecial(criatura);
                        break;
                    case 11:
                        comprarVitamina(criatura);
                        break;
                    case 12:
                        comprarShampooPremium(criatura);
                        break;
                    case 13:
                        comprarPocaoAmor(criatura);
                        break;
                    case 0:
                        continuarNaLoja = false;
                        System.out.println("✨ Obrigado por visitar nossa loja premium!");
                        break;
                    default:
                        System.out.println("❌ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, digite um número válido!");
            }
        }
    }
    
    // Métodos para upgrades permanentes
    private static void comprarMultiplicadorPontos(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.multiplicadorPontos) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(200)) {
            upgrades.multiplicadorPontos = true;
            System.out.println("🎯 MULTIPLICADOR DE PONTOS ATIVADO!");
            System.out.println("✨ Agora você ganha pontos em dobro nos minigames!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 200 pontos.");
        }
    }
    
    private static void comprarEficienciaComida(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.eficienciaComida) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(150)) {
            upgrades.eficienciaComida = true;
            System.out.println("🍎 EFICIÊNCIA ALIMENTAR ATIVADA!");
            System.out.println("✨ Todas as comidas agora têm 50% mais efeito!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 150 pontos.");
        }
    }
    
    private static void comprarResistenciaDoenca(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.resistenciaDoenca) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(300)) {
            upgrades.resistenciaDoenca = true;
            System.out.println("🛡️ RESISTÊNCIA A DOENÇAS ATIVADA!");
            System.out.println("✨ Seu pet agora raramente fica doente!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 300 pontos.");
        }
    }
    
    private static void comprarEnergiaMaxima(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.energiaMaxima) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(250)) {
            upgrades.energiaMaxima = true;
            System.out.println("⚡ ENERGIA MÁXIMA PLUS ATIVADA!");
            System.out.println("✨ Seu pet agora cansa muito menos!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 250 pontos.");
        }
    }
    
    private static void comprarFelicidadeEterna(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.felicidadeEterna) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(400)) {
            upgrades.felicidadeEterna = true;
            criatura.setFelicidade(Math.max(30, criatura.getFelicidade()));
            System.out.println("😊 FELICIDADE ETERNA ATIVADA!");
            System.out.println("✨ Seu pet nunca ficará muito triste!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 400 pontos.");
        }
    }
    
    private static void comprarSuperRegeneracao(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.superRegeneracao) {
            System.out.println("❌ Você já possui este upgrade!");
            return;
        }
        
        if (criatura.gastarPontos(500)) {
            upgrades.superRegeneracao = true;
            System.out.println("💚 SUPER REGENERAÇÃO ATIVADA!");
            System.out.println("✨ Seu pet agora se cura automaticamente!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 500 pontos.");
        }
    }
    
    private static void comprarMasterpet(CriaturaVirtual criatura, UpgradesPermanentes upgrades) {
        if (upgrades.masterpet) {
            System.out.println("❌ Você já possui este upgrade supremo!");
            return;
        }
        
        if (criatura.gastarPontos(1000)) {
            upgrades.masterpet = true;
            criatura.setFome(100);
            criatura.setSede(100);
            criatura.setSono(100);
            criatura.setFelicidade(100);
            criatura.setSaude(100);
            System.out.println("👑 MASTERPET SUPREME ATIVADO!");
            System.out.println("🎉 SEU PET AGORA É IMORTAL E PERFEITO!");
            System.out.println("✨ Todos os status sempre no máximo!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 1000 pontos.");
        }
    }
    
    // Métodos públicos para verificar upgrades (para usar em outras classes)
    public static boolean temMultiplicadorPontos(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.multiplicadorPontos;
    }
    
    public static boolean temEficienciaComida(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.eficienciaComida;
    }
    
    public static boolean temResistenciaDoenca(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.resistenciaDoenca;
    }
    
    public static boolean temEnergiaMaxima(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.energiaMaxima;
    }
    
    public static boolean temFelicidadeEterna(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.felicidadeEterna;
    }
    
    public static boolean temSuperRegeneracao(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.superRegeneracao;
    }
    
    public static boolean temMasterpet(CriaturaVirtual criatura) {
        UpgradesPermanentes upgrades = upgradesComprados.get(criatura);
        return upgrades != null && upgrades.masterpet;
    }private static void comprarComidaPremium(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(20)) {
            criatura.setFome(Math.min(100, criatura.getFome() + 40));
            criatura.setSono(Math.min(100, criatura.getSono() + 10));
            System.out.println("🍖 " + criatura.getNome() + " comeu comida premium! Que delícia!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 20 pontos.");
        }
    }
    
    private static void comprarRemedio(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(30)) {
            criatura.curar();
            System.out.println("💊 " + criatura.getNome() + " tomou remédio e está se sentindo melhor!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 30 pontos.");
        }
    }
    
    private static void comprarBrinquedoEspecial(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(25)) {
            criatura.setFelicidade(Math.min(100, criatura.getFelicidade() + 30));
            System.out.println("🎾 " + criatura.getNome() + " adorou o brinquedo especial!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 25 pontos.");
        }
    }
    
    private static void comprarVitamina(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(15)) {
            criatura.setSono(Math.min(100, criatura.getSono() + 25));
            System.out.println("💊 " + criatura.getNome() + " tomou vitamina e está cheio de energia!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 15 pontos.");
        }
    }
    
    private static void comprarShampooPremium(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(10)) {
            criatura.setSaude(Math.min(100, criatura.getSaude() + 10));
            criatura.setFelicidade(Math.min(100, criatura.getFelicidade() + 15));
            System.out.println("🛁 " + criatura.getNome() + " tomou um banho premium e está radiante!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 10 pontos.");
        }
    }
    
    private static void comprarPocaoAmor(CriaturaVirtual criatura) {
        if (criatura.gastarPontos(50)) {
            criatura.setFelicidade(Math.min(100, criatura.getFelicidade() + 30));
            criatura.setSaude(Math.min(100, criatura.getSaude() + 10));
            System.out.println("💖 " + criatura.getNome() + " bebeu a poção do amor e está te amando ainda mais!");
        } else {
            System.out.println("💸 Pontos insuficientes! Você precisa de 50 pontos.");
        }
    }
}
