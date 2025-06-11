// Parte do Douglas
package interfacegrafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pagina_Nome {
    public static void main(String[] args){
        JFrame janela = new JFrame("Primeira Janela");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); //Está centralizando a janela
        janela.getContentPane().setBackground(new Color(245, 241, 230)); // Cor de fundo da janela

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(245, 241, 230)); // Cor de fundo do painel

        JLabel label = new JLabel("Dê um nome ao seu Tamagotchi:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.ITALIC, 26));
        label.setForeground(new Color(70, 60, 50));

        JTextField campoNome = new JTextField();
        campoNome.setMaximumSize(new Dimension(300, 40));
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o campo de Texto
        campoNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        campoNome.setBackground(new Color(235, 225, 210));
        campoNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 140, 90), 2),
                BorderFactory.createEmptyBorder(8, 10, 8, 10) // Espaço interno
        ));


        JButton botao = new JButton("Confirmar");
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setBackground(new Color(180, 140, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(150, 45));
        botao.setMaximumSize(new Dimension(200, 50));
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DadosDoJogo.nomeTamagotchi = campoNome.getText(); // SALVA O NOME
                TipoAnimal.main(null); // abre a próxima página
                janela.dispose();
            }
        });

        painel.add(Box.createVerticalStrut(30));
        painel.add(label);

        painel.add(Box.createVerticalStrut(20));
        painel.add(campoNome);

        painel.add(Box.createVerticalStrut(20));
        painel.add(botao);

        janela.add(painel);
        janela.setVisible(true);
    }
}