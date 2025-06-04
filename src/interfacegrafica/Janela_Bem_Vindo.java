package interfacegrafica;
import javax.swing.*;
import java.awt.*;
public class Janela_Bem_Vindo {
    public static void main(String []args){
        JFrame janela = new JFrame("Primeira Janela");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); //Está centralizando a janela

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 241, 230)); // bege claro
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // layout vertical
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // margem

        JLabel label = new JLabel("Bemi-vindo ao nosso jogo");
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(new Color(70, 60, 50)); // cor marrom escuro
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        //uma logo pro trabalho, ainda não decidida

        JLabel label_descricao = new JLabel("(Uma leve descrição sobre o jogo Tamagotchi)");
        label_descricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label_descricao.setForeground(new Color(90, 85, 80));
        label_descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(label_descricao);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton botao = new JButton("Iniciar");
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setBackground(new Color(180, 160, 120)); // tom bege/marrom suave
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(120, 40));
        botao.setMaximumSize(new Dimension(150, 50));

        painel.add(botao);
        janela.add(painel);
        janela.setVisible(true);
    }
}
