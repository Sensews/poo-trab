package modelo;

public class Peixe extends CriaturaVirtual {
    
    public Peixe(String nome) {
        super(nome);
        // Peixes têm características especiais
        this.higiene = 100; // Sempre limpos na água
        this.energia = 80; // Menos energéticos
        this.felicidade = 70; // Mais zen
    }

    @Override
    public void agir() {
        // Comportamento automático do peixe
        if (Math.random() < 0.5) { // 50% chance
            System.out.println(nome + " está nadando tranquilamente...");
            energia = Math.min(100, energia + 2);
        } else if (Math.random() < 0.3) { // 30% chance adicional
            System.out.println(nome + " fez bolhinhas na água!");
            felicidade = Math.min(100, felicidade + 2);
        }
        atualizarHumor();
    }

    @Override
    public void banho() {
        // Peixes não precisam de banho, sempre limpos
        System.out.println(nome + " já está sempre limpo na água!");
        felicidade = Math.min(100, felicidade + 5);
        atualizarHumor();
    }

    @Override
    public void brincar() {
        System.out.println(nome + " nadou em círculos brincando!");
        felicidade = Math.min(100, felicidade + 10);
        energia = Math.max(0, energia - 5);
        afeto += 1;
        atualizarHumor();
    }

    public String getTipo() {
        return "Peixe";
    }
}
