package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;

public class PaginaDificuldade extends JFrame {

    public PaginaDificuldade(CriaturaVirtual animal) {
        setTitle("Escolha a Dificuldade");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🎨 Paleta de cores harmônica
        Color corFundo = new Color(245, 241, 230);      // Bege claro
        Color corBotao = new Color(150, 110, 90);       // Marrom
        Color corTexto = new Color(70, 60, 50);         // Marrom escuro

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Escolha a Dificuldade");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(corTexto);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.setLayout(new GridLayout(4, 1, 12, 12));

        JButton facil = criarBotao("Fácil", 0, animal, corBotao);
        JButton medio = criarBotao("Médio", 1, animal, corBotao);
        JButton dificil = criarBotao("Difícil", 2, animal, corBotao);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelar.setBackground(new Color(180, 80, 80));
        cancelar.setForeground(Color.WHITE);
        cancelar.setFocusPainted(false);
        cancelar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        cancelar.addActionListener(e -> dispose());

        painelBotoes.add(facil);
        painelBotoes.add(medio);
        painelBotoes.add(dificil);
        painelBotoes.add(cancelar);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        add(painelPrincipal);
        setVisible(true);
    }

    private JButton criarBotao(String texto, int dificuldade, CriaturaVirtual animal, Color corBotao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(corBotao);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botao.addActionListener(e -> {
            new PaginaJogo(animal, dificuldade);
            dispose();
        });
        return botao;
    }
}
