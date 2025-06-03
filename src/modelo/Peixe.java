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
    }

    @Override
    public void brincar() {
        super.brincar();
        System.out.println(nome + " nadou em círculos brincando graciosamente!");
        
        // Peixes gastam pouca energia brincando
        setSono(Math.max(0, getSono() - 3));
        setFelicidade(Math.min(100, getFelicidade() + 8));
    }

    @Override
    public void darAgua() {
        // Peixes não precisam beber água, já estão nela
        System.out.println(nome + " já está sempre hidratado na água!");
        setFelicidade(Math.min(100, getFelicidade() + 3));
        setSede(100);
    }
}
