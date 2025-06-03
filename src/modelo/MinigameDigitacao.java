package modelo;

import java.util.Scanner;
import java.util.Random;

public class MinigameDigitacao {
    // Palavras por nível de dificuldade
    private static final String[] PALAVRAS_FACIL = {
        "java", "codigo", "animal", "feliz", "casa", "tempo", "amor", "vida",
        "shrek", "gato", "pato", "bolo", "pizza", "meme", "lol", "noob",
        "mario", "sonic", "peach", "yoshi", "link", "zelda", "pikachu"
    };
    
    private static final String[] PALAVRAS_MEDIO = {
        "programacao", "tamagochi", "virtual", "energia", "saude", "higiene",
        "brincar", "dormir", "veterinario", "pontos", "minigame", "digitacao",
        "rapunzel", "minecraft", "creeper", "enderman", "diamond", "redstone",
        "chicken", "jockey", "zombie", "skeleton", "spider", "blaze", "ghast",
        "discord", "twitch", "youtube", "instagram", "tiktok", "whatsapp"
    };
    
    private static final String[] PALAVRAS_DIFICIL = {
        "desenvolvimento", "implementacao", "funcionalidade", "performance",
        "algoritmo", "estrutura", "orientacao", "encapsulamento", "polimorfismo",
        "pneumoultramicroscopicossilicovulcanoconiotico", "otorrinolaringologista",
        "paralelepipedo", "supercalifragilisticexpialidocious", "anticonstitucionalissimamente",
        "chicken jockey", "netherite sword", "enchanted golden apple", "enderdragon",
        "witherstorm", "herobrine", "notch apple", "diamond pickaxe"
    };
    
    private static final String[] FRASES_FACIL = {
        "o gato subiu no telhado",
        "java e facil",
        "vamos jogar",
        "shrek e vida",
        "batman vs superman",
        "mario come cogumelo",
        "pikachu usa thunderbolt",
        "link salva zelda",
        "sonic corre rapido"
    };
    
    private static final String[] FRASES_MEDIO = {
        "programar e divertido e util",
        "tamagochi precisa de cuidados constantes",
        "digite rapido para ganhar mais pontos",
        "rapunzel rapunzel jogue seu cabelo",
        "minecraft e o melhor jogo do mundo",
        "creeper explode e destroi tudo",
        "chicken jockey e muito raro de encontrar",
        "discord e melhor que whatsapp",
        "youtube premium vale a pena",
        "tiktok vicia demais cara"
    };
    
    private static final String[] FRASES_DIFICIL = {
        "a programacao orientada a objetos facilita o desenvolvimento",
        "implementar funcionalidades complexas requer conhecimento avancado",
        "algoritmos eficientes melhoram significativamente a performance",
        "supercalifragilisticexpialidocious e uma palavra inventada da mary poppins",
        "pneumoultramicroscopicossilicovulcanoconiotico e a maior palavra do dicionario",
        "chicken jockey riding skeleton horse with enchanted diamond armor",
        "herobrine never existed but became minecraft legendary creepypasta folklore",
        "enderdragon fight requires lots of preparation and enderpeals for movement",
        "anticonstitucionalissimamente significa de forma anticonstitucional ao extremo"
    };

