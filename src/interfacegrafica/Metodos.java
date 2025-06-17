package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;

public class Metodos {
    public static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(180, 140, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setMaximumSize(new Dimension(150, 40));
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botao;
    }
    public static JLabel carregarImagem(String caminhoImagem) {
        try {
            ImageIcon icone = new ImageIcon(TipoAnimal.class.getResource(caminhoImagem));
            Image imagemReduzida = icone.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(imagemReduzida));
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + caminhoImagem);
            return new JLabel(); // retorna vazio se não achar
        }
    }
}
