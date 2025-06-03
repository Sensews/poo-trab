package modelo;

public class Gato extends CriaturaVirtual {
    
    public Gato(String nome) {
        super(nome);
        // Gatos começam com mais felicidade
        this.felicidade = Math.min(100, this.felicidade + 10);
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Miau miau! 🐱");
    }

    @Override
    public String getTipo() {
        return "Gato";
    }

    @Override
    public void habilidadeEspecial() {
        System.out.println(nome + " está se lambendo e ficando mais limpo!");
        System.out.println("Gatos são naturalmente higiênicos!");
        
        // Melhora um pouco a saúde e felicidade
        setSaude(Math.min(100, getSaude() + 5));
        setFelicidade(Math.min(100, getFelicidade() + 8));
        
        System.out.println("Saúde e felicidade aumentaram!");
    }

    @Override
    public void brincar() {
        super.brincar();
        // Gatos ganham mais felicidade brincando
        setFelicidade(Math.min(100, getFelicidade() + 5));
        System.out.println(nome + " brincou com uma bolinha de lã e adorou!");
    }
}
