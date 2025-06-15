import modelo.JogoTamagochi;

public class Main {
    public static void main(String[] args) {
        List<CriaturaVirtual> criaturas = ImportadorTXT.importarDeTXT("criatura.txt");
        JogoTamagochi jogo = new JogoTamagochi();
        jogo.iniciarJogo();
    }
}
