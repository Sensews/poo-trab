//parte JP
package modelo;

// Serializable é uma interface que permite que a classe seja serializada (ou seja, convertida em um fluxo de bytes) para que possa ser salva em um arquivo.
import java.io.Serializable;

public abstract class CriaturaVirtual  implements Serializable{
    protected String nome; // Nome do bixo
    protected int idade; // A idade do bixo, ao chegar em certa idade ele 💀
    protected int fome; // Quanto maior, mais fome, se chegar em 100 ele 💀
    protected int saude; // Quanto maior, mais saude, se chegar em 0 ele 💀
    protected int energia; // Quanto maior, mais energia, se chegar em 15 ele fica exausto
    protected int higiene; // Quanto maior, mais limpo, se chegar em 25 ele fica sujo
    protected int felicidade; // Quanto maior, mais feliz, se chegar em 25 ele fica triste
    protected int afeto; // vínculo com o dono
    protected boolean doente; // Se o bixo estiver doente, ele não pode brincar, comer ou fazer nada
    protected String humor; //Ex: "Feliz", "Cansado", "Com Fome", "Triste", "Morto (se energia, higiene e felicidade forem 0, ele 💀)"
}

    //construtor
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