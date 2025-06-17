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
    private JLabel labelDoenca;

    public PaginaAnimal(CriaturaVirtual animal) {
        this.animal = animal;

        setTitle("Tamagotchi - " + animal.getNome());
        setSize(600, 725);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundo = new Color(245, 241, 230);
        Color corTexto = new Color(70, 60, 50);

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Seu Tamagotchi: " + animal.getNome());
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(corTexto);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelBarras = new JPanel();
        painelBarras.setLayout(new BoxLayout(painelBarras, BoxLayout.Y_AXIS));
        painelBarras.setBackground(corFundo);

        barraFome = criarBarra("Fome", painelBarras, corTexto);
        barraSede = criarBarra("Sede", painelBarras, corTexto);
        barraSono = criarBarra("Sono", painelBarras, corTexto);
        barraFelicidade = criarBarra("Felicidade", painelBarras, corTexto);
        barraSaude = criarBarra("Saúde", painelBarras, corTexto);

        labelHumor = criarLabel("Humor: " + animal.getHumor(), corTexto);
        painelBarras.add(criarEspacamentoVertical(10));
        painelBarras.add(labelHumor);

        labelDoenca = criarLabel("Doença: " + (animal.getTipoDoenca() != null ? animal.getTipoDoenca() : "Nenhuma"), corTexto);
        painelBarras.add(criarEspacamentoVertical(5));
        painelBarras.add(labelDoenca);

        JLabel imagemAnimal = Metodos.carregarImagem(getCaminhoImagem());
        imagemAnimal.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBackground(corFundo);
        painelCentro.add(Box.createRigidArea(new Dimension(0, 5))); // Diminuído para 5
        painelCentro.add(imagemAnimal);
        painelCentro.add(Box.createRigidArea(new Dimension(0, 10))); // Diminuído para 10
        painelCentro.add(painelBarras);

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corFundo);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        JButton botaoAlimentar = Metodos.criarBotao("ALIMENTAR");
        botaoAlimentar.addActionListener(e -> {
            new PaginaInventario(animal);
            atualizarBarras();
        });

        JButton botaoBrincar = Metodos.criarBotao("BRINCAR");
        botaoBrincar.addActionListener(e -> {
            if (verificarSeEstaVivo()) {
                animal.brincar();
                atualizarBarras();
            }
        });

        JButton botaoLoja = Metodos.criarBotao("LOJA");
        botaoLoja.addActionListener(e -> new PaginaLoja(animal));

        JButton botaoMinigame = Metodos.criarBotao("MINIGAME");
        botaoMinigame.addActionListener(e -> new PaginaDificuldade(animal));

        JButton botaoDescansar = Metodos.criarBotao("DESCANSAR");
        botaoDescansar.addActionListener(e -> {
            animal.dormir();
            atualizarBarras();

            botaoAlimentar.setEnabled(false);
            botaoDescansar.setEnabled(false);
            botaoBrincar.setEnabled(false);
            botaoLoja.setEnabled(false);
            botaoMinigame.setEnabled(false);

            Timer temporizador = new Timer(10000, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    botaoAlimentar.setEnabled(true);
                    botaoDescansar.setEnabled(true);
                    botaoBrincar.setEnabled(true);
                    botaoLoja.setEnabled(true);
                    botaoMinigame.setEnabled(true);
                }
            });
            temporizador.setRepeats(false);
            temporizador.start();
        });

        painelBotoes.add(criarLinhaBotoes(botaoAlimentar, botaoDescansar));
        painelBotoes.add(criarEspacamentoVertical(15));
        painelBotoes.add(criarLinhaBotoes(botaoBrincar, botaoLoja));
        painelBotoes.add(criarEspacamentoVertical(15));
        painelBotoes.add(criarLinhaCentralizada(botaoMinigame));

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, (ActionEvent e) -> {
            animal.atualizacaoTempo();
            atualizarBarras();
        });
        timer.start();

        add(painelPrincipal);
        setVisible(true);
    }

    private JLabel criarLabel(String texto, Color corTexto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(corTexto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private String getCaminhoImagem() {
        return switch (DadosDoJogo.tipoTamagotchi) {
            case "Cachorro" -> "imagens/cachorro.png";
            case "Gato" -> "imagens/gato.png";
            case "Peixe" -> "imagens/peixe.png";
            default -> null;
        };
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
        painel.add(criarEspacamentoVertical(8));
        return barra;
    }
    private JPanel criarLinhaBotoes(JButton botao1, JButton botao2) {
        JPanel linha = new JPanel();
        linha.setBackground(new Color(245, 241, 230));
        linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
        linha.add(botao1);
        linha.add(Box.createRigidArea(new Dimension(20, 0)));
        linha.add(botao2);
        return linha;
    }

    private JPanel criarLinhaCentralizada(JButton botao) {
        JPanel linha = new JPanel();
        linha.setBackground(new Color(245, 241, 230));
        linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
        linha.add(Box.createHorizontalGlue());
        linha.add(botao);
        linha.add(Box.createHorizontalGlue());
        return linha;
    }

    private Component criarEspacamentoVertical(int altura) {
        return Box.createRigidArea(new Dimension(0, altura));
    }

    private void atualizarBarras() {
        barraFome.setValue(animal.getFome());
        barraSede.setValue(animal.getSede());
        barraSono.setValue(animal.getSono());
        barraFelicidade.setValue(animal.getFelicidade());
        barraSaude.setValue(animal.getSaude());
        labelHumor.setText("Humor: " + animal.getHumor());
        labelDoenca.setText("Doença: " + (animal.getTipoDoenca() != null ? animal.getTipoDoenca() : "Nenhuma"));
    }

    private boolean verificarSeEstaVivo() {
        if (!animal.isVivo()) {
            JOptionPane.showMessageDialog(this, "Seu Tamagotchi morreu. Reinicie o jogo para começar novamente.");
            return false;
        }
        return true;
    }
}