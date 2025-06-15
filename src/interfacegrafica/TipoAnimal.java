package interfacegrafica;

import javax.swing.*;
import java.awt.*;
import modelo.Cachorro;
import modelo.Peixe;
import modelo.Gato;

public class TipoAnimal {

    public static void main(String[] args) {
        JFrame janela = new JFrame("Escolha o seu Tamagotchi");
        janela.setSize(500, 550);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // centraliza

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 241, 230));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Escolha seu animal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 60, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ==============================
        // Cachorro
        JPanel painelCachorro = new JPanel();
        painelCachorro.setLayout(new BoxLayout(painelCachorro, BoxLayout.X_AXIS));
        painelCachorro.setBackground(new Color(245, 241, 230));
        painelCachorro.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imagemCachorro = carregarImagem("imagens/cachorro.png");
        painelCachorro.add(imagemCachorro);
        painelCachorro.add(Box.createRigidArea(new Dimension(15, 0)));

        JButton botaoCachorro = criarBotao("Cachorro");
        botaoCachorro.addActionListener(e -> {
            System.out.println("Animal escolhido: Cachorro | Nome: " + DadosDoJogo.nomeTamagotchi);
            DadosDoJogo.tipoTamagotchi = "Cachorro";
            DadosDoJogo.criatura = new Cachorro(DadosDoJogo.nomeTamagotchi);
            new PaginaAnimal(DadosDoJogo.criatura);
            janela.dispose();
        });
        painelCachorro.add(botaoCachorro);

        painel.add(painelCachorro);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==============================
        // Gato
        JPanel painelGato = new JPanel();
        painelGato.setLayout(new BoxLayout(painelGato, BoxLayout.X_AXIS));
        painelGato.setBackground(new Color(245, 241, 230));
        painelGato.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imagemGato = carregarImagem("imagens/gato.png");
        painelGato.add(imagemGato);
        painelGato.add(Box.createRigidArea(new Dimension(15, 0)));

        JButton botaoGato = criarBotao("Gato");
        botaoGato.addActionListener(e -> {
            System.out.println("Animal escolhido: Gato | Nome: " + DadosDoJogo.nomeTamagotchi);
            DadosDoJogo.tipoTamagotchi = "Gato";
            DadosDoJogo.criatura = new Cachorro(DadosDoJogo.nomeTamagotchi);
            SwingUtilities.invokeLater(() -> {
                new PaginaAnimal(DadosDoJogo.criatura);
            });
            janela.dispose();
        });
        painelGato.add(botaoGato);

        painel.add(painelGato);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ==============================
        // Peixe
        JPanel painelPeixe = new JPanel();
        painelPeixe.setLayout(new BoxLayout(painelPeixe, BoxLayout.X_AXIS));
        painelPeixe.setBackground(new Color(245, 241, 230));
        painelPeixe.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imagemPeixe = carregarImagem("imagens/peixe.png");
        painelPeixe.add(imagemPeixe);
        painelPeixe.add(Box.createRigidArea(new Dimension(15, 0)));

        JButton botaoPeixe = criarBotao("Peixe");
        botaoPeixe.addActionListener(e -> {
            System.out.println("Animal escolhido: Peixe | Nome: " + DadosDoJogo.nomeTamagotchi);
            DadosDoJogo.tipoTamagotchi = "Peixe";
            DadosDoJogo.criatura = new Peixe(DadosDoJogo.nomeTamagotchi);
            new PaginaAnimal(DadosDoJogo.criatura);
            janela.dispose();
        });
        painelPeixe.add(botaoPeixe);

        painel.add(painelPeixe);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ==============================
        janela.add(painel);
        janela.setVisible(true);
    }

    // Função auxiliar só para carregar a imagem e redimensionar
    private static JLabel carregarImagem(String caminhoImagem) {
        try {
            ImageIcon icone = new ImageIcon(TipoAnimal.class.getResource(caminhoImagem));
            Image imagemReduzida = icone.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(imagemReduzida));
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + caminhoImagem);
            return new JLabel(); // retorna vazio se não achar
        }
    }

    // Função auxiliar para criar botão padrão
    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(180, 140, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setMaximumSize(new Dimension(150, 40));
        return botao;
    }
}
