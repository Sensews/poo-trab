package interfacegrafica;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import modelo.*;

public class Janela_Bem_Vindo {
    public static void inicializarInterface(){
        JFrame janela = new JFrame("Tamagotchi - Bem-vindo");
        janela.setSize(600, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); //Está centralizando a janela

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 241, 230)); // bege claro
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // layout vertical
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margem

        JLabel label = new JLabel("Bem-vindo ao nosso jogo");
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(new Color(70, 60, 50)); // cor marrom escuro
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        //uma logo pro trabalho, ainda não decidida
        try {
            ImageIcon logo = new ImageIcon(Janela_Bem_Vindo.class.getResource("imagens/logo.png")); // coloque a imagem 'logo.png' na mesma pasta do projeto
            Image imagemRedimensionada = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(labelImagem);
        } catch (Exception e) {
            System.out.println("Imagem não encontrada!");
        }
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel labelDescricao = new JLabel("Cuide do seu Tamagotchi e divirta-se!");
        labelDescricao.setFont(new Font("Serif", Font.ITALIC, 18));
        labelDescricao.setForeground(new Color(90, 85, 80));
        labelDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(labelDescricao);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão Novo Jogo
        JButton botaoNovoJogo = Metodos.criarBotao("Novo Jogo");
        botaoNovoJogo.setPreferredSize(new Dimension(200, 50));
        botaoNovoJogo.setMaximumSize(new Dimension(200, 50));
        botaoNovoJogo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoNovoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        botaoCarregarJogo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoCarregarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarJogo(janela);
            }
        });
        painel.add(botaoCarregarJogo);

        painel.add(Box.createVerticalGlue()); // empurra o botão pra parte de baixo

        janela.add(painel);
        janela.setVisible(true);
    }

    private static void carregarJogo(JFrame janelaAtual) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo de save do Tamagotchi");
        
        // Filtro para arquivos CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV (*.csv)", "csv");
        fileChooser.setFileFilter(filter);
        
        // Definir diretório inicial para a pasta saves
        File pastaSaves = new File(System.getProperty("user.home") + File.separator + "TamagotchiSaves");
        if (pastaSaves.exists()) {
            fileChooser.setCurrentDirectory(pastaSaves);
        } else {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }

        int resultado = fileChooser.showOpenDialog(janelaAtual);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
              try {
                // Carregar criatura do arquivo CSV
                CriaturaVirtual criatura = PersistenciaCSV.carregarDeCSV(arquivoSelecionado.getAbsolutePath());
                
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
                    
            } catch (modelo.TamagotchiException ex) {
                JOptionPane.showMessageDialog(janelaAtual, 
                    "Erro ao carregar o arquivo: " + ex.getMessage(),
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        inicializarInterface();
    }
}
