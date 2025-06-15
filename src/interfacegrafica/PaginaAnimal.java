package interfacegrafica;

import modelo.CriaturaVirtual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PaginaAnimal extends JFrame {
    private CriaturaVirtual animal;

    private JProgressBar barraFome;
    private JProgressBar barraSede;
    private JProgressBar barraSono;
    private JProgressBar barraFelicidade;
    private JProgressBar barraSaude;
    private JLabel labelHumor;

    public PaginaAnimal(CriaturaVirtual animal) {
        this.animal = animal;

        setTitle("Tamagotchi - " + animal.getNome());
        setSize(600, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cor de fundo padrão
        Color corFundo = new Color(245, 241, 230);
        Color corBotao = new Color(180, 140, 90);
        Color corTexto = new Color(70, 60, 50);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setLayout(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Título ---
        JLabel titulo = new JLabel("Seu Tamagotchi: " + animal.getNome());
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(corTexto);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // --- Parte gráfica das barras ---
        JPanel painelBarras = new JPanel();
        painelBarras.setLayout(new BoxLayout(painelBarras, BoxLayout.Y_AXIS));
        painelBarras.setBackground(corFundo);

        barraFome = criarBarra("Fome", painelBarras, corTexto);
        barraSede = criarBarra("Sede", painelBarras, corTexto);
        barraSono = criarBarra("Sono", painelBarras, corTexto);
        barraFelicidade = criarBarra("Felicidade", painelBarras, corTexto);
        barraSaude = criarBarra("Saúde", painelBarras, corTexto);

        labelHumor = new JLabel("Humor: " + animal.getHumor());
        labelHumor.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelHumor.setForeground(corTexto);
        labelHumor.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBarras.add(Box.createRigidArea(new Dimension(0, 10)));
        painelBarras.add(labelHumor);

        // --- Imagem ---
        String texto;
        if (DadosDoJogo.tipoTamagotchi.equals("Cachorro")) {
            texto = "imagens/cachorro.png";
        } else if (DadosDoJogo.tipoTamagotchi.equals("Gato")) {
            texto = "imagens/gato.png";
        } else {
            texto = "imagens/peixe.png";
        }

        JLabel imagemAnimal = carregarImagem(texto, 200, 200);
        imagemAnimal.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBackground(corFundo);
        painelCentro.add(imagemAnimal);
        painelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
        painelCentro.add(painelBarras);

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        // --- Botões ---
        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 15, 15));
        painelBotoes.setBackground(corFundo);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        painelBotoes.add(criarBotao("ALIMENTAR", corBotao));
        painelBotoes.add(criarBotao("DESCANSAR", corBotao));
        painelBotoes.add(criarBotao("BRINCAR", corBotao));
        painelBotoes.add(criarBotao("LOJA", corBotao));

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Atualização automática
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            animal.atualizacaoTempo();
            atualizarBarras();
        });
        timer.start();

        add(painelPrincipal);
        setVisible(true);
    }

    private static JLabel carregarImagem(String caminhoImagem, int largura, int altura) {
        try {
            ImageIcon icone = new ImageIcon(PaginaAnimal.class.getResource(caminhoImagem));
            Image imagemReduzida = icone.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(imagemReduzida));
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + caminhoImagem);
            return new JLabel();
        }
    }

    private JProgressBar criarBarra(String titulo, JPanel painel, Color corTexto) {
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(corTexto);
        painel.add(label);

        JProgressBar barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        barra.setPreferredSize(new Dimension(400, 20));
        barra.setForeground(new Color(100, 160, 120));
        barra.setBackground(new Color(230, 230, 220));
        painel.add(barra);
        painel.add(Box.createRigidArea(new Dimension(0, 8)));
        return barra;
    }

    private JButton criarBotao(String texto, Color corBotao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(corBotao);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        botao.setPreferredSize(new Dimension(150, 40));
        return botao;
    }

    private void atualizarBarras() {
        barraFome.setValue(animal.getFome());
        barraSede.setValue(animal.getSede());
        barraSono.setValue(animal.getSono());
        barraFelicidade.setValue(animal.getFelicidade());
        barraSaude.setValue(animal.getSaude());
        labelHumor.setText("Humor: " + animal.getHumor());
    }
}
