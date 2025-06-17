package interfacegrafica;

import modelo.CriaturaVirtual;
import modelo.PersistenciaCSV;
import modelo.GerenciadorSaves;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * REQUISITO 1: Programa estruturado em classes (Encapsulamento)
 * REQUISITO 2: Uma das 5+ classes do programa
 * REQUISITO 7: DEMONSTRAÇÃO ATIVA - Botões BRINCAR e HABILIDADE ESPECIAL mostram métodos sobrescritos
 * REQUISITO 8: Usa polimorfismo com CriaturaVirtual
 * REQUISITO 9: Relação de associação com CriaturaVirtual
 * REQUISITO 12: Interface gráfica principal do programa
 * 
 * REQUISITO 7 EM AÇÃO:
 * - Botão BRINCAR chama animal.brincar() que é sobrescrito em cada subclasse (Cachorro, Gato, Peixe)
 * - Cada animal mostra comportamento único na interface através de mensagens específicas
 * - Botão HABILIDADE ESPECIAL chama animal.habilidadeEspecial() (método abstrato implementado)
 * - O usuário pode ver visualmente as diferenças entre os animais ao interagir com eles
 */
public class PaginaAnimal extends JFrame {
    // REQUISITO 9: Associação - PaginaAnimal tem uma CriaturaVirtual
    private CriaturaVirtual animal; // Atributo 20

    // REQUISITO 12: Componentes da interface gráfica
    private JProgressBar barraFome; // Atributo 21
    private JProgressBar barraSede; // Atributo 22
    private JProgressBar barraSono; // Atributo 23
    private JProgressBar barraFelicidade; // Atributo 24
    private JProgressBar barraSaude; // Atributo 25
    private JLabel labelHumor; // Atributo 26
    private JLabel labelDoenca; // Atributo 27

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

        // Painel para a imagem do animal
        JPanel painelImagem = new JPanel();
        painelImagem.setBackground(corFundo);
        painelImagem.setLayout(new BoxLayout(painelImagem, BoxLayout.Y_AXIS));

        String caminhoImagem = getCaminhoImagem();
        if (caminhoImagem != null) {
            try {
                ImageIcon icone = new ImageIcon(getClass().getClassLoader().getResource(caminhoImagem));
                Image imagem = icone.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                JLabel labelImagem = new JLabel(new ImageIcon(imagem));
                labelImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
                painelImagem.add(labelImagem);
            } catch (Exception e) {
                JLabel placeholder = new JLabel("🐾");
                placeholder.setFont(new Font("Segoe UI", Font.PLAIN, 72));
                placeholder.setHorizontalAlignment(SwingConstants.CENTER);
                placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
                painelImagem.add(placeholder);
            }
        }

        // REQUISITO 1: Encapsulamento - uso de getters para acessar atributos privados
        JPanel painelStatus = new JPanel();
        painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
        painelStatus.setBackground(corFundo);

        barraFome = criarBarra("Fome", painelStatus, corTexto);
        barraSede = criarBarra("Sede", painelStatus, corTexto);
        barraSono = criarBarra("Sono", painelStatus, corTexto);
        barraFelicidade = criarBarra("Felicidade", painelStatus, corTexto);
        barraSaude = criarBarra("Saúde", painelStatus, corTexto);

        labelHumor = criarLabel("Humor: " + animal.getHumor(), corTexto);
        labelDoenca = criarLabel("Doença: " + (animal.getTipoDoenca() != null ? animal.getTipoDoenca() : "Nenhuma"), corTexto);

        painelStatus.add(Box.createRigidArea(new Dimension(0, 10)));
        painelStatus.add(labelHumor);
        painelStatus.add(labelDoenca);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBackground(corFundo);

