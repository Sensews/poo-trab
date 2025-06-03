package modelo;

public class Cachorro extends CriaturaVirtual {
    
    public Cachorro(String nome) {
        super(nome);
        // Cachorros têm características especiais
        this.energia = 120; // Cachorros têm mais energia
        this.felicidade = 90; // Começam mais felizes
        this.afeto = 10; // Muito afetuosos
    }

    @Override
    public void agir() {
        // Comportamento automático do cachorro
        if (Math.random() < 0.4) { // 40% chance
            System.out.println(nome + " está abanando o rabo!");
            felicidade = Math.min(100, felicidade + 3);
            afeto = Math.min(100, afeto + 1);
        } else if (Math.random() < 0.3) { // 30% chance adicional
            System.out.println(nome + " está correndo em círculos!");
            energia = Math.max(0, energia - 5);
            felicidade = Math.min(100, felicidade + 5);
        }
        atualizarHumor();
    }

    @Override
    public void brincar() {
        super.brincar();
        // Cachorros ganham mais afeto brincando
        afeto = Math.min(100, afeto + 2);
        System.out.println(nome + " brincou de buscar a bolinha!");
    }

    public String getTipo() {
        return "Cachorro";
    }
}
