package ui;

import entidades.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CadastroVendaDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CadastroVendaDialog.class.getName());
    private Venda venda;
    private Tecnologias tecnologias;
    private Fornecedores fornecedores;

    public CadastroVendaDialog(java.awt.Frame parent, boolean modal, Venda listaVendas, Tecnologias listaTecnologias, Fornecedores listaFornecedores) {
        super(parent, modal);
        getComponents();

        this.venda = listaVendas;
        this.tecnologias = listaTecnologias;
        this.fornecedores = listaFornecedores;

        this.iniciaComboboxProdutos();
        this.iniciaComboboxFornecedores();
        this.incrementaVendasCadastradas();

        // cores e fontes
        java.awt.Color corFundo = java.awt.Color.WHITE;
        java.awt.Color corPrimaria = new java.awt.Color(0, 80, 160);
        java.awt.Color corHover = new java.awt.Color(0, 60, 130);
        java.awt.Color corTextoBotao = java.awt.Color.WHITE;
        java.awt.Color corSecundaria = new java.awt.Color(220, 220, 220);
        java.awt.Color corSecundariaHover = new java.awt.Color(200, 200, 200);
        java.awt.Color corTextoSecundario = new java.awt.Color(50, 50, 50);
        java.awt.Color corBordaCampo = new java.awt.Color(200, 200, 200);
        java.awt.Color corTextoLabel = new java.awt.Color(50, 50, 50);

        java.awt.Font fonteTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22);
        java.awt.Font fonteLabel = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16);
        java.awt.Font fonteCampo = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16);
        java.awt.Font fonteBotao = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);

        // dialog
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().setBackground(corFundo);

        // titulo
        javax.swing.JPanel panelTitulo = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        panelTitulo.setBackground(corFundo);
        panelTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 15, 25));

        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Cadastrar Venda");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setForeground(corPrimaria);
        panelTitulo.add(lblTitulo);

        // formulario
        javax.swing.JPanel panelFormulario = new javax.swing.JPanel();
        panelFormulario.setBackground(corFundo);
        panelFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 20, 30));
        panelFormulario.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        // labels
        javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7};
        for (javax.swing.JLabel label : labels) {
            label.setFont(fonteLabel);
            label.setForeground(corTextoLabel);
        }

        // text fields
        javax.swing.border.Border bordaCampo = javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(corBordaCampo),
                javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8)
        );
        javax.swing.JTextField[] fields = {TFId, TFCliente, TFQuantidade, TFValorTotal, TFDataVenda};
        for (javax.swing.JTextField field : fields) {
            field.setFont(fonteCampo);
            field.setBorder(bordaCampo);
        }

        // quantidade cadastrada
        TFTotalVendas.setFont(fonteCampo);
        TFTotalVendas.setBorder(bordaCampo);
        TFTotalVendas.setBackground(new java.awt.Color(235, 235, 235));
        TFTotalVendas.setEditable(false);

        // combobox
        CBProduto.setFont(fonteCampo);
        CBProduto.setBackground(java.awt.Color.WHITE);
        CBProduto.setBorder(javax.swing.BorderFactory.createLineBorder(corBordaCampo));
        ((javax.swing.JLabel) CBProduto.getRenderer()).setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        CBForn.setFont(fonteCampo);
        CBForn.setBackground(java.awt.Color.WHITE);
        CBForn.setBorder(javax.swing.BorderFactory.createLineBorder(corBordaCampo));
        ((javax.swing.JLabel) CBForn.getRenderer()).setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // estilo text area log
        TALog.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        TALog.setForeground(new java.awt.Color(200, 50, 50));
        TALog.setBackground(new java.awt.Color(250, 250, 250));
        TALog.setEditable(false);
        TALog.setLineWrap(true);
        TALog.setWrapStyleWord(true);
        jScrollPane1.setBorder(bordaCampo);
        jScrollPane1.setViewportView(TALog);

        // coluna 0 (labels)
        gbc.gridx = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.weightx = 0.1;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.gridy = 0;
        panelFormulario.add(jLabel1, gbc);
        gbc.gridy = 1;
        panelFormulario.add(jLabel2, gbc);
        gbc.gridy = 2;
        panelFormulario.add(jLabel3, gbc);
        gbc.gridy = 3;
        panelFormulario.add(jLabel4, gbc);
        gbc.gridy = 4;
        panelFormulario.add(jLabel5, gbc);
        gbc.gridy = 5;
        panelFormulario.add(jLabel6, gbc);
        gbc.gridy = 6;
        panelFormulario.add(jLabel7, gbc);

        // coluna 1 (campos)
        gbc.gridx = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.weightx = 0.9;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        panelFormulario.add(TFId, gbc);
        gbc.gridy = 1;
        panelFormulario.add(TFCliente, gbc);
        gbc.gridy = 2;
        panelFormulario.add(CBProduto, gbc);
        gbc.gridy = 3;
        panelFormulario.add(TFQuantidade, gbc);
        gbc.gridy = 4;
        panelFormulario.add(TFValorTotal, gbc);
        gbc.gridy = 5;
        panelFormulario.add(TFDataVenda, gbc);
        gbc.gridy = 6;
        panelFormulario.add(CBForn, gbc);

        // campo quantidade
        gbc.gridy = 7;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        TFTotalVendas.setPreferredSize(new java.awt.Dimension(80, 38));
        panelFormulario.add(TFTotalVendas, gbc);

        // log de erro
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.insets = new java.awt.Insets(15, 8, 8, 8);
        panelFormulario.add(jScrollPane1, gbc);

        // botões
        javax.swing.JPanel panelSul = new javax.swing.JPanel(new java.awt.BorderLayout());
        panelSul.setBackground(corFundo);
        panelSul.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 20, 25));

        javax.swing.JPanel panelBotoesEsquerda = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        panelBotoesEsquerda.setBackground(corFundo);

        javax.swing.JPanel panelBotoesDireita = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
        panelBotoesDireita.setBackground(corFundo);

        panelSul.add(panelBotoesEsquerda, java.awt.BorderLayout.WEST);
        panelSul.add(panelBotoesDireita, java.awt.BorderLayout.EAST);

        java.awt.Dimension tamBotaoSecundario = new java.awt.Dimension(110, 35);
        java.awt.Dimension tamBotaoPrimario = new java.awt.Dimension(120, 35);

        java.util.function.Consumer<javax.swing.JButton> estiloPrimario = (botao) -> {
            botao.setFont(fonteBotao);
            botao.setBackground(corPrimaria);
            botao.setForeground(corTextoBotao);
            botao.setOpaque(true);
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setPreferredSize(tamBotaoPrimario);
            botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corHover);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corPrimaria);
                }
            });
        };

        java.util.function.Consumer<javax.swing.AbstractButton> estiloSecundario = (botao) -> {
            botao.setFont(fonteBotao);
            botao.setBackground(corSecundaria);
            botao.setForeground(corTextoSecundario);
            botao.setOpaque(true);
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setPreferredSize(tamBotaoSecundario);
            botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corSecundariaHover);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corSecundaria);
                }
            });
        };

        estiloPrimario.accept(BtnCadastraVenda);
        estiloSecundario.accept(btnVoltar);
        estiloSecundario.accept(btnLimpar);
        estiloSecundario.accept(btnRelatorio);

        panelBotoesEsquerda.add(btnLimpar);
        panelBotoesEsquerda.add(btnRelatorio);

        panelBotoesDireita.add(btnVoltar);
        panelBotoesDireita.add(BtnCadastraVenda);

        getContentPane().add(panelTitulo, java.awt.BorderLayout.NORTH);
        getContentPane().add(panelFormulario, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelSul, java.awt.BorderLayout.SOUTH);

        this.pack();
        this.setMinimumSize(new java.awt.Dimension(700, 750));
        this.setResizable(false);
    }

    // incrementa total de vendas
    public void incrementaVendasCadastradas() {
        int total = this.venda.size();
        TFTotalVendas.setText(String.valueOf(total));
    }

    // inicia combo box de produtos
    public void iniciaComboboxProdutos() {
        CBProduto.removeAllItems();
        CBProduto.addItem("Selecione um Produto");
        List<Tecnologia> listaTec = tecnologias.getTecnologias();
        for (Tecnologia t : listaTec) {
            CBProduto.addItem(t.getModelo());
        }
        CBProduto.setSelectedIndex(0);
    }

    // inicia combo box de fornecedores
    public void iniciaComboboxFornecedores() {
        CBForn.removeAllItems();
        CBForn.addItem("Selecione um Vendedor");
        List<Fornecedor> listaFor = fornecedores.getFornecedores();
        for (Fornecedor f : listaFor) {
            CBForn.addItem(f.getNome());
        }
        CBForn.setSelectedIndex(0);
    }

    // aqui você implementaria os métodos actionPerformed, como BtnCadastraVendaActionPerformed, btnLimparActionPerformed etc.,
    // seguindo a mesma lógica do CadastroTecnologiaDialog, adaptando para os atributos da Venda.

    // Variables declaration
    private javax.swing.JButton BtnCadastraVenda;
    private javax.swing.JComboBox<String> CBProduto;
    private javax.swing.JComboBox<String> CBForn;
    private javax.swing.JTextArea TALog;
    private javax.swing.JTextField TFId;
    private javax.swing.JTextField TFCliente;
    private javax.swing.JTextField TFQuantidade;
    private javax.swing.JTextField TFValorTotal;
    private javax.swing.JTextField TFDataVenda;
    private javax.swing.JTextField TFTotalVendas;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JToggleButton btnLimpar;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
}