        // REQUISITO 10: Coleções - Inventory usa ArrayList
        JButton botaoAlimentar = Metodos.criarBotao("ALIMENTAR");
        botaoAlimentar.addActionListener(e -> {
            try {
                // REQUISITO 11: Tratamento de exceção customizada
                animal.validarEstadoCritico();
                new PaginaInventario(animal);
                atualizarBarras();
            } catch (modelo.TamagotchiException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Ação Bloqueada",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        // REQUISITO 7: Botão que demonstra método sobrescrito não-abstrato
        // Cada animal (Cachorro, Gato, Peixe) tem comportamento único ao brincar
        JButton botaoBrincar = Metodos.criarBotao("BRINCAR");
        botaoBrincar.addActionListener(e -> {
            try {
                // REQUISITO 11: Validação antes de brincar
                animal.validarEstadoCritico();
                if (verificarSeEstaVivo()) {
                    // REQUISITO 7: Chamada do método sobrescrito não-abstrato brincar()
                    // Cada animal tem comportamento único ao brincar
                    animal.brincar();
                    atualizarBarras();
                    
                    // Mostrar mensagem específica baseada no tipo de animal
                    // Demonstra o polimorfismo e método sobrescrito em ação
                    String mensagemBrincar = obterMensagemBrincar();
                    JOptionPane.showMessageDialog(this,
                        mensagemBrincar,
                        "Brincando com " + animal.getNome() + "!",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (modelo.TamagotchiException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Não Pode Brincar",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botaoLoja = Metodos.criarBotao("LOJA");
        botaoLoja.addActionListener(e -> new PaginaLoja(animal));        JButton botaoMinigame = Metodos.criarBotao("MINIGAME");
        botaoMinigame.addActionListener(e -> new PaginaDificuldade(animal));

        // REQUISITO 4: Botão que demonstra método abstrato implementado
        // Cada animal tem uma habilidade especial única
        JButton botaoHabilidade = Metodos.criarBotao("HABILIDADE ESPECIAL");
        botaoHabilidade.addActionListener(e -> {
            try {
                animal.validarEstadoCritico();
                if (verificarSeEstaVivo()) {
                    // REQUISITO 4: Chamada do método abstrato implementado
                    animal.habilidadeEspecial();
                    atualizarBarras();
                    
                    // Mostrar mensagem específica da habilidade especial
                    String mensagemHabilidade = obterMensagemHabilidadeEspecial();
                    JOptionPane.showMessageDialog(this,
                        mensagemHabilidade,
                        "Habilidade Especial de " + animal.getNome() + "!",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (modelo.TamagotchiException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Não Pode Usar Habilidade",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botaoSalvar = Metodos.criarBotao("SALVAR JOGO");
        botaoSalvar.addActionListener(e -> salvarJogo());

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
        });        painelBotoes.add(criarLinhaBotoes(botaoAlimentar, botaoDescansar));
        painelBotoes.add(criarEspacamentoVertical(15));
        painelBotoes.add(criarLinhaBotoes(botaoBrincar, botaoLoja));
        painelBotoes.add(criarEspacamentoVertical(15));
        painelBotoes.add(criarLinhaBotoes(botaoMinigame, botaoHabilidade));
        painelBotoes.add(criarEspacamentoVertical(15));
        
        // Botão salvar centralizado
        JPanel painelSalvar = new JPanel();
        painelSalvar.setBackground(new Color(245, 241, 230));
        painelSalvar.add(botaoSalvar);
        painelBotoes.add(painelSalvar);

        painelPrincipal.add(titulo, BorderLayout.NORTH);
        painelPrincipal.add(painelImagem, BorderLayout.CENTER);
        painelPrincipal.add(painelStatus, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Timer para atualização dos atributos a cada 3 segundos
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            animal.atualizacaoTempo();
            atualizarBarras();
        });
        timer.start();

        // Timer para auto-salvamento a cada 5 minutos (300000 ms)
        Timer timerAutoSave = new Timer(300000, (ActionEvent e) -> {
            autoSalvarJogo();
        });
        timerAutoSave.start();

        // Atualizar barras inicialmente para mostrar os valores corretos
        atualizarBarras();

        add(painelPrincipal);
        setVisible(true);
    }    /**
     * REQUISITO 7: Método que demonstra o uso do método sobrescrito brincar()
     * Retorna mensagens específicas baseadas no tipo de animal
     * Mostra o comportamento único de cada subclasse
     */
    private String obterMensagemBrincar() {
        // REQUISITO 8: Uso de polimorfismo - getTipo() retorna o tipo específico
        return switch (animal.getTipo()) {
            case "Cachorro" -> 
                "🐶 " + animal.getNome() + " correu, pulou e se divertiu muito!\n" +
                "Cachorros adoram brincar e ficam muito felizes (+10 felicidade)\n" +
                "Mas gastam mais energia brincando (-8 sono)";
                
            case "Gato" -> 
                "🐱 " + animal.getNome() + " brincou com uma bolinha de lã e adorou!\n" +
                "Gatos são elegantes ao brincar (+5 felicidade)\n" +
                "Brincam de forma mais controlada";
                
            case "Peixe" -> 
                "🐠 " + animal.getNome() + " nadou em círculos brincando graciosamente!\n" +
                "Peixes brincam de forma tranquila (+8 felicidade)\n" +
                "Gastam pouca energia (-3 sono)";
                
            default -> 
                animal.getNome() + " brincou e se divertiu!";
        };
    }

    /**
     * REQUISITO 4: Método que demonstra o uso de métodos abstratos implementados
     * Retorna mensagens específicas da habilidade especial de cada animal
     * Mostra a implementação única de cada subclasse
     */
    private String obterMensagemHabilidadeEspecial() {
        // REQUISITO 8: Uso de polimorfismo - getTipo() retorna o tipo específico
        return switch (animal.getTipo()) {
            case "Cachorro" -> 
                "🐕 " + animal.getNome() + " está abanando o rabo e saltitando de alegria!\n" +
                "A energia contagiante do cachorro espalha felicidade!\n" +
                "Bonus: +15 felicidade, +10 sono, +5 pontos!";
                
            case "Gato" -> 
                "🐈 " + animal.getNome() + " está ronronando em tranquilidade zen!\n" +
                "A sabedoria felina traz equilíbrio e cura!\n" +
                "Bonus: +20 saúde, +15 felicidade, cura de doenças!";
                
            case "Peixe" -> 
                "🐟 " + animal.getNome() + " nada em círculos meditativos!\n" +
                "A tranquilidade do peixe traz paz e restaura a energia!\n" +
                "Bonus: +15 sono, sede saciada, +10 felicidade, +5 saúde!";
                
            default -> 
                animal.getNome() + " usou sua habilidade especial!";
        };
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

    private void salvarJogo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar jogo do Tamagotchi");
        
        // Filtro para arquivos CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV (*.csv)", "csv");
        fileChooser.setFileFilter(filter);
        
        // Nome padrão do arquivo
        String nomeArquivo = "tamagotchi_" + animal.getNome().toLowerCase().replaceAll("\\s+", "_") + ".csv";
        fileChooser.setSelectedFile(new File(nomeArquivo));
        
        // Definir diretório inicial para a pasta de saves
        File pastaSaves = new File(GerenciadorSaves.getCaminhoPastaSaves());
        fileChooser.setCurrentDirectory(pastaSaves);

        int resultado = fileChooser.showSaveDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSalvar = fileChooser.getSelectedFile();
            
            // Garantir que o arquivo tenha extensão .csv
            if (!arquivoSalvar.getName().toLowerCase().endsWith(".csv")) {
                arquivoSalvar = new File(arquivoSalvar.getAbsolutePath() + ".csv");
            }
            
            try {
                PersistenciaCSV.salvarParaCSV(animal, arquivoSalvar.getAbsolutePath());
                
                JOptionPane.showMessageDialog(this, 
                    "Jogo salvo com sucesso!\nArquivo: " + arquivoSalvar.getName(),
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (modelo.TamagotchiException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao salvar o jogo: " + ex.getMessage(),
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void autoSalvarJogo() {
        try {
            // Usar o gerenciador de saves para auto-save
            String caminhoCompleto = GerenciadorSaves.gerarCaminhoSave(animal.getNome(), true);
            
            // Salvar automaticamente
            PersistenciaCSV.salvarParaCSV(animal, caminhoCompleto);
            
            System.out.println("Auto-salvamento realizado: " + caminhoCompleto);
            
        } catch (modelo.TamagotchiException e) {
            System.err.println("Erro no auto-salvamento: " + e.getMessage());
        }
    }
}
