package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;

public class PaginaJogo extends JFrame {

    private final String[] palavrasFacil = {"bola", "gato", "casa", "pato", "carro"};
    private final String[] palavrasMedio = {"computador", "bicicleta", "elefante", "girassol", "janela"};
    private final String[] palavrasDificil = {"extraordinario", "responsabilidade", "desenvolvimento", "programacao", "hipopotamo"};

    private String[] palavrasEscolhidas;
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
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        configurarPalavras();

        Color corFundo = new Color(245, 241, 230);
        Color corTexto = new Color(70, 60, 50);

        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(corFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        labelPalavra = new JLabel("Clique em COMEÇAR para ver a primeira palavra!", SwingConstants.CENTER);
        labelPalavra.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelPalavra.setForeground(corTexto);
        painel.add(labelPalavra, BorderLayout.NORTH);

        campoResposta = new JTextField();
        campoResposta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campoResposta.setColumns(15);  // 👈 Define largura menor para o campo
        campoResposta.addActionListener(e -> verificarResposta()); // 👈 ENTER chama verificarResposta()
        painel.add(campoResposta, BorderLayout.CENTER);

        JButton botaoConfirmar = Metodos.criarBotao("Confirmar");
        botaoConfirmar.addActionListener(e -> verificarResposta());

        JButton botaoComecar = Metodos.criarBotao("▶ Começar");
        botaoComecar.addActionListener(e -> mostrarProximaPalavra());

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        painelBotoes.setBackground(corFundo);
        painelBotoes.add(botaoComecar);
        painelBotoes.add(botaoConfirmar);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);
    }

    private void configurarPalavras() {
        switch (dificuldade) {
            case 0 -> palavrasEscolhidas = palavrasFacil;
            case 1 -> palavrasEscolhidas = palavrasMedio;
            case 2 -> palavrasEscolhidas = palavrasDificil;
            default -> palavrasEscolhidas = palavrasFacil;
        }
    }

    private void mostrarProximaPalavra() {
        if (rodadaAtual < palavrasEscolhidas.length) {
            labelPalavra.setText("Digite: " + palavrasEscolhidas[rodadaAtual]);
            campoResposta.setText("");
            campoResposta.requestFocus();
        } else {
            fimDoJogo();
        }
    }

    private void verificarResposta() {
        if (rodadaAtual >= palavrasEscolhidas.length) {
            fimDoJogo();
            return;
        }

        String resposta = campoResposta.getText().trim();
        if (resposta.equalsIgnoreCase(palavrasEscolhidas[rodadaAtual])) {
            pontos++;
            JOptionPane.showMessageDialog(this, "Correto!");
        } else {
            JOptionPane.showMessageDialog(this, "Errado! A palavra era: " + palavrasEscolhidas[rodadaAtual]);
        }

        rodadaAtual++;
        mostrarProximaPalavra();
    }

    private void fimDoJogo() {
        int recompensa = pontos * (dificuldade + 2);
        animal.ganharPontos(recompensa);

        JOptionPane.showMessageDialog(this,
                "Fim do jogo!\n" +
                        "Pontos corretos: " + pontos + "\n" +
                        "Felicidade recebida: +" + recompensa);

        dispose();
    }
}
