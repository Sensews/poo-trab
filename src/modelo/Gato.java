package modelo;

/**
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 6: Segunda relação de herança (Gato extends CriaturaVirtual)
 * REQUISITO 7: Sobrescreve métodos da superclasse
 * REQUISITO 8: Usada polimorficamente como CriaturaVirtual
 */
public class Gato extends CriaturaVirtual {
    
    public Gato(String nome) {
        super(nome);
        // Gatos começam com mais felicidade
        this.felicidade = Math.min(100, this.felicidade + 10);
    }

    /**
     * REQUISITO 4: Implementação de método abstrato
     */
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
    }    /**
     * REQUISITO 7: Método sobrescrito (não abstrato) - DEMONSTRAÇÃO DO REQUISITO 7
     * Este método mostra o polimorfismo em ação: cada animal tem comportamento único ao brincar
     * Gato: Ganha felicidade moderada (+5) de forma elegante e controlada
     * Este método é chamado na interface e mostra comportamento específico para o usuário
     */
    @Override
    public void brincar() {
        super.brincar();
        // Gatos ganham mais felicidade brincando
        setFelicidade(Math.min(100, getFelicidade() + 5));
        System.out.println(nome + " brincou com uma bolinha de lã e adorou!");
    }
}
