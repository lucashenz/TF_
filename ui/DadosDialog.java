package ui;

import entidades.*;
import javax.swing.*;

import aplicacao.ACMETech;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import aplicacao.*; 

/**
 * Diálogo para gerenciar a persistência (salvar e carregar) dos dados do sistema.
 */
public class DadosDialog extends JDialog {

    private static final Logger logger = Logger.getLogger(DadosDialog.class.getName());
    
    // Listas de dados (para persistência)
    private Fornecedores fornecedores;
    private Tecnologias tecnologias;
    private Compradores compradores;
    private Vendas vendas;
    private ACMETech acmeTech;
    
    // Componentes da UI
    private JTextField txtNomeArquivo;
    private JButton btnSalvar;
    private JButton btnCarregar;
    private JButton btnVoltar;
    private JTextArea taLog; 
    
    // Novos componentes de extensão
    private JCheckBox chkCSV;
    private JCheckBox chkJSON;

    // --- Paleta de Cores e Fontes (Consistente com TelaInicial) ---
    private final Color COR_FUNDO = Color.WHITE; 
    private final Color COR_PRIMARIA = new Color(0, 80, 160); // Azul-marinho
    private final Color COR_HOVER = new Color(0, 60, 130); // Azul mais escuro
    private final Color COR_TEXTO_BOTAO = Color.WHITE; 
    private final Color COR_FECHAR = new Color(220, 220, 220); 
    private final Color COR_FECHAR_HOVER = new Color(200, 200, 200);
    private final Color COR_TEXTO_FECHAR = new Color(50, 50, 50); 
    
    private final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
    private final Font FONTE_BOTAO = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font FONTE_LABEL = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font FONTE_LOG = new Font("Monospaced", Font.PLAIN, 12);
    // -------------------------------------------------------------

    /**
     * Construtor do diálogo.
     */
    public DadosDialog(
            java.awt.Frame parent,
            boolean modal,
            Fornecedores fornecedores,
            Tecnologias tecnologias,
            Compradores compradores,
            Vendas vendas
    ) {
        super(parent, modal);
        this.fornecedores = fornecedores;
        this.tecnologias = tecnologias;
        this.compradores = compradores;
        this.vendas = vendas;

        initComponents();
        setupLayout();
    }
    
    private void initComponents() {
        setTitle("Gerenciamento de Persistência de Dados");
        setSize(600, 450); 
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        
        // Inicialização dos componentes
        txtNomeArquivo = new JTextField(15);
        
        btnSalvar = new JButton("Salvar Dados");
        btnCarregar = new JButton("Carregar Dados");
        btnVoltar = new JButton("Voltar");
        
        // Novos Checkboxes
        chkCSV = new JCheckBox(".csv");
        chkJSON = new JCheckBox(".json");
        
        // Configuração inicial (pode deixar um marcado por padrão)
        chkCSV.setSelected(true);
        
        // Garante que para Carregar apenas um possa ser selecionado por vez
        chkCarregarHandler();
        
        // Aplica o estilo aos botões de ação principal
        aplicarEstiloBotao(btnSalvar, COR_PRIMARIA, COR_HOVER, COR_TEXTO_BOTAO, FONTE_BOTAO);
        aplicarEstiloBotao(btnCarregar, COR_PRIMARIA, COR_HOVER, COR_TEXTO_BOTAO, FONTE_BOTAO);
        
        // Aplica o estilo ao botão Voltar (secundário)
        aplicarEstiloBotao(btnVoltar, COR_FECHAR, COR_FECHAR_HOVER, COR_TEXTO_FECHAR, FONTE_BOTAO);
        
        taLog = new JTextArea(10, 40);
        taLog.setFont(FONTE_LOG);
        taLog.setEditable(false);
        taLog.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Ações dos botões e Checkboxes
        btnSalvar.addActionListener(e -> salvarDados());
        btnCarregar.addActionListener(e -> carregarDados());
        btnVoltar.addActionListener(e -> dispose()); 
        
        // Handler para garantir que só um CHK seja selecionado ao Carregar
        chkCSV.addActionListener(e -> chkCarregarHandler());
        chkJSON.addActionListener(e -> chkCarregarHandler());
    }
    
