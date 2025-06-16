package interfacegrafica;

import modelo.CriaturaVirtual;
import modelo.Inventario;
import modelo.ItemComida;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaginaInventario extends JFrame {
    private Inventario inventario;
    private CriaturaVirtual animal;
    private JPanel painelItens;

    public PaginaInventario(CriaturaVirtual animal) {
        this.animal = animal;
        this.inventario = animal.getInventario();

        setTitle("Inventário de Comidas");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color corFundo = new Color(245, 241, 230);
        Color corTexto = new Color(70, 60, 50);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Inventário de Comidas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(corTexto);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        painelItens.setBackground(corFundo);

        JScrollPane scroll = new JScrollPane(painelItens);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        atualizarInventario();

        add(painelPrincipal);
        setVisible(true);
    }

    private void atualizarInventario() {
        painelItens.removeAll();

        List<ItemComida> comidas = inventario.getComidas();

        if (comidas.isEmpty()) {
            JLabel vazio = new JLabel("Inventário vazio. Compre itens na loja!");
            vazio.setFont(new Font("Segoe UI", Font.BOLD, 16));
            vazio.setForeground(Color.GRAY);
            vazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelItens.add(vazio);
        } else {
            for (ItemComida comida : comidas) {
                painelItens.add(criarPainelComida(comida));
                painelItens.add(Box.createRigidArea(new Dimension(0, 8)));
            }
        }

        painelItens.revalidate();
        painelItens.repaint();
    }

    private JPanel criarPainelComida(ItemComida comida) {
        Color corFundoItem = new Color(230, 220, 210);
        Color corBotao = new Color(100, 160, 120);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(corFundoItem);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 170, 160)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome e descrição
        JLabel nome = new JLabel("<html><b>" + comida.getNome() + "</b>: " + comida.getDescricao() + "</html>");
        nome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        painel.add(nome, gbc);

        // Efeitos
        JLabel efeitos = new JLabel(String.format(
                "<html><small>Fome +%d | Sede +%d | Felicidade +%d | Saúde +%d</small></html>",
                comida.getEfeitoFome(), comida.getEfeitoSede(),
                comida.getEfeitoFelicidade(), comida.getEfeitoSaude()
        ));
        efeitos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridy = 1;
        painel.add(efeitos, gbc);

        // Botão
        JButton botaoUsar = new JButton("Usar");
        botaoUsar.setBackground(corBotao);
        botaoUsar.setForeground(Color.WHITE);
        botaoUsar.setFocusPainted(false);
        botaoUsar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botaoUsar.setPreferredSize(new Dimension(80, 30));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        painel.add(botaoUsar, gbc);

        botaoUsar.addActionListener(e -> {
            usarComida(comida);
            atualizarInventario();
        });

        return painel;
    }

    private void usarComida(ItemComida comida) {
        inventario.removerComida(comida);
        animal.alimentar(comida);
        JOptionPane.showMessageDialog(this,
                "Você usou: " + comida.getNome() + "\nO Tamagotchi foi alimentado!",
                "Item Usado",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
