package modelo;

public class Gato extends CriaturaVirtual {
    
    public Gato(String nome) {
        super(nome);
        // Gatos têm características especiais
        this.higiene = 90; // Gatos são mais limpos
        this.afeto = 5; // Começam com um pouco mais de afeto
    }

    @Override
    public void agir() {
        // Comportamento automático do gato
        if (Math.random() < 0.3) { // 30% chance
            System.out.println(nome + " está se lambendo (higiene +5)");
            higiene = Math.min(100, higiene + 5);
        } else if (Math.random() < 0.5) { // 20% chance adicional
            System.out.println(nome + " está ronronando de felicidade");
            felicidade = Math.min(100, felicidade + 3);
        }
        atualizarHumor();
    }

    @Override
    public void brincar() {
        super.brincar();
        // Gatos ganham mais felicidade brincando
        felicidade = Math.min(100, felicidade + 5);
        System.out.println(nome + " brincou com uma bolinha de lã!");
    }

    public String getTipo() {
        return "Gato";
    }
}
