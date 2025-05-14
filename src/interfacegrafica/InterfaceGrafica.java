// Parte do Douglas
package interfacegrafica;
import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica {
    public static void main(String[] args){
        JFrame janela = new JFrame("Primeira Janela");
        janela.setSize(300, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        JButton botao = new JButton("Alimentar");
        JButton botao1 = new JButton("Banho");
        JButton botao2 = new JButton("Brincar");
        JButton botao3 = new JButton("Veterinária");

        painel.add(botao);
        painel.add(botao1);
        painel.add(botao2);
        painel.add(botao3);


        janela.add(painel);
        janela.setVisible(true);
    }
}