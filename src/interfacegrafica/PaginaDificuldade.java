package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;

public class PaginaDificuldade extends JFrame {

    public PaginaDificuldade(CriaturaVirtual animal) {
        setTitle("Escolha a Dificuldade");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton facil = criarBotao("🟢 Fácil", 0, animal);
        JButton medio = criarBotao("🟡 Médio", 1, animal);
        JButton dificil = criarBotao("🔴 Difícil", 2, animal);

        painel.add(facil);
        painel.add(medio);
        painel.add(dificil);

        JButton cancelar = new JButton("❌ Cancelar");
        cancelar.addActionListener(e -> dispose());
        painel.add(cancelar);

        add(painel);
        setVisible(true);
    }

    private JButton criarBotao(String texto, int dificuldade, CriaturaVirtual animal) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.addActionListener(e -> {
            new PaginaJogo(animal, dificuldade); // Vai para a página do jogo
            dispose(); // Fecha a tela de escolha
        });
        return botao;
    }
}
