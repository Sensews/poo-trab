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
        setSize(600, 800);
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
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
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
        } else if (DadosDoJogo.tipoTamagotchi.equals("Peixe")) {
            texto = "imagens/peixe.png";
        }else {
            texto = "Imagem não encontrada";
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
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Botões
        JButton botaoAlimentar = criarBotao("ALIMENTAR");
        botaoAlimentar.addActionListener(e -> new PaginaInventario(animal));

        JButton botaoDescansar = criarBotao("DESCANSAR");
        botaoDescansar.addActionListener(e -> animal.dormir());

        JButton botaoBrincar = criarBotao("BRINCAR");
        botaoBrincar.addActionListener(e -> animal.brincar());

        JButton botaoLoja = criarBotao("LOJA");
        botaoLoja.addActionListener(e -> new PaginaLoja(animal));

        JButton botaoMinigame =  criarBotao("MINIGAME");
        botaoMinigame.addActionListener(e -> new PaginaDificuldade(animal));


        // Linha 1
        JPanel linha1 = new JPanel();
        linha1.setBackground(corFundo);
        linha1.setLayout(new BoxLayout(linha1, BoxLayout.X_AXIS));
        linha1.add(botaoAlimentar);
        linha1.add(Box.createRigidArea(new Dimension(20, 0)));
        linha1.add(botaoDescansar);

        // Linha 2
        JPanel linha2 = new JPanel();
        linha2.setBackground(corFundo);
        linha2.setLayout(new BoxLayout(linha2, BoxLayout.X_AXIS));
        linha2.add(botaoBrincar);
        linha2.add(Box.createRigidArea(new Dimension(20, 0)));
        linha2.add(botaoLoja);

        // Linha 3 → MiniGame (sozinho, centralizado)
        JPanel linha3 = new JPanel();
        linha3.setBackground(corFundo);
        linha3.setLayout(new BoxLayout(linha3, BoxLayout.X_AXIS));
        linha3.add(Box.createHorizontalGlue());
        linha3.add(botaoMinigame);
        linha3.add(Box.createHorizontalGlue());


        painelBotoes.add(linha1);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 15)));
        painelBotoes.add(linha2);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 15)));
        painelBotoes.add(linha3);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        Timer timer = new Timer(1500, (ActionEvent e) -> {
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

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(180, 140, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setMaximumSize(new Dimension(150, 40));
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
