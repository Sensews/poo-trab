package modelo;

import java.util.Scanner;
import java.util.Random;

public class MinigameDigitacao {
    private static final String[] PALAVRAS = {
        "java", "codigo", "programacao", "tamagochi", "animal", "virtual", "feliz",
        "energia", "saude", "fome", "higiene", "brincar", "dormir", "veterinario",
        "pontos", "loja", "minigame", "digitacao", "rapido", "precisao", "tempo",
        "desafio", "palavra", "letra", "tecla", "velocidade", "score", "nivel",
        "computador", "teclado", "mouse", "monitor", "software", "hardware", "dados"
    };
    
    private static final String[] FRASES = {
        "o gato subiu no telhado",
        "programar e divertido",
        "java e uma linguagem poderosa",
        "tamagochi precisa de cuidados",
        "digite rapido para ganhar pontos",
        "a pratica leva a perfeicao",
        "codigo limpo e importante",
        "vamos jogar mais um round"
    };
    
    public static void jogarMinigame(CriaturaVirtual criatura, Scanner scanner) {
        System.out.println("\n=== MINIGAME DE DIGITAÇÃO ===");
        System.out.println("Digite as palavras/frases que aparecerem o mais rápido possível!");
        System.out.println("Você tem 5 rodadas para ganhar pontos.");
        System.out.println("Acerte pelo menos 3 para ganhar pontos!");
        System.out.println("\nPressione ENTER para começar...");
        scanner.nextLine();
        
        Random random = new Random();
        int acertos = 0;
        int totalRodadas = 5;
        long tempoTotal = 0;
        
        for (int rodada = 1; rodada <= totalRodadas; rodada++) {
            System.out.println("\n--- RODADA " + rodada + " ---");
            
            // Escolhe entre palavra simples ou frase
            String desafio;
            if (random.nextBoolean()) {
                desafio = PALAVRAS[random.nextInt(PALAVRAS.length)];
            } else {
                desafio = FRASES[random.nextInt(FRASES.length)];
            }
            
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
                System.out.printf("✓ CORRETO! (%.2f segundos)\n", segundos);
                
                // Bônus por velocidade
                if (segundos < 3.0) {
                    System.out.println("🚀 BÔNUS DE VELOCIDADE!");
                }
            } else {
                System.out.println("✗ Incorreto! Era: " + desafio);
            }
        }
        
        // Calcular resultado
        System.out.println("\n=== RESULTADO ===");
        System.out.println("Acertos: " + acertos + "/" + totalRodadas);
        double tempoMedio = (tempoTotal / 1000.0) / totalRodadas;
        System.out.printf("Tempo médio: %.2f segundos\n", tempoMedio);
        
        int pontosGanhos = calcularPontos(acertos, totalRodadas, tempoMedio);
          if (pontosGanhos > 0) {
            criatura.ganharPontos(pontosGanhos);
            System.out.println("🎉 Você ganhou " + pontosGanhos + " pontos!");
            
            // Pequeno bônus para a criatura por jogar
            criatura.setFelicidade(Math.min(100, criatura.getFelicidade() + 5));
            criatura.atualizarHumor();
            
            System.out.println(criatura.getNome() + " ficou feliz vendo você jogar!");
        } else {
            System.out.println("😞 Você precisa acertar pelo menos 3 para ganhar pontos!");
            System.out.println("Tente novamente!");
        }
    }
    
    private static int calcularPontos(int acertos, int total, double tempoMedio) {
        if (acertos < 3) {
            return 0; // Não ganha pontos se acertar menos de 3
        }
        
        int pontosBase = acertos * 10; // 10 pontos por acerto
        
        // Bônus por porcentagem de acertos
        double porcentagemAcertos = (double) acertos / total;
        if (porcentagemAcertos == 1.0) {
            pontosBase += 20; // Bônus por 100% de acertos
        } else if (porcentagemAcertos >= 0.8) {
            pontosBase += 10; // Bônus por 80%+ de acertos
        }
        
        // Bônus por velocidade (tempo médio menor que 4 segundos)
        if (tempoMedio < 4.0) {
            pontosBase += 15;
        } else if (tempoMedio < 6.0) {
            pontosBase += 5;
        }
        
        return pontosBase;
    }
    
    public static void exibirRegras() {
        System.out.println("\n=== REGRAS DO MINIGAME ===");
        System.out.println("• Digite exatamente as palavras/frases que aparecem");
        System.out.println("• Você tem 5 rodadas para mostrar sua velocidade");
        System.out.println("• Precisa acertar pelo menos 3 para ganhar pontos");
        System.out.println("• Quanto mais rápido, mais pontos você ganha!");
        System.out.println("• Pontuação: 10 pontos por acerto + bônus");
        System.out.println("• Bônus por 100% de acertos: +20 pontos");
        System.out.println("• Bônus por 80%+ de acertos: +10 pontos");
        System.out.println("• Bônus por velocidade (< 4s): +15 pontos");
        System.out.println("• Bônus por velocidade (< 6s): +5 pontos");
    }
}
