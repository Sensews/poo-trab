package interfacegrafica;

import modelo.CriaturaVirtual;
import modelo.ItemComida;
import modelo.LojaComidas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PaginaLoja extends JFrame {
    private CriaturaVirtual animal;
    private LojaComidas lojaComidas;

    private JLabel labelPontos;
    private JPanel painelItens;

    public PaginaLoja(CriaturaVirtual animal) {
        this.animal = animal;
        this.lojaComidas = new LojaComidas();

        setTitle("Loja de Comidas");
        setSize(600, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundo = new Color(230, 210, 190);
        Color corTexto = new Color(60, 40, 20);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Loja de Comidas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(corTexto);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Exibir pontos atuais
        labelPontos = new JLabel("Pontos disponíveis: " + animal.getPontos());
        labelPontos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelPontos.setForeground(corTexto);
        labelPontos.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(labelPontos, BorderLayout.SOUTH);

        // Painel com lista de itens
        painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        painelItens.setBackground(corFundo);

        JScrollPane scrollPane = new JScrollPane(painelItens);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); //Aumentar a velocidade da rolagem
        scrollPane.setBorder(null);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        carregarItens();

        JButton botaoSair = Metodos.criarBotao("SAIR");
        botaoSair.addActionListener(e -> dispose());

        painelItens.add(Box.createRigidArea(new Dimension(0, 10)));
        painelItens.add(botaoSair);

        add(painelPrincipal);
        setVisible(true);
    }

    private void carregarItens() {
        for (int i = 0; i < lojaComidas.getQuantidadeComidas(); i++) {
            ItemComida comida = lojaComidas.getComida(i);

            JPanel itemPanel = new JPanel(new BorderLayout(10, 10));
            itemPanel.setBackground(new Color(240, 225, 200));
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(180, 140, 90), 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel nomeComida = new JLabel(comida.getNome() + " - " + comida.getPreco() + " pts");
            nomeComida.setFont(new Font("Segoe UI", Font.BOLD, 16));

            JTextArea descricao = new JTextArea(comida.getDescricao() + "\n"
                    + "🍖 Fome +" + comida.getEfeitoFome() + " | 💧 Sede +" + comida.getEfeitoSede()
                    + " | 😀 Felicidade +" + comida.getEfeitoFelicidade()
                    + " | ❤️ Saúde +" + comida.getEfeitoSaude());
            descricao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            descricao.setEditable(false);
            descricao.setBackground(new Color(240, 225, 200));

            JButton botaoComprar = Metodos.criarBotao("Comprar");
            botaoComprar.addActionListener((ActionEvent e) -> comprarComida(comida));

            itemPanel.add(nomeComida, BorderLayout.NORTH);
            itemPanel.add(descricao, BorderLayout.CENTER);
            itemPanel.add(botaoComprar, BorderLayout.EAST);

            painelItens.add(itemPanel);
            painelItens.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private void comprarComida(ItemComida comida) {
        String input = JOptionPane.showInputDialog(this,
                "Quantas unidades deseja comprar?", "Compra de " + comida.getNome(),
                JOptionPane.PLAIN_MESSAGE);

        if (input == null) return; // Cancelado
        int quantidade;
        try {
            quantidade = Integer.parseInt(input);
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Informe um valor válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int custoTotal = quantidade * comida.getPreco();

        if (animal.getPontos() < custoTotal) {
            JOptionPane.showMessageDialog(this,
                    "❌ Pontos insuficientes!\nCusto: " + custoTotal + " pontos\nVocê tem: " + animal.getPontos(),
                    "Erro na compra", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmar compra
        int confirmar = JOptionPane.showConfirmDialog(this,
                "Confirmar compra de " + quantidade + " x " + comida.getNome() + " por " + custoTotal + " pontos?",
                "Confirmação de Compra", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            animal.gastarPontos(custoTotal);
            for (int i = 0; i < quantidade; i++) {
                animal.getInventario().adicionarComida(new ItemComida(
                        comida.getNome(), comida.getPreco(), comida.getEfeitoFome(),
                        comida.getEfeitoSede(), comida.getEfeitoFelicidade(), comida.getEfeitoSaude(),
                        comida.getDescricao()
                ));
            }
            JOptionPane.showMessageDialog(this, "✅ Compra realizada com sucesso!");
            atualizarPontos();
        }
    }

    private void atualizarPontos() {
        labelPontos.setText("Pontos disponíveis: " + animal.getPontos());
    }
}
