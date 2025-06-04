// Parte do Douglas
package interfacegrafica;
import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica {
    public static void main(String[] args){
        JFrame janela = new JFrame("Primeira Janela");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); //Está centralizando a janela
        janela.getContentPane().setBackground(new Color(240, 240, 240)); // Cor de fundo da janela

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(240, 240, 240)); // Cor de fundo do painel

        JLabel label = new JLabel("Dê um nome ao seu tamagotchi:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto

        Font fonte = new Font("Arial", Font.BOLD, 16);
        label.setFont(fonte);

        JTextField campoNome = new JTextField();
        campoNome.setMaximumSize(new Dimension(300, 30));
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o campo de Texto
        campoNome.setFont(new Font("Arial", Font.PLAIN, 14));

        campoNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Espaço interno
        ));

        JButton botao = new JButton("Confirmar");
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o Botão
        botao.setFont(new Font("Arial", Font.BOLD, 14));

        painel.add(Box.createVerticalStrut(20));
        painel.add(label);

        painel.add(Box.createVerticalStrut(10));
        painel.add(campoNome);

        painel.add(Box.createVerticalStrut(10));
        painel.add(botao);

        janela.add(painel);
        janela.setVisible(true);
    }
}