    public static void jogarMinigame(CriaturaVirtual criatura, Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       MINIGAME DE DIGITAÇÃO          ║");
        System.out.println("╚══════════════════════════════════════╝");
        
        // Escolher dificuldade
        int dificuldade = escolherDificuldade(scanner);
        if (dificuldade == -1) return; // Cancelou
        
        String[] nomeDificuldade = {"FÁCIL", "MÉDIO", "DIFÍCIL"};
        int multiplicadorPontos = dificuldade + 1; // 1x, 2x, 3x
        
        System.out.println("\n🎯 DIFICULDADE: " + nomeDificuldade[dificuldade]);
        System.out.println("💰 MULTIPLICADOR DE PONTOS: " + multiplicadorPontos + "x");
        System.out.println("\nDigite as palavras/frases que aparecerem o mais rápido possível!");
        System.out.println("Você tem 5 rodadas para ganhar pontos.");
        System.out.println("Acerte pelo menos 3 para ganhar pontos!");
        
        // Verificar se tem multiplicador de pontos da loja
        if (Loja.temMultiplicadorPontos(criatura)) {
            multiplicadorPontos *= 2;
            System.out.println("🎯 BÔNUS DA LOJA: Multiplicador dobrado! Total: " + multiplicadorPontos + "x");
        }
        
        System.out.println("\nPressione ENTER para começar...");
        scanner.nextLine();
        
        Random random = new Random();
        int acertos = 0;
        int totalRodadas = 5;
        long tempoTotal = 0;
        
        for (int rodada = 1; rodada <= totalRodadas; rodada++) {
            System.out.println("\n--- RODADA " + rodada + " ---");
            
            String desafio = gerarDesafio(dificuldade, random);
            System.out.println("Digite: " + desafio);
            System.out.print("> ");
            
            long inicio = System.currentTimeMillis();
            String resposta = scanner.nextLine().trim().toLowerCase();
            long fim = System.currentTimeMillis();
            
            long tempoRodada = fim - inicio;
            tempoTotal += tempoRodada;
            
            if (resposta.equals(desafio.toLowerCase())) {
                acertos++;
                double segundos = tempoRodada / 1000.0;
                System.out.printf("✅ CORRETO! (%.2f segundos)\n", segundos);
                
                // Bônus por velocidade baseado na dificuldade
                double limiteVelocidade = 3.0 + (dificuldade * 2.0); // 3s, 5s, 7s
                if (segundos < limiteVelocidade) {
                    System.out.println("🚀 BÔNUS DE VELOCIDADE!");
                }
            } else {
                System.out.println("❌ Incorreto! Era: " + desafio);
            }
        }
        
        // Calcular resultado
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║             RESULTADO                ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("🎯 Acertos: " + acertos + "/" + totalRodadas);
        double tempoMedio = (tempoTotal / 1000.0) / totalRodadas;
        System.out.printf("⏱️ Tempo médio: %.2f segundos\n", tempoMedio);
        System.out.println("📈 Dificuldade: " + nomeDificuldade[dificuldade]);
        
        int pontosGanhos = calcularPontos(acertos, totalRodadas, tempoMedio, dificuldade, multiplicadorPontos);
        
        if (pontosGanhos > 0) {
            criatura.ganharPontos(pontosGanhos);
            System.out.println("🎉 Você ganhou " + pontosGanhos + " pontos!");
            
            // Bônus para a criatura por jogar
            int bonusFelicidade = 5 + (dificuldade * 2); // 5, 7, 9
            criatura.setFelicidade(Math.min(100, criatura.getFelicidade() + bonusFelicidade));
            criatura.atualizarHumor();
            
            System.out.println("😊 " + criatura.getNome() + " ficou " + 
                             (dificuldade == 2 ? "muito" : dificuldade == 1 ? "bem" : "") + 
                             " feliz vendo você jogar!");
        } else {
            System.out.println("😞 Você precisa acertar pelo menos 3 para ganhar pontos!");
            System.out.println("💪 Tente novamente! A prática leva à perfeição!");
        }
    }
    
    private static int escolherDificuldade(Scanner scanner) {
        while (true) {
            System.out.println("\n🎯 Escolha o nível de dificuldade:");
            System.out.println("1. 🟢 FÁCIL - Palavras simples (1x pontos)");
            System.out.println("2. 🟡 MÉDIO - Palavras médias (2x pontos)");
            System.out.println("3. 🔴 DIFÍCIL - Palavras complexas (3x pontos)");
            System.out.println("0. ❌ Cancelar");
            
            System.out.print("\nSua escolha (0-3): ");
            
            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                
                switch (escolha) {
                    case 1: return 0; // Fácil
                    case 2: return 1; // Médio
                    case 3: return 2; // Difícil
                    case 0:
                        System.out.println("⬅️ Voltando ao menu principal...");
                        return -1; // Cancelar
                    default:
                        System.out.println("❌ Opção inválida! Escolha 0-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, digite um número válido!");
            }
        }
    }
    