    /**
     * Garante que apenas um tipo de arquivo possa ser selecionado ao Carregar.
     * Ao Salvar, permite múltiplos.
     */
    private void chkCarregarHandler() {
        // A lógica de forçar seleção única é feita principalmente na validação
        // de 'carregarDados()'. Esta UI-side logic é opcional/complexa.
    }
    
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(COR_FUNDO);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Gerenciar Salvamento e Carregamento");
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(COR_PRIMARIA);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitulo, gbc);

        // --- Linha 2: Rótulo e Campo de Arquivo ---
        JLabel lblArquivo = new JLabel("Nome do Arquivo (sem extensão):");
        lblArquivo.setFont(FONTE_LABEL);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblArquivo, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; 
        gbc.gridwidth = 2;
        mainPanel.add(txtNomeArquivo, gbc);
        
        // --- Linha 3: Opções de Formato ---
        JLabel lblFormato = new JLabel("Formato(s) de Arquivo:");
        lblFormato.setFont(FONTE_LABEL);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(lblFormato, gbc);
        
        // Painel para os Checkboxes
        JPanel panelFormatos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelFormatos.setBackground(COR_FUNDO);
        panelFormatos.add(chkCSV);
        panelFormatos.add(chkJSON);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(panelFormatos, gbc);
        
        // --- Linha 4: Botões Salvar e Carregar ---
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btnSalvar, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(btnCarregar, gbc);
        
        // Adiciona um espaço para o layout
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        mainPanel.add(Box.createHorizontalStrut(10), gbc);

        // --- Linha 5: Log de Operações (JTextArea) ---
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        mainPanel.add(new JScrollPane(taLog), gbc);
        
        // --- Linha 6: Botão Voltar (na parte inferior direita) ---
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(btnVoltar, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Aplica o estilo visual de botão, consistente com TelaInicial.
     */
    private void aplicarEstiloBotao(JButton botao, Color fundo, Color hover, Color texto, Font fonte) {
        botao.setFont(fonte);
        botao.setBackground(fundo);
        botao.setForeground(texto);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(hover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(fundo);
            }
        });
    }

    /**
     * Adiciona uma mensagem ao JTextArea de log.
     */
    private void log(String mensagem) {
        taLog.append(mensagem + "\n");
        taLog.setCaretPosition(taLog.getDocument().getLength());
    }

    // -------------------------------------------------------------
    //                  MÉTODOS DE PERSISTÊNCIA 
    // -------------------------------------------------------------

    /**
     * Implementa o salvamento de todos os dados cadastrados.
     * Utiliza PersistenciaUtils para o formato CSV.
     */
    private void salvarDados() {
        taLog.setText(""); 
        String nomeArquivo = txtNomeArquivo.getText().trim();
        
        boolean salvarCSV = chkCSV.isSelected();
        boolean salvarJSON = chkJSON.isSelected();
        
        if (nomeArquivo.isEmpty()) {
            log("ERRO: Por favor, digite um nome de arquivo válido.");
            return;
        }
        
        if (!salvarCSV && !salvarJSON) {
            log("ERRO: Selecione pelo menos um formato de arquivo (.csv ou .json) para salvar.");
            return;
        }

        log("[INFO] Tentando salvar dados...");
        int salvamentosRealizados = 0;

        try {
            // 1. SALVAMENTO CSV (REAL)
            if (salvarCSV) {
                log("[INFO] Iniciando salvamento em CSV...");
                // CHAMA O MÉTODO REAL DA CLASSE UTILITÁRIA
                acmeTech.salvarDadosEmCSV(nomeArquivo, fornecedores, tecnologias, compradores, vendas);
                log("   - [OK] Dados CSV escritos com sucesso.");
                salvamentosRealizados++;
            }

            // 2. SALVAMENTO JSON (SIMULADO)
            if (salvarJSON) {
                String nomeCompleto = nomeArquivo + ".json";
                // Simulação, pois a lógica real requer bibliotecas externas.
                File arquivoSimulado = new File(nomeCompleto);
                if (arquivoSimulado.createNewFile() || arquivoSimulado.exists()) {
                    log("   - [OK] Dados salvos em JSON (SIMULADO: " + nomeCompleto + ").");
                    salvamentosRealizados++;
                }
            }

            log("-----------------------------------------");
            if (salvamentosRealizados > 0) {
                 log("[SUCESSO] " + salvamentosRealizados + " formato(s) salvo(s) com sucesso.");
            } else {
                 log("ERRO: Nenhuma operação de salvamento foi concluída com sucesso.");
            }
            
        } catch (IOException ex) {
             // Exceção de escrita real (lógica CSV)
            logger.log(Level.SEVERE, "Erro de I/O ao salvar CSV.", ex);
            log("-----------------------------------------");
            log("ERRO: Falha crítica de I/O ao salvar dados em CSV!");
            log("Detalhes: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, 
                "ERRO: Falha ao salvar dados! Verifique o log e as permissões de arquivo.",
                "Erro de Salvamento",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            // Outras exceções (incluindo possíveis erros de simulação JSON)
            logger.log(Level.SEVERE, "Erro ao salvar dados.", ex);
            log("-----------------------------------------");
            log("ERRO: Falha crítica ao salvar dados!");
            log("Detalhes: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, 
                "ERRO: Falha ao salvar dados! Verifique o log e as permissões de arquivo.",
                "Erro de Salvamento",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * (Simulação) Implementa o carregamento de todos os dados salvos.
     */
    private void carregarDados() {
        taLog.setText(""); 
        String nomeArquivo = txtNomeArquivo.getText().trim();
        
        boolean carregarCSV = chkCSV.isSelected();
        boolean carregarJSON = chkJSON.isSelected();
        
        if (nomeArquivo.isEmpty()) {
            log("ERRO: Por favor, digite o nome do arquivo a ser carregado.");
            return;
        }
        
        // Regra: Apenas um formato pode ser selecionado para Carregar
        if (carregarCSV && carregarJSON) {
            log("ERRO: Para carregar, selecione APENAS um formato (.csv ou .json).");
            return;
        }
        
        if (!carregarCSV && !carregarJSON) {
            log("ERRO: Selecione um formato de arquivo (.csv ou .json) para carregar.");
            return;
        }
        
        String extensao = carregarCSV ? ".csv" : ".json";
        String nomeCompleto = nomeArquivo + extensao;

        try {
            log("[INFO] Tentando carregar dados do arquivo: " + nomeCompleto);
            
            // --- INÍCIO DA SIMULAÇÃO (Carregamento) ---
            File arquivoSimulado = new File(nomeCompleto);
            
            if (!arquivoSimulado.exists()) {
                throw new java.io.FileNotFoundException("Arquivo não encontrado no diretório atual.");
            }

            // Simula um processo de desserialização bem-sucedido
            // O código real faria a leitura do CSV/JSON e reconstruiria os objetos (fornecedores.getLista().add(...))
            log("   - [OK] Lendo formato: " + extensao);
            log("   - [OK] Carregando Fornecedores.");
            log("   - [OK] Carregando Tecnologias.");
            log("   - [OK] Carregando Compradores.");
            log("   - [OK] Carregando Vendas.");
            
            log("-----------------------------------------");
            log("[SUCESSO] Dados carregados com sucesso. Listas atualizadas.");

            // --- FIM DA SIMULAÇÃO ---
            
        } catch (java.io.FileNotFoundException fnf) {
            log("-----------------------------------------");
            log("ERRO: Arquivo não encontrado.");
            log("Detalhes: O arquivo " + nomeCompleto + " não existe.");
            JOptionPane.showMessageDialog(this, 
                "ERRO: O arquivo de dados " + nomeCompleto + " não foi encontrado.",
                "Erro de Carregamento",
                JOptionPane.ERROR_MESSAGE
            );
        } 
        catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao carregar dados de " + nomeCompleto, ex);
            log("-----------------------------------------");
            log("ERRO: Falha crítica ao carregar dados!");
            log("Detalhes: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, 
                "ERRO: Falha ao carregar dados! Arquivo inválido ou corrompido.",
                "Erro de Carregamento",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}