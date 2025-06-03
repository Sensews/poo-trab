//parte JP
package modelo;

// Serializable é uma interface que permite que a classe seja serializada (ou seja, convertida em um fluxo de bytes) para que possa ser salva em um arquivo.
import java.io.Serializable;

public abstract class CriaturaVirtual implements Serializable {
    protected String nome; // Nome do bixo
    protected int idade; // A idade do bixo, ao chegar em certa idade ele 💀
    protected int fome; // Quanto maior, mais fome, se chegar em 100 ele 💀
    protected int saude; // Quanto maior, mais saude, se chegar em 0 ele 💀
    protected int energia; // Quanto maior, mais energia, se chegar em 15 ele fica exausto
    protected int higiene; // Quanto maior, mais limpo, se chegar em 25 ele fica sujo
    protected int felicidade; // Quanto maior, mais feliz, se chegar em 25 ele fica triste
    protected int afeto; // vínculo com o dono
    protected boolean doente; // Se o bixo estiver doente, ele não pode brincar, comer ou fazer nada
    protected String humor; //Ex: "Feliz", "Cansado", "Com Fome", "Triste", "Morto"
    protected int pontos; // Pontos ganhos em minigames para usar na loja
    protected boolean vivo; // Se ainda está vivo    //construtor
    public CriaturaVirtual(String nome) {
        this.nome = nome;
        this.idade = 0;
        this.fome = 0;
        this.saude = 100;
        this.energia = 100;
        this.higiene = 100;
        this.felicidade = 100;
        this.afeto = 0;
        this.doente = false;
        this.humor = "Feliz";
        this.pontos = 0;
        this.vivo = true;
    }

    //
    public abstract void agir();

    // "botão para alimentar"
    public void alimentar() {
        fome = Math.max(0, fome - 20);
        energia = Math.min(100, energia + 5);
        atualizarHumor();
    }

    // "botão para brincar"
    public void brincar() {
        felicidade = Math.min(100, felicidade + 15);
        energia = Math.max(0, energia - 10);
        higiene = Math.max(0, higiene - 5);
        afeto += 1;
        atualizarHumor();
    }

    // "botão para por para dormir"
    public void dormir() {
        energia = Math.min(100, energia + 50);
        fome = Math.min(100, fome + 20);
        atualizarHumor();
    }

    // "botão para dar banho"
    public void banho() {
        higiene = 100;
        felicidade = Math.min(100, felicidade + 10);
        energia = Math.max(0, energia - 5);
        atualizarHumor();
    }

    // "botão para levar ao veterinário"
    public void veterinario() {
        if (doente) {
            doente = false;
            saude = Math.min(100, saude + 30);
        }
        atualizarHumor();
    }

    // Método para atualizar o humor baseado nos stats
    public void atualizarHumor() {
        if (!vivo) {
            humor = "Morto";
            return;
        }
        
        if (saude <= 0 || (energia <= 0 && higiene <= 0 && felicidade <= 0)) {
            vivo = false;
            humor = "Morto";
            return;
        }
        
        if (doente) {
            humor = "Doente";
        } else if (fome >= 80) {
            humor = "Faminto";
        } else if (energia <= 20) {
            humor = "Exausto";
        } else if (higiene <= 30) {
            humor = "Sujo";
        } else if (felicidade <= 30) {
            humor = "Triste";
        } else if (felicidade >= 80 && energia >= 70) {
            humor = "Muito Feliz";
        } else {
            humor = "Feliz";
        }
    }

    // Método para passar o tempo (envelhece e altera stats)
    public void passarTempo() {
        if (!vivo) return;
        
        idade++;
        fome = Math.min(100, fome + 5);
        energia = Math.max(0, energia - 3);
        higiene = Math.max(0, higiene - 2);
        felicidade = Math.max(0, felicidade - 1);
        
        // Chance de ficar doente se stats estão baixos
        if ((fome > 70 || higiene < 30 || energia < 20) && Math.random() < 0.1) {
            doente = true;
            saude = Math.max(0, saude - 10);
        }
        
        // Morre de velhice
        if (idade > 100) {
            vivo = false;
        }
        
        atualizarHumor();
    }

    // Métodos para o sistema de pontos
    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public boolean gastarPontos(int pontos) {
        if (this.pontos >= pontos) {
            this.pontos -= pontos;
            return true;
        }
        return false;
    }

    // Métodos para a loja poder modificar atributos
    public void adicionarEnergia(int valor) {
        energia = Math.min(100, energia + valor);
        atualizarHumor();
    }
    
    public void reduzirFome(int valor) {
        fome = Math.max(0, fome - valor);
        atualizarHumor();
    }
    
    public void adicionarSaude(int valor) {
        saude = Math.min(100, saude + valor);
        atualizarHumor();
    }
    
    public void curarDoenca() {
        doente = false;
        atualizarHumor();
    }
    
    public void adicionarFelicidade(int valor) {
        felicidade = Math.min(100, felicidade + valor);
        atualizarHumor();
    }
    
    public void adicionarAfeto(int valor) {
        afeto = Math.min(100, afeto + valor);
        atualizarHumor();
    }
    
    public void limparTotalmente() {
        higiene = 100;
        atualizarHumor();
    }

    // Getters
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public int getFome() { return fome; }
    public int getSaude() { return saude; }
    public int getEnergia() { return energia; }
    public int getHigiene() { return higiene; }
    public int getFelicidade() { return felicidade; }
    public int getAfeto() { return afeto; }
    public boolean isDoente() { return doente; }
    public String getHumor() { return humor; }
    public int getPontos() { return pontos; }
    public boolean isVivo() { return vivo; }

    // Setters (se necessário)
    public void setNome(String nome) { this.nome = nome; }

    // Método para exibir status
    public String getStatus() {
        return String.format(
            "=== %s ===\n" +
            "Idade: %d anos\n" +
            "Humor: %s\n" +
            "Fome: %d/100\n" +
            "Saúde: %d/100\n" +
            "Energia: %d/100\n" +
            "Higiene: %d/100\n" +
            "Felicidade: %d/100\n" +
            "Afeto: %d\n" +
            "Pontos: %d\n" +
            "Status: %s\n",
            nome, idade, humor, fome, saude, energia, higiene, felicidade, afeto, pontos,
            vivo ? (doente ? "Doente" : "Saudável") : "Morto"
        );
    }
}