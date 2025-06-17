package modelo;

/**
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 6: Primeira relação de herança (Cachorro extends CriaturaVirtual)
 * REQUISITO 7: Sobrescreve métodos da superclasse
 * REQUISITO 8: Usada polimorficamente como CriaturaVirtual
 */
public class Cachorro extends CriaturaVirtual {
    
    public Cachorro(String nome) {
        super(nome);
        // Cachorros começam com mais energia e felicidade
        this.felicidade = Math.min(100, this.felicidade + 15);
        this.sono = Math.min(100, this.sono + 10);
    }

    /**
     * REQUISITO 4: Implementação de método abstrato
     */
    @Override
    public void emitirSom() {
        System.out.println(nome + " faz: Au au au! 🐶");
    }

    @Override
    public String getTipo() {
        return "Cachorro";
    }

    @Override
    public void habilidadeEspecial() {
        System.out.println(nome + " está abanando o rabo e saltitando de alegria!");
        System.out.println("A energia contagiante do cachorro espalha felicidade!");
        
        // Cachorros ganham muita felicidade e energia
        setFelicidade(Math.min(100, getFelicidade() + 15));
        setSono(Math.min(100, getSono() + 10));
        ganharPontos(5); // Bonus de pontos pela alegria
        
        System.out.println("Felicidade e energia aumentaram! +5 pontos bonus!");
    }    /**
     * REQUISITO 7: Método sobrescrito (não abstrato) - DEMONSTRAÇÃO DO REQUISITO 7
     * Este método mostra o polimorfismo em ação: cada animal tem comportamento único ao brincar
     * Cachorro: Ganha mais felicidade (+10) mas gasta mais energia (-8 sono)
     * Este método é chamado na interface e mostra comportamento específico para o usuário
     */
    @Override
    public void brincar() {
        super.brincar();
        // Cachorros adoram brincar e ficam muito felizes
        setFelicidade(Math.min(100, getFelicidade() + 10));
        System.out.println(nome + " correu, pulou e se divertiu muito!");
        
        // Mas gastam mais energia
        setSono(Math.max(0, getSono() - 8));
        if (getSono() < 30) {
            System.out.println(nome + " está ficando cansado de tanto brincar!");
        }
    }
}
