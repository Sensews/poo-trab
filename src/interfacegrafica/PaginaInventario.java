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

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Inventário de Comidas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(corTexto);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        painelItens.setBackground(corFundo);

        JScrollPane scroll = new JScrollPane(painelItens);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        painelPrincipal.add(scroll);

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

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(corFundoItem);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 170, 160)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JLabel nome = new JLabel(comida.getNome());
        nome.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(nome);

        JLabel descricao = new JLabel("<html><center>" + comida.getDescricao() + "</center></html>");
        descricao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(descricao);

        JLabel efeitos = new JLabel(String.format(
                "<html><center>Fome +%d | Sede +%d | Felicidade +%d | Saúde +%d</center></html>",
                comida.getEfeitoFome(), comida.getEfeitoSede(),
                comida.getEfeitoFelicidade(), comida.getEfeitoSaude()
        ));
        efeitos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        efeitos.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(efeitos);

        painel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton botaoUsar = Metodos.criarBotao("Usar");
        botaoUsar.setMaximumSize(new Dimension(100, 30)); // Ajustei só pra esse botão ser menor
        botaoUsar.setPreferredSize(new Dimension(100, 30));
        botaoUsar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botaoUsar.addActionListener(e -> {
            usarComida(comida);
            atualizarInventario();
        });
        painel.add(botaoUsar);

        return painel;
    }    /**
     * REQUISITO 11: Método que usa TamagotchiException
     */
    private void usarComida(ItemComida comida) {
        try {
            // Usar o método seguro que valida o estado
            animal.alimentarSeguro(comida);
            inventario.removerComida(comida);
            atualizarInventario();
            
            JOptionPane.showMessageDialog(this,
                    "Você usou: " + comida.getNome() + "\nO Tamagotchi foi alimentado!",
                    "Item Usado",
                    JOptionPane.INFORMATION_MESSAGE);
                    
        } catch (modelo.TamagotchiException ex) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível usar o item:\n" + ex.getMessage(),
                    "Ação Bloqueada",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
