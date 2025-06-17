package modelo;

public class Peixe extends CriaturaVirtual {
    
    public Peixe(String nome) {
        super(nome);
        // Peixes começam mais zen e com menos sede (já estão na água)
        this.felicidade = Math.min(100, this.felicidade + 5);
        this.sede = 100; // Sempre hidratados
    }

    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: *bolhas na água* 🐠");
    }

    @Override
    public String getTipo() {
        return "Peixe";
    }

    @Override
    public void habilidadeEspecial() {
        System.out.println(nome + " está nadando em movimentos zen e relaxantes...");
        System.out.println("A tranquilidade do peixe traz paz e restaura a energia!");
        
        // Peixes restauram sono e sede, e ganham felicidade pela paz
        setSono(Math.min(100, getSono() + 15));
        setSede(100); // Sempre hidratados
        setFelicidade(Math.min(100, getFelicidade() + 10));
        setSaude(Math.min(100, getSaude() + 5));
        
        System.out.println("Energia restaurada! Sede saciada! Paz interior alcançada!");
    }    /**
     * REQUISITO 7: Método sobrescrito (não abstrato) - DEMONSTRAÇÃO DO REQUISITO 7
     * Este método mostra o polimorfismo em ação: cada animal tem comportamento único ao brincar
     * Peixe: Ganha felicidade (+8) gastando pouca energia (-3 sono) de forma tranquila
     * Este método é chamado na interface e mostra comportamento específico para o usuário
     */
    @Override
    public void brincar() {
        super.brincar();
        System.out.println(nome + " nadou em círculos brincando graciosamente!");
          // Peixes gastam pouca energia brincando
        setSono(Math.max(0, getSono() - 3));
        setFelicidade(Math.min(100, getFelicidade() + 8));
    }
}
