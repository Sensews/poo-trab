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

        JLabel label = new JLabel("Bemi-vindo ao nosso jogo");

        //uma logo pro trabalho, ainda não decidida

        JLabel label_descricao = new JLabel("(leve descrição sobre o jogo tamagotchi)");

        JButton botao = new JButton("Iniciar");


        janela.add(label);
        janela.add(label_descricao);
        janela.add(botao);
        janela.add(painel);
        janela.setVisible(true);
    }
}
