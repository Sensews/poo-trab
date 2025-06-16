package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;

public class PaginaJogo extends JFrame {

    private final String[] palavrasFacil = {"bola", "gato", "casa", "pato", "carro"};
    private final String[] palavrasMedio = {"computador", "bicicleta", "elefante", "girassol", "janela"};
    private final String[] palavrasDificil = {"extraordinario", "responsabilidade", "desenvolvimento", "programacao", "hipopotamo"};

    private String[] palavrasEscolhidas; // Sequência fixa de palavras
    private int rodadaAtual = 0;
    private int pontos = 0;

    private JTextField campoResposta;
    private JLabel labelPalavra;
    private CriaturaVirtual animal;
    private int dificuldade;

    public PaginaJogo(CriaturaVirtual animal, int dificuldade) {
        this.animal = animal;
        this.dificuldade = dificuldade;

        setTitle("Mini-Game de Digitação");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        configurarPalavras(); // Carrega a sequência correta das palavras

        // Elementos visuais
        labelPalavra = new JLabel("Clique em COMEÇAR para ver a primeira palavra!", SwingConstants.CENTER);
        labelPalavra.setFont(new Font("Segoe UI", Font.BOLD, 18));

        campoResposta = new JTextField();
        campoResposta.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoConfirmar.addActionListener(e -> verificarResposta());

        JButton botaoComecar = new JButton("▶ Começar");
        botaoComecar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoComecar.addActionListener(e -> mostrarProximaPalavra());

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(labelPalavra, BorderLayout.NORTH);
        painel.add(campoResposta, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 2, 10, 10));
        botoes.add(botaoComecar);
        botoes.add(botaoConfirmar);

        painel.add(botoes, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);
    }

    /** Define a sequência fixa de palavras com base na dificuldade */
    private void configurarPalavras() {
        switch (dificuldade) {
            case 1 -> palavrasEscolhidas = palavrasFacil;
            case 2 -> palavrasEscolhidas = palavrasMedio;
            case 3 -> palavrasEscolhidas = palavrasDificil;
        }
    }

    /** Mostra a próxima palavra da lista, na ordem */
    private void mostrarProximaPalavra() {
        if (rodadaAtual < palavrasEscolhidas.length) {
            labelPalavra.setText("Digite: " + palavrasEscolhidas[rodadaAtual]);
            campoResposta.setText("");
            campoResposta.requestFocus();
        } else {
            fimDoJogo();
        }
    }

    /** Verifica a resposta digitada pelo usuário */
    private void verificarResposta() {
        if (rodadaAtual >= palavrasEscolhidas.length) {
            fimDoJogo();
            return;
        }

        String resposta = campoResposta.getText().trim();
        if (resposta.equalsIgnoreCase(palavrasEscolhidas[rodadaAtual])) {
            pontos++;
            JOptionPane.showMessageDialog(this, "✅ Correto!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Errado! A palavra era: " + palavrasEscolhidas[rodadaAtual]);
        }

        rodadaAtual++;

        if (rodadaAtual >= palavrasEscolhidas.length) {
            fimDoJogo();
        } else {
            mostrarProximaPalavra();
        }
    }

    /** Finaliza o jogo e aplica as recompensas corretas */
    private void fimDoJogo() {
        int recompensa = pontos * dificuldade;  // Igual ao terminal → recompensa proporcional à dificuldade
        animal.ganharPontos(recompensa);

        JOptionPane.showMessageDialog(this,
                "🏁 Fim do jogo!\n" +
                        "Pontos corretos: " + pontos + "\n" +
                        "Felicidade recebida: +" + recompensa);

        dispose(); // Fecha a janela
    }
}
