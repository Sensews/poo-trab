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
        this.inventario = animal.getInventario(); // pega o inventário do próprio animal

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
        scroll.getVerticalScrollBar().setUnitIncrement(16); // rolagem suave
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
                painelItens.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        painelItens.revalidate();
        painelItens.repaint();
    }

    private JPanel criarPainelComida(ItemComida comida) {
        Color corFundoItem = new Color(230, 220, 210);
        Color corBotao = new Color(100, 160, 120);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(corFundoItem);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JLabel nome = new JLabel(comida.getNome() + " - " + comida.getDescricao());
        nome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painel.add(nome, BorderLayout.NORTH);

        JLabel efeitos = new JLabel(String.format(
                "<html><body style='width: 300px;'>Efeitos: Fome +%d | Sede +%d | Felicidade +%d | Saúde +%d</body></html>",
                comida.getEfeitoFome(), comida.getEfeitoSede(),
                comida.getEfeitoFelicidade(), comida.getEfeitoSaude()
        ));
        efeitos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        painel.add(efeitos, BorderLayout.CENTER);

        JButton botaoUsar = new JButton("Usar");
        botaoUsar.setBackground(corBotao);
        botaoUsar.setForeground(Color.WHITE);
        botaoUsar.setFocusPainted(false);
        botaoUsar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoUsar.setPreferredSize(new Dimension(80, 35));

        botaoUsar.addActionListener(e -> {
            usarComida(comida);
            atualizarInventario(); // atualiza a interface após remover o item
        });

        painel.add(botaoUsar, BorderLayout.EAST);
        return painel;
    }

    private void usarComida(ItemComida comida) {
        inventario.removerComida(comida); // Remove a comida do inventário
        animal.alimentar(comida); // Aplica os efeitos no animal
        JOptionPane.showMessageDialog(this,
                "Você usou: " + comida.getNome() + "\nO Tamagotchi foi alimentado!",
                "Item Usado",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
