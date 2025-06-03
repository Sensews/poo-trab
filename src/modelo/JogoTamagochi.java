package modelo;

import java.util.Scanner;

public class JogoTamagochi {
    private CriaturaVirtual criatura;
    private Scanner scanner;
    private boolean jogoAtivo;
    
    public JogoTamagochi() {
        scanner = new Scanner(System.in);
        jogoAtivo = true;
    }
    
    public void iniciarJogo() {
        exibirBoasVindas();
        criarCriatura();
        
        if (criatura != null) {
            loopPrincipal();
        }
        
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }
    
    private void exibirBoasVindas() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     BEM-VINDO AO TAMAGOCHI VIRTUAL   ║");
        System.out.println("║            Versão Terminal           ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nCuide bem do seu bichinho virtual!");
        System.out.println("Alimente, brinque, dê banho e jogue minigames para ganhar pontos!");
    }
    
    private void criarCriatura() {
        System.out.println("\n=== CRIANDO SEU TAMAGOCHI ===");
        
        // Escolher nome
        System.out.print("Digite o nome do seu tamagochi: ");
        String nome = scanner.nextLine().trim();
        
        if (nome.isEmpty()) {
            nome = "Tamagochi";
        }
        
        // Escolher tipo
        System.out.println("\nEscolha o tipo do seu tamagochi:");
        System.out.println("1. Gato (Mais limpo, afetuoso)");
        System.out.println("2. Cachorro (Energético, muito feliz)");
        System.out.println("3. Peixe (Zen, sempre limpo)");
        
        boolean tipoEscolhido = false;
        while (!tipoEscolhido) {
            System.out.print("\nDigite sua escolha (1-3): ");
            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                
                switch (escolha) {
                    case 1:
                        criatura = new Gato(nome);
                        System.out.println("\n🐱 " + nome + " (Gato) foi criado!");
                        tipoEscolhido = true;
                        break;
                    case 2:
                        criatura = new Cachorro(nome);
                        System.out.println("\n🐶 " + nome + " (Cachorro) foi criado!");
                        tipoEscolhido = true;
                        break;
                    case 3:
                        criatura = new Peixe(nome);
                        System.out.println("\n🐠 " + nome + " (Peixe) foi criado!");
                        tipoEscolhido = true;
                        break;
                    default:
                        System.out.println("Opção inválida! Escolha 1, 2 ou 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
        
        System.out.println("Seu tamagochi está pronto para começar a aventura!");
        System.out.println(criatura.getStatus());
    }
    
    private void loopPrincipal() {
        while (jogoAtivo && criatura.isVivo()) {
            exibirMenu();
            int opcao = lerOpcao();
            processarOpcao(opcao);
            
            // A criatura age automaticamente
            if (Math.random() < 0.3) { // 30% chance
                criatura.agir();
            }
            
            // Passar tempo automaticamente (simula o tempo passando)
            if (Math.random() < 0.1) { // 10% chance de passar tempo
                criatura.passarTempo();
            }
        }
        
        if (!criatura.isVivo()) {
            System.out.println("\n💀 OH NÃO! " + criatura.getNome() + " morreu!");
            System.out.println("Fim de jogo... Cuide melhor na próxima vez!");
        }
    }
    
    private void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(criatura.getStatus());
        System.out.println("=".repeat(50));
        System.out.println("O que você quer fazer?");
        System.out.println("1. Alimentar");
        System.out.println("2. Brincar");
        System.out.println("3. Colocar para dormir");
        System.out.println("4. Dar banho");
        System.out.println("5. Levar ao veterinário");
        System.out.println("6. Ver status detalhado");
        System.out.println("7. Jogar minigame (ganhar pontos)");
        System.out.println("8. Ir à loja");
        System.out.println("9. Ver regras do minigame");
        System.out.println("0. Sair do jogo");
    }
    
    private int lerOpcao() {
        System.out.print("\nSua escolha: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }
    
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                criatura.alimentar();
                System.out.println(criatura.getNome() + " foi alimentado!");
                break;
            case 2:
                criatura.brincar();
                break;
            case 3:
                criatura.dormir();
                System.out.println(criatura.getNome() + " dormiu e recuperou energia!");
                break;
            case 4:
                criatura.banho();
                System.out.println(criatura.getNome() + " tomou banho!");
                break;
            case 5:
                criatura.veterinario();
                System.out.println(criatura.getNome() + " foi ao veterinário!");
                break;
            case 6:
                System.out.println(criatura.getStatus());
                break;
            case 7:
                MinigameDigitacao.jogarMinigame(criatura, scanner);
                break;
            case 8:
                Loja.abrirLoja(criatura, scanner);
                break;
            case 9:
                MinigameDigitacao.exibirRegras();
                break;
            case 0:
                System.out.println("Salvando jogo...");
                jogoAtivo = false;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }
    
    public CriaturaVirtual getCriatura() {
        return criatura;
    }
}
