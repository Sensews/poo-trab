// Parte do Douglas
package interfacegrafica;
import javax.swing.JFrame;

public class InterfaceGrafica {
    public static void main(String[] args){
        JFrame janela = new JFrame();

        janela.setSize(300, 200);
        janela.setBounds(100, 100, 300, 200);

        janela.setTitle("Minha Janela");
        janela.setVisible(true);
    }
}