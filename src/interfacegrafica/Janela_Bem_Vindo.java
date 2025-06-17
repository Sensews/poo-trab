package interfacegrafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela_Bem_Vindo {
    public static void inicializarInterface(){
        JFrame janela = new JFrame("Tamagotchi - Bem-vindo");
        janela.setSize(600, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); //Está centralizando a janela

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 241, 230)); // bege claro
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // layout vertical
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margem

        JLabel label = new JLabel("Bem-vindo ao nosso jogo");
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(new Color(70, 60, 50)); // cor marrom escuro
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        //uma logo pro trabalho, ainda não decidida
        try {
            ImageIcon logo = new ImageIcon(Janela_Bem_Vindo.class.getResource("imagens/logo.png")); // coloque a imagem 'logo.png' na mesma pasta do projeto
            Image imagemRedimensionada = logo.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(labelImagem);
        } catch (Exception e) {
            System.out.println("Imagem não encontrada!");
        }
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel labelDescricao = new JLabel("Cuide do seu Tamagotchi e divirta-se!");
        label.setFont(new Font("Serif", Font.ITALIC, 28));
        labelDescricao.setForeground(new Color(90, 85, 80));
        labelDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(labelDescricao);
        painel.add(Box.createVerticalGlue()); // empurra o botão pra parte de baixo

        JButton botao = Metodos.criarBotao("Iniciar");
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pagina_Nome.main(null); // abre a próxima página
                janela.dispose(); // fecha a janela atual
            }
        });

        painel.add(botao);
        janela.add(painel);
        janela.setVisible(true);
    }
    public static void main(String[] args) {
        inicializarInterface();
    }
}
