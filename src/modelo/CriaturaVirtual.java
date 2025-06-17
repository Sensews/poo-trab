package modelo;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * REQUISITO 1: Programa estruturado em classes (Encapsulamento)
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 3: Classe abstrata
 * REQUISITO 6: Superclasse para relações de herança
 */
public abstract class CriaturaVirtual implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // REQUISITO 5: Atributos do programa (mais de 10 no total)
    protected String nome; // Atributo 1
    protected int fome; // Atributo 2 - 0-100 (0 = com fome, 100 = satisfeito)
    protected int sede; // Atributo 3 - 0-100 (0 = com sede, 100 = satisfeito)
    protected int sono; // Atributo 4 - 0-100 (0 = cansado, 100 = descansado)
    protected int felicidade; // Atributo 5 - 0-100 (0 = triste, 100 = feliz)
    protected int saude; // Atributo 6 - 0-100 (0 = doente, 100 = saudável)
    protected boolean vivo; // Atributo 7
    protected String humor; // Atributo 8
    protected int pontos; // Atributo 9 - Pontos para comprar na loja
    // REQUISITO 9: Relação de associação (CriaturaVirtual tem um Inventario)
    protected Inventario inventario; // Atributo 10
    protected boolean doente; // Atributo 11
    protected String tipoDoenca; // Atributo 12

    public CriaturaVirtual(String nome) {
        this.nome = nome;
        this.fome = 100;
        this.sede = 100;
        this.sono = 100;
        this.felicidade = 100;
        this.saude = 100;
        this.vivo = true;
        this.pontos = 50; // Pontos iniciais
        this.inventario = new Inventario();
        this.doente = false;
        this.tipoDoenca = "";
        atualizarHumor();    }    /**
     * REQUISITO 4: Métodos abstratos
     * REQUISITO 6: Métodos para serem implementados pelas subclasses
     */
    public abstract void emitirSom(); // Método abstrato 1
    public abstract String getTipo(); // Método abstrato 2
    public abstract void habilidadeEspecial(); // Método abstrato 3

    /**
     * REQUISITO 5: Métodos do programa - mais de 10 no total
     */

    // Ações básicas
    public void alimentar() { // Método 1
        if (!vivo) {
            System.out.println(nome + " não pode ser alimentado pois não está vivo.");
            return;
        }
        
        if (fome >= 90) {
            System.out.println(nome + " não está com fome no momento!");
            return;
        }
        
        fome = Math.min(100, fome + 30);
        felicidade = Math.min(100, felicidade + 5);
        System.out.println(nome + " foi alimentado! Fome: " + fome);        atualizarHumor();
    }
    
    public void alimentar(ItemComida comida) { // Método 2
        if (!vivo) {
            System.out.println(nome + " não pode ser alimentado pois não está vivo.");
            return;
        }

        if (fome >= 90) {
            System.out.println(nome + " não está com fome no momento!");
            return;
        }

        aplicarEfeitosComida(comida);
        System.out.println(nome + " comeu " + comida.getNome() + "!");
        System.out.println("Efeitos: " + comida.getDescricao());
        atualizarHumor();
    }

    public void alimentar(Scanner scanner) { // Método 3
        if (!vivo) {
            System.out.println(nome + " não pode ser alimentado pois não está vivo.");
            return;
        }
        
        if (fome >= 90) {
            System.out.println(nome + " não está com fome no momento!");
            return;
        }

        System.out.println("\n=== ALIMENTAR " + nome.toUpperCase() + " ===");
        inventario.mostrarComidas();
        
        if (inventario.temComidas()) {
            System.out.print("Escolha uma comida (número): ");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                
                ItemComida comida = inventario.usarComida(escolha);
                if (comida != null) {
                    aplicarEfeitosComida(comida);
                    System.out.println(nome + " comeu " + comida.getNome() + "!");
                    System.out.println("Efeitos: " + comida.getDescricao());
                    atualizarHumor();
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
                scanner.nextLine(); // limpar buffer
            }
        } else {
            System.out.println("Você não tem comidas no inventário!");
            System.out.println("Visite a loja de comidas para comprar alimentos.");
        }
    }    private void aplicarEfeitosComida(ItemComida comida) {
        int multiplicador = Loja.temEficienciaComida(this) ? 150 : 100; // 50% mais efeito se tiver upgrade
        
        fome = Math.min(100, fome + (comida.getEfeitoFome() * multiplicador / 100));
        sede = Math.min(100, sede + (comida.getEfeitoSede() * multiplicador / 100));
        felicidade = Math.min(100, felicidade + (comida.getEfeitoFelicidade() * multiplicador / 100));
        saude = Math.min(100, saude + (comida.getEfeitoSaude() * multiplicador / 100));
        
        if (Loja.temEficienciaComida(this)) {
            System.out.println("✨ BÔNUS DE EFICIÊNCIA ALIMENTAR APLICADO!");
        }
        
        // Curar doenças com comidas especiais
        if (comida.getEfeitoSaude() >= 30 && doente) {
            curarDoenca();
        }    }

    public void dormir() {
        if (!vivo) {
            System.out.println(nome + " não pode dormir pois não está vivo.");
            return;
        }
        
        if (sono >= 90) {
            System.out.println(nome + " não está com sono no momento!");
            return;
        }
        
        sono = Math.min(100, sono + 50);
        saude = Math.min(100, saude + 5);
        System.out.println(nome + " dormiu um pouco! Sono: " + sono);
        atualizarHumor();
    }    /**
     * REQUISITO 7: Método NÃO-ABSTRATO que pode ser sobrescrito pelas subclasses
     * Este é o método base para brincar, mas cada animal tem seu comportamento único
     * É chamado como super.brincar() pelas subclasses antes da implementação específica
     * IMPORTANTE: Este método demonstra o REQUISITO 7 - método sobrescrito não-abstrato
     */
    public void brincar() {
        if (!vivo) {
            System.out.println(nome + " não pode brincar pois não está vivo.");
            return;
        }
        
        felicidade = Math.min(100, felicidade + 20);
        fome = Math.max(0, fome - 5);
        sede = Math.max(0, sede - 5);
        sono = Math.max(0, sono - 10);
        System.out.println(nome + " brincou e ficou mais feliz! Felicidade: " + felicidade);
        atualizarHumor();
    }

    public void curar() {
        if (!vivo) {
            System.out.println(nome + " não pode ser curado pois não está vivo.");
            return;
        }
        
        if (saude >= 90 && !doente) {
            System.out.println(nome + " já está saudável!");
            return;
        }
        
        saude = Math.min(100, saude + 30);
        curarDoenca();
        System.out.println(nome + " foi curado! Saúde: " + saude);
        atualizarHumor();
    }    // Atualização automática pelo tempo
    public void atualizacaoTempo() {
        if (!vivo) return;
        
        // Não fazer degradação se tiver Masterpet (já será regenerado automaticamente)
        if (Loja.temMasterpet(this)) {
            return;
        }
        
        // Degradação natural com velocidades diferentes para cada atributo
        fome = Math.max(0, fome - 2); // Fome desce moderadamente
        sede = Math.max(0, sede - 3); // Sede desce mais rápido (você fica com sede mais rápido)
        
        // Upgrade de Energia Máxima reduz degradação do sono em 50%
        int degradacaoSono = Loja.temEnergiaMaxima(this) ? 1 : 2; // Sono desce mais devagar
        sono = Math.max(0, sono - degradacaoSono);
        
        // Upgrade de Felicidade Eterna mantém felicidade mínima em 30
        if (Loja.temFelicidadeEterna(this)) {
            felicidade = Math.max(30, felicidade - 1);
        } else {
            felicidade = Math.max(0, felicidade - 1); // Felicidade desce devagar
        }
        
        // Se doente, saúde diminui mais rápido
        if (doente) {
            saude = Math.max(0, saude - 3); // Saúde desce mais rápido quando doente
        } else {
            saude = Math.max(0, saude - 1); // Saúde desce devagar quando saudável
            
            // Chance de ficar doente com upgrade de resistência (reduzida)
            if (fome < 15 || sede < 15 || sono < 15) {
                Random random = new Random();
                int chanceDoenca = Loja.temResistenciaDoenca(this) ? 1 : 5; // 1% vs 5%
                if (random.nextInt(100) < chanceDoenca) {
                    ficarDoente();
                }
            }
        }
        
        // Verificar se ainda está vivo
        verificarVida();
        atualizarHumor();
    }

    private void ficarDoente() {
        if (!doente) {
            doente = true;
            Random random = new Random();
            String[] doencas = {"Resfriado", "Dor de barriga", "Febre", "Tristeza profunda"};
            tipoDoenca = doencas[random.nextInt(doencas.length)];
            saude = Math.max(0, saude - 20);
            System.out.println("\n⚠️  " + nome + " ficou doente com: " + tipoDoenca);
        }
    }
    private void curarDoenca() {
        if (doente) {
            doente = false;
            tipoDoenca = "";
            System.out.println("✅ " + nome + " foi curado da doença!");
        }
    }    public void verificarVida() {
        // Condições de morte mais tolerantes
        if ((fome <= 0 && sede <= 0) || (fome <= 0 && sono <= 0) || (sede <= 0 && sono <= 0)) {
            vivo = false;
            humor = "💀 Morto";
            System.out.println("\n💀 " + nome + " morreu por falta de cuidados...");
        } else if (saude <= 0) {
            vivo = false;
            humor = "💀 Morto";
            System.out.println("\n💀 " + nome + " morreu por problemas de saúde...");
        }
    }

    public void atualizarHumor() {
        if (!vivo) {
            humor = "💀 Morto";
            return;
        }
        
        if (doente) {
            humor = "🤒 Doente (" + tipoDoenca + ")";
            return;
        }
        
        int media = (fome + sede + sono + felicidade + saude) / 5;
        
        if (media >= 90) {
            humor = "😄 Muito Feliz";
        } else if (media >= 70) {
            humor = "😊 Feliz";
        } else if (media >= 50) {
            humor = "😐 Neutro";
        } else if (media >= 30) {
            humor = "😔 Triste";
        } else {
            humor = "😭 Muito Triste";
        }
    }    // Sistema de pontos
    public void ganharPontos(int quantidade) {
        // Aplicar multiplicador da loja se existir
        if (Loja.temMultiplicadorPontos(this)) {
            quantidade *= 2;
            System.out.println("🎯 MULTIPLICADOR DA LOJA ATIVADO!");
        }
        
        pontos += quantidade;
        System.out.println("+" + quantidade + " pontos! Total: " + pontos);
    }

    public boolean gastarPontos(int quantidade) {
        if (pontos >= quantidade) {
            pontos -= quantidade;
            return true;
        }
        return false;
    }

    // Método para mostrar status
    public void mostrarStatus() {
        System.out.println("\n=== STATUS DE " + nome.toUpperCase() + " ===");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Humor: " + humor);
        System.out.println("Vivo: " + (vivo ? "Sim" : "Não"));
        if (doente) {
            System.out.println("Estado: 🤒 Doente (" + tipoDoenca + ")");
        }
        System.out.println("\n--- Necessidades ---");
        System.out.println("Fome: " + fome + "/100 " + getBarraStatus(fome));
        System.out.println("Sede: " + sede + "/100 " + getBarraStatus(sede));
        System.out.println("Sono: " + sono + "/100 " + getBarraStatus(sono));
        System.out.println("Felicidade: " + felicidade + "/100 " + getBarraStatus(felicidade));
        System.out.println("Saúde: " + saude + "/100 " + getBarraStatus(saude));
        System.out.println("\nPontos: " + pontos);
        System.out.println("Comidas no inventário: " + inventario.getQuantidadeTotal());
    }

    private String getBarraStatus(int valor) {
        StringBuilder barra = new StringBuilder("[");
        int barras = valor / 10;
        for (int i = 0; i < 10; i++) {
            if (i < barras) {
                barra.append("█");
            } else {
                barra.append("░");
            }
        }
        barra.append("]");
        return barra.toString();
    }

    /**
     * REQUISITO 11: Método que usa TamagotchiException para validações críticas
     * Valida se a criatura pode realizar uma ação
     */
    public void validarEstadoCritico() throws TamagotchiException {
        if (!vivo) {
            throw new TamagotchiException("Operação inválida: " + nome + " não está vivo!");
        }
        
        if (saude <= 5) {
            throw new TamagotchiException("Estado crítico: " + nome + " está muito doente (saúde: " + saude + ")!");
        }
        
        if (fome <= 5 && sede <= 5) {
            throw new TamagotchiException("Estado de emergência: " + nome + " está morrendo de fome e sede!");
        }
    }

    /**
     * REQUISITO 11: Método seguro que valida antes de alimentar
     */
    public void alimentarSeguro(ItemComida comida) throws TamagotchiException {
        if (comida == null) {
            throw new TamagotchiException("Erro: Tentativa de alimentar com comida nula!");
        }
        
        validarEstadoCritico();
        
        if (fome >= 95) {
            throw new TamagotchiException(nome + " está muito satisfeito e não pode comer mais!");
        }
        
        // Se passou nas validações, alimenta normalmente
        alimentar(comida);
    }

    // Getters
    public String getNome() { return nome; }
    public int getFome() { return fome; }
    public int getSede() { return sede; }
    public int getSono() { return sono; }
    public int getFelicidade() { return felicidade; }
    public int getSaude() { return saude; }
    public boolean isVivo() { return vivo; }
    public String getHumor() { return humor; }
    public int getPontos() { return pontos; }
    public Inventario getInventario() { return inventario; }
    public boolean isDoente() { return doente; }
    public String getTipoDoenca() { return tipoDoenca; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setFome(int fome) { 
        this.fome = Math.max(0, Math.min(100, fome)); 
        atualizarHumor();
    }
    public void setSede(int sede) { 
        this.sede = Math.max(0, Math.min(100, sede)); 
        atualizarHumor();
    }
    public void setSono(int sono) { 
        this.sono = Math.max(0, Math.min(100, sono)); 
        atualizarHumor();
    }
    public void setFelicidade(int felicidade) { 
        this.felicidade = Math.max(0, Math.min(100, felicidade)); 
        atualizarHumor();
    }
    public void setSaude(int saude) { 
        this.saude = Math.max(0, Math.min(100, saude)); 
        atualizarHumor();
    }
    public void aumentarFelicidade(int quantidade) {
        this.felicidade += quantidade;
    }
    public void ganharPontos2(int quantidade) {
        this.pontos += quantidade;
    }
    public void setPontos(int pontos) { this.pontos = Math.max(0, pontos); }
}