    private static String gerarDesafio(int dificuldade, Random random) {
        // Escolhe entre palavra ou frase baseado na dificuldade
        boolean usarFrase = random.nextInt(10) < (3 + dificuldade * 2); // 30%, 50%, 70%
        
        if (usarFrase) {
            switch (dificuldade) {
                case 0: return FRASES_FACIL[random.nextInt(FRASES_FACIL.length)];
                case 1: return FRASES_MEDIO[random.nextInt(FRASES_MEDIO.length)];
                case 2: return FRASES_DIFICIL[random.nextInt(FRASES_DIFICIL.length)];
            }
        } else {
            switch (dificuldade) {
                case 0: return PALAVRAS_FACIL[random.nextInt(PALAVRAS_FACIL.length)];
                case 1: return PALAVRAS_MEDIO[random.nextInt(PALAVRAS_MEDIO.length)];
                case 2: return PALAVRAS_DIFICIL[random.nextInt(PALAVRAS_DIFICIL.length)];
            }
        }
        
        return "erro"; // Fallback
    }
    
    private static int calcularPontos(int acertos, int total, double tempoMedio, int dificuldade, int multiplicador) {
        if (acertos < 3) {
            return 0; // Não ganha pontos se acertar menos de 3
        }
        
        int pontosBase = acertos * (10 + dificuldade * 5); // 10, 15, 20 por acerto
        
        // Bônus por porcentagem de acertos
        double porcentagemAcertos = (double) acertos / total;
        if (porcentagemAcertos == 1.0) {
            pontosBase += 30 + (dificuldade * 10); // 30, 40, 50
        } else if (porcentagemAcertos >= 0.8) {
            pontosBase += 15 + (dificuldade * 5); // 15, 20, 25
        }
        
        // Bônus por velocidade baseado na dificuldade
        double[] limitesVelocidade = {4.0, 6.0, 8.0}; // Fácil, Médio, Difícil
        double[] limitesVelocidadeBonus = {6.0, 8.0, 10.0};
        
        if (tempoMedio < limitesVelocidade[dificuldade]) {
            pontosBase += 20 + (dificuldade * 5); // 20, 25, 30
        } else if (tempoMedio < limitesVelocidadeBonus[dificuldade]) {
            pontosBase += 10 + (dificuldade * 2); // 10, 12, 14
        }
        
        // Aplicar multiplicador final
        return pontosBase * multiplicador;
    }
    
    public static void exibirRegras() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        REGRAS DO MINIGAME            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("🎯 OBJETIVO:");
        System.out.println("• Digite exatamente as palavras/frases que aparecem");
        System.out.println("• Você tem 5 rodadas para mostrar sua velocidade");
        System.out.println("• Precisa acertar pelo menos 3 para ganhar pontos");
        
        System.out.println("\n📊 DIFICULDADES:");
        System.out.println("🟢 FÁCIL: Palavras simples, multiplicador 1x");
        System.out.println("🟡 MÉDIO: Palavras médias, multiplicador 2x");
        System.out.println("🔴 DIFÍCIL: Palavras complexas, multiplicador 3x");
        
        System.out.println("\n💰 PONTUAÇÃO:");
        System.out.println("• Pontos base por acerto: 10/15/20 (por dificuldade)");
        System.out.println("• Bônus por 100% acertos: +30/40/50 pontos");
        System.out.println("• Bônus por 80%+ acertos: +15/20/25 pontos");
        System.out.println("• Bônus por velocidade alta: +20/25/30 pontos");
        System.out.println("• Bônus por velocidade média: +10/12/14 pontos");
        
        System.out.println("\n🎁 UPGRADES:");
        System.out.println("• Multiplicador da Loja: Dobra todos os pontos!");
        System.out.println("• Felicidade da criatura aumenta com a dificuldade!");
    }
}
