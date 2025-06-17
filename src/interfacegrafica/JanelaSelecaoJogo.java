package interfacegrafica;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import modelo.*;

public class JanelaSelecaoJogo {
    
    public static void inicializarInterface() {
        JFrame janela = new JFrame("Tamagotchi - Selecionar Jogo");
        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        Color corFundo = new Color(245, 241, 230);
        Color corTexto = new Color(70, 60, 50);

        JPanel painel = new JPanel();
        painel.setBackground(corFundo);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        JLabel titulo = new JLabel("Tamagotchi Virtual");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(corTexto);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Logo (se disponível)
        try {
            ImageIcon logo = new ImageIcon(JanelaSelecaoJogo.class.getResource("imagens/logo.png"));
            Image imagemRedimensionada = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(labelImagem);
        } catch (Exception e) {
            System.out.println("Imagem logo não encontrada!");
        }
        painel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Descrição
        JLabel descricao = new JLabel("Escolha uma opção para começar:");
        descricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descricao.setForeground(corTexto);
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(descricao);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão Novo Jogo
        JButton botaoNovoJogo = Metodos.criarBotao("Novo Jogo");
        botaoNovoJogo.setPreferredSize(new Dimension(200, 50));
        botaoNovoJogo.setMaximumSize(new Dimension(200, 50));
        botaoNovoJogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botaoNovoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a página de nome para criar novo jogo
                Pagina_Nome.main(null);
                janela.dispose();
            }
        });
        painel.add(botaoNovoJogo);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Botão Carregar Jogo
        JButton botaoCarregarJogo = Metodos.criarBotao("Carregar Jogo");
        botaoCarregarJogo.setPreferredSize(new Dimension(200, 50));
        botaoCarregarJogo.setMaximumSize(new Dimension(200, 50));
        botaoCarregarJogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botaoCarregarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarJogo(janela);
            }
        });
        painel.add(botaoCarregarJogo);

        painel.add(Box.createVerticalGlue());

        janela.add(painel);
        janela.setVisible(true);
    }

    private static void carregarJogo(JFrame janelaAtual) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo de save do Tamagotchi");
        
        // Filtro para arquivos CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV (*.csv)", "csv");
        fileChooser.setFileFilter(filter);
        
        // Definir diretório inicial (pasta do projeto ou home do usuário)
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int resultado = fileChooser.showOpenDialog(janelaAtual);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            
            try {
                // Carregar criatura do arquivo CSV
                CriaturaVirtual criatura = PersistenciaCSV.carregarDeCSV(arquivoSelecionado.getAbsolutePath());
                
                if (criatura != null) {
                    // Salvar nos dados do jogo
                    DadosDoJogo.criatura = criatura;
                    DadosDoJogo.nomeTamagotchi = criatura.getNome();
                    DadosDoJogo.tipoTamagotchi = criatura.getTipo();
                    
                    // Abrir a página do animal diretamente
                    SwingUtilities.invokeLater(() -> {
                        PaginaAnimal paginaAnimal = new PaginaAnimal(criatura);
                        paginaAnimal.setVisible(true);
                    });
                    
                    janelaAtual.dispose();
                    
                    JOptionPane.showMessageDialog(null, 
                        "Jogo carregado com sucesso!\nTamagotchi: " + criatura.getNome() + " (" + criatura.getTipo() + ")",
                        "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(janelaAtual, 
                        "Erro ao carregar o arquivo. Verifique se o arquivo está correto.",
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janelaAtual, 
                    "Erro ao carregar o arquivo: " + ex.getMessage(),
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> inicializarInterface());
    }
}
