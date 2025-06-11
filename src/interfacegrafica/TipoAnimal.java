package interfacegrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipoAnimal {

    public static void main(String[] args) {
        JFrame janela = new JFrame("Escolha o seu Tamagotchi");
        janela.setSize(500, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // centraliza

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 241, 230));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Escolha seu animal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(70, 60, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        painel.add(criarItemAnimal("imagens/cachorro.png", "Cachorro"));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemAnimal("imagens/gato.png", "Gato"));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemAnimal("imagens/peixe.png", "Peixe"));

        janela.add(painel);
        janela.setVisible(true);
    }

    private static JPanel criarItemAnimal(String caminhoImagem, String nomeAnimal) {
        JPanel painelAnimal = new JPanel();
        painelAnimal.setLayout(new BoxLayout(painelAnimal, BoxLayout.X_AXIS));
        painelAnimal.setBackground(new Color(245, 241, 230));
        painelAnimal.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Carrega a imagem
        try {
            ImageIcon icone = new ImageIcon(TipoAnimal.class.getResource(caminhoImagem));
            Image imagemRedimensionada = icone.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            painelAnimal.add(labelImagem);
            painelAnimal.add(Box.createRigidArea(new Dimension(15, 0)));

        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + caminhoImagem);
        }

        JButton botao = new JButton(nomeAnimal);
        botao.setBackground(new Color(180, 140, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setMaximumSize(new Dimension(150, 40));

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DadosDoJogo.tipoTamagotchi = nomeAnimal; // Salva o tipo escolhido
                System.out.println("Animal escolhido: " + nomeAnimal); // Teste - pode abrir outra tela depois
                // Aqui você pode abrir a próxima tela
                // NovaTela.main(null);
                // Exemplo: janela.dispose();
            }
        });

        painelAnimal.add(botao);
        return painelAnimal;
    }
}
