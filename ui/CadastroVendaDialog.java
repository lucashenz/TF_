/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import entidades.*;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author arthurzimmer
 */
public class CadastroVendaDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CadastroVendaDialog.class.getName());
    private Compradores listaCompradores;
    private Tecnologias listaTecnologias;
    private Vendas vendas;
    /**
     * Creates new form CadastroVendaDialog
     */
    public CadastroVendaDialog(java.awt.Frame parent, boolean modal, Compradores compradores, Tecnologias tecnologias, Vendas listaVendas) {
        super(parent, modal);
        this.vendas = listaVendas;
        this.listaCompradores = compradores;
        this.listaTecnologias = tecnologias;
        initComponents();
        this.iniciaListas(compradores, tecnologias);

        // --- 1. ESTILOS ---
        java.awt.Color corFundo = java.awt.Color.WHITE;
        java.awt.Color corPrimaria = new java.awt.Color(0, 80, 160);
        java.awt.Color corHover = new java.awt.Color(0, 60, 130);
        java.awt.Color corTextoBotao = java.awt.Color.WHITE;
        java.awt.Color corSecundaria = new java.awt.Color(220, 220, 220);
        java.awt.Color corSecundariaHover = new java.awt.Color(200, 200, 200);
        java.awt.Color corTextoSecundario = new java.awt.Color(50, 50, 50);
        java.awt.Color corBordaCampo = new java.awt.Color(200, 200, 200);
        java.awt.Color corTextoLabel = new java.awt.Color(50, 50, 50);
        java.awt.Color corReadOnly = new java.awt.Color(245, 245, 245);

        java.awt.Font fonteTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22);
        java.awt.Font fonteSubTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16);
        java.awt.Font fonteLabel = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        java.awt.Font fonteCampo = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        java.awt.Font fonteBotao = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);

        // --- 2. ESTRUTURA GERAL ---
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().setBackground(corFundo);

        // Topo
        javax.swing.JPanel panelTitulo = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        panelTitulo.setBackground(corFundo);
        panelTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 25, 5, 25));
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Cadastro de Venda");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setForeground(corPrimaria);
        panelTitulo.add(lblTitulo);

        // Centro (Scroll)
        jPanel1.removeAll();
        jPanel1.setBackground(corFundo);
        jPanel1.setLayout(new java.awt.GridBagLayout());
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 20, 20));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc.fill = java.awt.GridBagConstraints.BOTH;

        javax.swing.border.Border bordaCampo = javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(corBordaCampo),
                javax.swing.BorderFactory.createEmptyBorder(4, 6, 4, 6)
        );

        // ======================= SEÇÃO 1: COMPRADOR =======================
        jLabel11.setText("1. Selecione o Comprador");
        jLabel11.setFont(fonteSubTitulo);
        jLabel11.setForeground(corPrimaria);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        jPanel1.add(jLabel11, gbc);

        // Lista
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.weightx = 0.25; gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.NORTH;
        ListaCompradores.setFont(fonteCampo);
        jScrollPane3.setBorder(bordaCampo);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(150, 140));
        jPanel1.add(jScrollPane3, gbc);

        // Detalhes Comprador
        javax.swing.JPanel pnlDetComp = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlDetComp.setBackground(corFundo);
        javax.swing.JLabel[] lblsC = {jLabel1, jLabel5, jLabel6, jLabel4};
        javax.swing.JTextField[] txtsC = {TFNomeComprador, TFEmailComprador, TFPaisComprador, TFCodComprador};
        configurarDetalhes(pnlDetComp, lblsC, txtsC, fonteLabel, fonteCampo, bordaCampo, corTextoLabel, corReadOnly);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.75;
        jPanel1.add(pnlDetComp, gbc);

        // Separador
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weighty = 0;
        gbc.insets = new java.awt.Insets(15, 5, 5, 5);
        jPanel1.add(new javax.swing.JSeparator(), gbc);

        // ======================= SEÇÃO 2: TECNOLOGIA =======================
        jLabel12.setText("2. Selecione a Tecnologia");
        jLabel12.setFont(fonteSubTitulo);
        jLabel12.setForeground(corPrimaria);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel12, gbc);

        // Lista
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        ListaTecnologias.setFont(fonteCampo);
        jScrollPane4.setBorder(bordaCampo);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(150, 220));
        jPanel1.add(jScrollPane4, gbc);

        // Detalhes Tecnologia
        javax.swing.JPanel pnlDetTec = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlDetTec.setBackground(corFundo);
        javax.swing.JLabel[] lblsT = {jLabel17, jLabel7, jLabel8, jLabel9, jLabel10, jLabel15, jLabel16};
        javax.swing.JTextField[] txtsT = {TFIdTec, TFModeloTec, TFDescTec, TFValorTec, TFPesoTec, TFTempTec, TFFornTec};
        configurarDetalhes(pnlDetTec, lblsT, txtsT, fonteLabel, fonteCampo, bordaCampo, corTextoLabel, corReadOnly);

        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 0.75;
        jPanel1.add(pnlDetTec, gbc);

        // Separador
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.insets = new java.awt.Insets(15, 5, 5, 5);
        jPanel1.add(new javax.swing.JSeparator(), gbc);

        // ======================= SEÇÃO 3: DADOS DA VENDA =======================
        javax.swing.JLabel lblDados = new javax.swing.JLabel("3. Dados da Venda");
        lblDados.setFont(fonteSubTitulo);
        lblDados.setForeground(corPrimaria);
        gbc.gridx = 0; gbc.gridy = 6; gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(lblDados, gbc);

        // Painel Dados Venda
        javax.swing.JPanel pnlVenda = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlVenda.setBackground(corFundo);

        // ARRAY ATUALIZADO COM VALOR FINAL
        javax.swing.JLabel[] lblsV = {jLabel13, jLabel14, jLabel19, jLabel18}; // ID, Data, Valor Final, Qtd
        javax.swing.JTextField[] txtsV = {TFId, TFData, TFValorFinal, TFQtdVendas};

        java.awt.GridBagConstraints gbcV = new java.awt.GridBagConstraints();
        gbcV.insets = new java.awt.Insets(2, 0, 2, 0);
        gbcV.anchor = java.awt.GridBagConstraints.WEST;

        for(int i=0; i<lblsV.length; i++) {
            gbcV.gridy = i;
            gbcV.gridx = 0; gbcV.weightx = 0; gbcV.fill = java.awt.GridBagConstraints.NONE;
            lblsV[i].setFont(fonteLabel);
            lblsV[i].setForeground(corTextoLabel);
            pnlVenda.add(lblsV[i], gbcV);

            gbcV.gridx = 1; gbcV.weightx = 1.0;
            gbcV.fill = java.awt.GridBagConstraints.NONE;
            txtsV[i].setPreferredSize(new java.awt.Dimension(120, 28)); // Tamanho curto para todos
            txtsV[i].setFont(fonteCampo);
            txtsV[i].setBorder(bordaCampo);

            // Lógica de Estilo: Valor Final e Qtd são ReadOnly (Cinza)
            if (txtsV[i] == TFValorFinal || txtsV[i] == TFQtdVendas) {
                txtsV[i].setBackground(corReadOnly);
                txtsV[i].setEditable(false);
            } else {
                txtsV[i].setBackground(java.awt.Color.WHITE);
                txtsV[i].setEditable(true);
            }

            gbcV.insets = new java.awt.Insets(2, 5, 2, 0);
            pnlVenda.add(txtsV[i], gbcV);
            gbcV.insets = new java.awt.Insets(2, 0, 2, 0); // Reset
        }

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        jPanel1.add(pnlVenda, gbc);

        // ======================= SEÇÃO 4: LOG =======================
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.insets = new java.awt.Insets(15, 5, 5, 5);

        TALog.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        TALog.setForeground(new java.awt.Color(200, 50, 50));
        TALog.setBackground(new java.awt.Color(250, 250, 250));
        TALog.setEditable(false);
        TALog.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        jScrollPane2.setBorder(bordaCampo);
        jScrollPane2.setViewportView(TALog);

        jPanel1.add(jScrollPane2, gbc);

        // Scroll Pane Principal
        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.setBorder(null);

        // --- 6. BOTÕES ---
        javax.swing.JPanel panelSul = new javax.swing.JPanel(new java.awt.BorderLayout());
        panelSul.setBackground(corFundo);
        panelSul.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 20, 25));

        javax.swing.JPanel panelBotoesEsquerda = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        panelBotoesEsquerda.setBackground(corFundo);
        javax.swing.JPanel panelBotoesDireita = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
        panelBotoesDireita.setBackground(corFundo);

        panelSul.add(panelBotoesEsquerda, java.awt.BorderLayout.WEST);
        panelSul.add(panelBotoesDireita, java.awt.BorderLayout.EAST);

        // Estilos Botões
        java.awt.Dimension tamBotaoSec = new java.awt.Dimension(110, 35);
        java.awt.Dimension tamBotaoPri = new java.awt.Dimension(120, 35);

        java.util.function.Consumer<javax.swing.JButton> stylePri = (btn) -> {
            btn.setFont(fonteBotao); btn.setBackground(corPrimaria); btn.setForeground(corTextoBotao);
            btn.setOpaque(true); btn.setBorderPainted(false); btn.setFocusPainted(false);
            btn.setPreferredSize(tamBotaoPri); btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(corHover); }
                public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(corPrimaria); }
            });
        };
        java.util.function.Consumer<javax.swing.JButton> styleSec = (btn) -> {
            btn.setFont(fonteBotao); btn.setBackground(corSecundaria); btn.setForeground(corTextoSecundario);
            btn.setOpaque(true); btn.setBorderPainted(false); btn.setFocusPainted(false);
            btn.setPreferredSize(tamBotaoSec); btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(corSecundariaHover); }
                public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(corSecundaria); }
            });
        };

        stylePri.accept(btnCadastrar);
        styleSec.accept(btnVoltar); styleSec.accept(btnLimpar); styleSec.accept(btnRelatorio);
        styleSec.accept(btnRemover);

        panelBotoesEsquerda.add(btnLimpar); panelBotoesEsquerda.add(btnRelatorio);
        panelBotoesEsquerda.add(btnRemover);
        panelBotoesDireita.add(btnVoltar); panelBotoesDireita.add(btnCadastrar);

        getContentPane().add(panelTitulo, java.awt.BorderLayout.NORTH);
        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelSul, java.awt.BorderLayout.SOUTH);

        this.setSize(950, 750);
        this.setMinimumSize(new java.awt.Dimension(800, 600));
        this.setResizable(true);
    }

    // Método auxiliar para configurar os painéis de detalhe
    private void configurarDetalhes(javax.swing.JPanel panel, javax.swing.JLabel[] labels, javax.swing.JTextField[] fields,
                                    java.awt.Font fontL, java.awt.Font fontF, javax.swing.border.Border border,
                                    java.awt.Color colorL, java.awt.Color colorRead) {
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        c.insets = new java.awt.Insets(2, 0, 2, 0);
        c.anchor = java.awt.GridBagConstraints.WEST;

        for (int i = 0; i < labels.length; i++) {
            c.gridy = i;
            c.gridx = 0; c.weightx = 0; c.fill = java.awt.GridBagConstraints.NONE;
            labels[i].setFont(fontL);
            labels[i].setForeground(colorL);
            panel.add(labels[i], c);

            c.gridx = 1; c.weightx = 1.0;
            String text = labels[i].getText().toLowerCase();
            if (text.contains("id") || text.contains("código") || text.contains("codigo")) {
                c.fill = java.awt.GridBagConstraints.NONE;
                fields[i].setPreferredSize(new java.awt.Dimension(70, 28));
            } else {
                c.fill = java.awt.GridBagConstraints.HORIZONTAL;
                fields[i].setPreferredSize(new java.awt.Dimension(0, 28));
            }
            fields[i].setFont(fontF);
            fields[i].setBorder(border);
            fields[i].setBackground(colorRead);
            fields[i].setEditable(false);
            c.insets = new java.awt.Insets(2, 5, 2, 0);
            panel.add(fields[i], c);
            c.insets = new java.awt.Insets(2, 0, 2, 0);
        }
    }
//private void configurarDetalhes(javax.swing.JPanel panel, javax.swing.JLabel[] labels, javax.swing.JTextField[] fields,
//                                    java.awt.Font fontL, java.awt.Font fontF, javax.swing.border.Border border,
//                                    java.awt.Color colorL, java.awt.Color colorRead) {
//        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
//        c.insets = new java.awt.Insets(2, 0, 2, 0); // Espaçamento vertical pequeno
//        c.anchor = java.awt.GridBagConstraints.WEST; // Tudo alinhado à esquerda
//
//        for (int i = 0; i < labels.length; i++) {
//            c.gridy = i;
//
//            // Label (Coluna 0)
//            c.gridx = 0;
//            c.weightx = 0;
//            c.fill = java.awt.GridBagConstraints.NONE;
//            labels[i].setFont(fontL);
//            labels[i].setForeground(colorL);
//            panel.add(labels[i], c);
//
//            // Field (Coluna 1)
//            c.gridx = 1;
//            c.weightx = 1.0;
//
//            // Lógica: ID ou Código fica curto, o resto preenche
//            String text = labels[i].getText().toLowerCase();
//            if (text.contains("id") || text.contains("código") || text.contains("codigo")) {
//                c.fill = java.awt.GridBagConstraints.NONE;
//                fields[i].setPreferredSize(new java.awt.Dimension(70, 28)); // Curto
//            } else {
//                c.fill = java.awt.GridBagConstraints.HORIZONTAL;
//                fields[i].setPreferredSize(new java.awt.Dimension(0, 28)); // Altura fixa
//            }
//
//            fields[i].setFont(fontF);
//            fields[i].setBorder(border);
//            fields[i].setBackground(colorRead);
//            fields[i].setEditable(false);
//
//            // Margem esquerda do campo para "desgrudar" um pouquinho do label (5px)
//            c.insets = new java.awt.Insets(2, 5, 2, 0);
//            panel.add(fields[i], c);
//
//            // Reseta insets para o próximo label
//            c.insets = new java.awt.Insets(2, 0, 2, 0);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    public void iniciaListas(Compradores listaCompradores, Tecnologias listaTecnologias) {
        if (listaCompradores.size() == 0){
            String[] compradoresVazio = new String[1];
            compradoresVazio[0] = "Nenhum comprador cadastrado";
            ListaCompradores.setListData(compradoresVazio);
        } else {

            String[] compradoresAux = new String[listaCompradores.size()];
            for (int i = 0; i < listaCompradores.size(); i++) {
                compradoresAux[i] = listaCompradores.getComprador(i).getNome();
            }

            ListaCompradores.setListData(compradoresAux);
        }

        if (listaTecnologias.size() == 0) {
            String[] TecnologiasVazio = new String[1];
            TecnologiasVazio[0] = "Nenhuma tecnologia cadastrada";
            ListaTecnologias.setListData(TecnologiasVazio);
        } else {

            String[] tecnologiasAux = new String[listaTecnologias.size()];
            for (int i = 0; i < listaTecnologias.size(); i++) {
                tecnologiasAux[i] = listaTecnologias.getTecnologia(i).getDescricao();
            }

            ListaTecnologias.setListData(tecnologiasAux);
        }


    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFNomeComprador = new javax.swing.JTextField();
        TFEmailComprador = new javax.swing.JTextField();
        TFPaisComprador = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaCompradores = new javax.swing.JList<>();
        TFCodComprador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TFModeloTec = new javax.swing.JTextField();
        TFDescTec = new javax.swing.JTextField();
        TFValorTec = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListaTecnologias = new javax.swing.JList<>();
        TFPesoTec = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TFId = new javax.swing.JTextField();
        TFData = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TALog = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TFTempTec = new javax.swing.JTextField();
        TFFornTec = new javax.swing.JTextField();
        TFIdTec = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        TFQtdVendas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TFValorFinal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollBar2 = new javax.swing.JScrollBar();
        btnCadastrar = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadsatrar Venda");

        jLabel1.setText("Nome:");

        jLabel5.setText("Email:");

        jLabel6.setText("País:");

        jLabel4.setText("ID:");

        TFNomeComprador.setEnabled(false);
        TFNomeComprador.setFocusable(false);

        TFEmailComprador.setEnabled(false);
        TFEmailComprador.setFocusable(false);

        TFPaisComprador.setEnabled(false);
        TFPaisComprador.setFocusable(false);

        ListaCompradores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaCompradores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaCompradoresValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(ListaCompradores);

        TFCodComprador.setEnabled(false);
        TFCodComprador.setFocusable(false);

        jLabel7.setText("Modelo:");

        jLabel8.setText("Desc:");

        jLabel9.setText("Valor base:");

        jLabel10.setText("Peso:");

        TFModeloTec.setEnabled(false);
        TFModeloTec.setFocusable(false);

        TFDescTec.setEnabled(false);
        TFDescTec.setFocusable(false);

        TFValorTec.setEnabled(false);
        TFValorTec.setFocusable(false);

        ListaTecnologias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaTecnologias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaTecnologiasValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(ListaTecnologias);

        TFPesoTec.setEnabled(false);
        TFPesoTec.setFocusable(false);

        jLabel11.setText("Selecione o comprador");

        jLabel12.setText("Selecione a tecnologia");

        jLabel13.setText("Informe o ID da venda:");

        jLabel14.setText("Informe a data:");

        TALog.setColumns(20);
        TALog.setRows(5);
        jScrollPane2.setViewportView(TALog);

        jLabel15.setText("Temperatura:");

        jLabel16.setText("Fornecedor:");

        jLabel17.setText("ID:");

        TFTempTec.setEnabled(false);
        TFTempTec.setFocusable(false);

        TFFornTec.setEnabled(false);
        TFFornTec.setFocusable(false);

        TFIdTec.setEnabled(false);
        TFIdTec.setFocusable(false);

        jLabel18.setText("Qtd. Vendas:");

        TFQtdVendas.setBackground(new java.awt.Color(242, 242, 242));
        TFQtdVendas.setEnabled(false);
        TFQtdVendas.setFocusable(false);

        jLabel19.setText("Valor final da venda:");

        TFValorFinal.setEnabled(false);
        TFValorFinal.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel10)
                                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(TFModeloTec)
                                                                                        .addComponent(TFDescTec)
                                                                                        .addComponent(TFValorTec, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                                                        .addComponent(TFPesoTec)))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel15)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(TFTempTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel16)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(TFFornTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel17)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(TFIdTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(TFNomeComprador)
                                                                        .addComponent(TFEmailComprador)
                                                                        .addComponent(TFPaisComprador, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                                        .addComponent(TFCodComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel13)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(TFId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel14)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(TFData, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel18)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(TFQtdVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel19)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(TFValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(128, 128, 128)
                                                .addComponent(jLabel11))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(jLabel12)))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(TFNomeComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(TFEmailComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(TFPaisComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(TFCodComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(TFModeloTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(TFDescTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(TFValorTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(TFPesoTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(TFTempTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel16)
                                                        .addComponent(TFFornTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(TFIdTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(TFId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(TFData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(TFValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(TFQtdVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnRelatorio.setText("Relatório");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });


        btnRelatorio.setText("Remover");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });


        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnVoltar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCadastrar)
                                                .addGap(20, 20, 20))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnLimpar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                                                .addComponent(btnRelatorio))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnRelatorio, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnLimpar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCadastrar)
                                        .addComponent(btnVoltar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void ListaCompradoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaCompradoresValueChanged
        TFNomeComprador.setText("");
        TFEmailComprador.setText("");
        TFPaisComprador.setText("");
        TFCodComprador.setText("");

        try{
            String nomeComprador = ListaCompradores.getSelectedValue();
            if (nomeComprador == null) {
                TALog.setText("");
            } else if (nomeComprador.equals("Nenhum comprador cadastrado")) {
                throw new IllegalArgumentException();
            } else {
                Comprador comprador = listaCompradores.getCompradorByName(nomeComprador);

                TFNomeComprador.setText(comprador.getNome());
                TFEmailComprador.setText(comprador.getEmail());
                TFPaisComprador.setText(comprador.getPais());

                long id = comprador.getCod();
                String idStr = "" + id;
                TFCodComprador.setText(idStr);
                atualizaValorFinal();
            }
        } catch (IllegalArgumentException e) {
            TALog.setText("Erro: Cadastre um comprador para seleciona-lo");
        }
    }//GEN-LAST:event_ListaCompradoresValueChanged

    private void ListaTecnologiasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaTecnologiasValueChanged
        TFModeloTec.setText("");
        TFDescTec.setText("");
        TFValorTec.setText("");
        TFPesoTec.setText("");
        TFTempTec.setText("");
        TFFornTec.setText("");
        TFIdTec.setText("");

        try{
            String nomeTecnologia = ListaTecnologias.getSelectedValue();
            if (nomeTecnologia == null) {
                TALog.setText("");
            } else if (nomeTecnologia.equals("Nenhuma tecnologia cadastrada")) {
                throw new IllegalArgumentException();
            } else {
                Tecnologia tecnologia = listaTecnologias.getTecnologiaByName(nomeTecnologia);

                TFModeloTec.setText(tecnologia.getModelo());
                TFDescTec.setText(tecnologia.getDescricao());

                double valor = tecnologia.getValorBase();
                String valorStr = "" + valor;
                TFValorTec.setText(valorStr);

                double peso = tecnologia.getPeso();
                String pesoStr = "" + peso;
                TFPesoTec.setText(pesoStr);

                double temperatura = tecnologia.getTemperatura();
                String tempStr = "" + temperatura;
                TFTempTec.setText(tempStr);

                Fornecedor forn = tecnologia.getFornecedor();
                if (forn == null) {
                    TFFornTec.setText("Nenhum fornecedor vinculado");
                } else {
                    TFFornTec.setText(tecnologia.getFornecedor().getNome());
                }

                long id = tecnologia.getId();
                String idStr = "" + id;
                TFIdTec.setText(idStr);
                atualizaValorFinal();
            }
        } catch (IllegalArgumentException e) {
            TALog.setText("Erro: Cadastre uma tecnologia para seleciona-la");
        }
    }//GEN-LAST:event_ListaTecnologiasValueChanged

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        ListaCompradores.clearSelection();
        ListaTecnologias.clearSelection();

        TFNomeComprador.setText("");
        TFEmailComprador.setText("");
        TFPaisComprador.setText("");
        TFCodComprador.setText("");
        TFModeloTec.setText("");
        TFDescTec.setText("");
        TFValorTec.setText("");
        TFPesoTec.setText("");
        TFTempTec.setText("");
        TFFornTec.setText("");
        TFIdTec.setText("");
        TFId.setText("");
        TFData.setText("");
        TFQtdVendas.setText("");
        TFValorFinal.setText("");
        TALog.setText("");
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try{
            String nomeComprador = ListaCompradores.getSelectedValue();
            if (nomeComprador.equals("Nenhum comprador cadastrado")) {
                throw new IllegalArgumentException("Selecione ao menos um comprador.");
            }
            Comprador comprador = listaCompradores.getCompradorByName(nomeComprador);

            String nomeTecnologia = ListaTecnologias.getSelectedValue();
            if (nomeTecnologia.equals("Nenhuma tecnologia cadastrada")) {
                throw new IllegalArgumentException("Selecione ao menos uma tecnologia.");
            }
            Tecnologia tecnologia = listaTecnologias.getTecnologiaByName(nomeTecnologia);

            if (tecnologia.isFoiVendida() == true)
                throw new IllegalArgumentException("Essa tecnologia não está disponível para compra.");

            String idStr = TFId.getText();
            if (!idStr.matches("^\\d*$"))
                throw new IllegalArgumentException("Formato inválido de ID! Apenas números.");
            if (idStr.equals(""))
                throw new IllegalArgumentException("O ID é obrigatório!");
            long id = Long.parseLong(idStr);

            String dataStr = TFData.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            Date dataAux;
            try {
                dataAux = sdf.parse(dataStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Data inválida: " + dataStr + ". Use o formato dd/MM/yyyy");
            }

            String valorFinalStr = TFValorFinal.getText();
            double valorFinal = Double.parseDouble(valorFinalStr);

            Venda venda = new Venda (comprador, tecnologia, id, dataAux); // ver ordem de insercao de vendas
            venda.setValorFinal(valorFinal);

            if (vendas.addVenda(venda)){
                tecnologia.setFoiVendida(true);
                comprador.setComprasRealizadas(comprador.incrementaCompra());
                TALog.setText("Venda cadastrada com sucesso!");
                incrementaVendas();
            } else {
                throw new IllegalArgumentException("Venda não cadastrada. Consulte o suporte.");
            }

        } catch (IllegalArgumentException e) {
            TALog.setText("Erro: " + e);
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed


    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {
        String input = TFId.getText().trim();

        // Validação básica
        if (input.isEmpty()) {
            TALog.setText("ERRO: Digite o número da venda para remover.");
            return;
        }

        try {
            int numeroVenda = Integer.parseInt(input);

            // Tenta remover a venda
            int removido = vendas.removeVendaPorNumero(numeroVenda);

            if (removido <=0) {
                TALog.setText("Venda nº " + numeroVenda + " removida com sucesso!");
                incrementaVendas(); // Atualiza o contador de vendas
                btnLimparActionPerformed(null); // Limpa os campos (opcional, mas deixa limpo)
            } else {
                TALog.setText("ERRO: Não existe venda com o número " + numeroVenda + ".");
            }

        } catch (NumberFormatException ex) {
            TALog.setText("ERRO: Número da venda inválido. Use apenas números inteiros.");
        } catch (Exception ex) {
            TALog.setText("ERRO inesperado ao remover venda: " + ex.getMessage());
        }
    }

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        TelaInicial telaAux = new TelaInicial();

        RelatorioVendasDialog relatorioVendas = new RelatorioVendasDialog(telaAux, true, vendas);
        relatorioVendas.setLocationRelativeTo(null);
        relatorioVendas.setVisible(true);
    }//GEN-LAST:event_btnRelatorioActionPerformed

    public void incrementaVendas() {
        int total = this.vendas.size();
        TFQtdVendas.setText(String.valueOf(total));
    }

    public void atualizaValorFinal() {
        String tecnologiaNome = ListaTecnologias.getSelectedValue();
        String compradorNome = ListaCompradores.getSelectedValue();

        if (tecnologiaNome != null &&
                compradorNome != null &&
                !tecnologiaNome.equals("Nenhuma tecnologia cadastrada") &&
                !compradorNome.equals("Nenhum comprador cadastrado")) {

            try {
                Comprador comprador = listaCompradores.getCompradorByName(compradorNome);
                Tecnologia tecnologia = listaTecnologias.getTecnologiaByName(tecnologiaNome);
                Fornecedor fornVenda = tecnologia.getFornecedor();

                if (comprador == null || tecnologia == null) return;

                if (fornVenda == null) {
                    TFValorFinal.setText("Erro: Tec. sem Fornecedor");
                    return;
                }

                int desconto = 0;
                if (comprador.getComprasRealizadas() > 10) {
                    desconto = 10;
                } else {
                    desconto = comprador.getComprasRealizadas();
                }

                double valorBase = tecnologia.getValorBase();
                double valorFinal = valorBase;

                if (fornVenda.getArea() == Area.ALIMENTOS) {
                    valorFinal = valorBase + ((valorBase / 100) * (10 - desconto));
                } else if (fornVenda.getArea() == Area.EMERGENTE) {
                    valorFinal = valorBase + ((valorBase / 100) * (25 - desconto));
                } else if (fornVenda.getArea() == Area.ANDROIDES) {
                    valorFinal = valorBase + ((valorBase / 100) * (15 - desconto));
                } else if (fornVenda.getArea() == Area.TI) {
                    valorFinal = valorBase + ((valorBase / 100) * (20 - desconto));
                }

                TFValorFinal.setText(String.format("%.2f", valorFinal));

            } catch (Exception e) {
                System.out.println("Erro ao calcular valor final: " + e.getMessage());
                TFValorFinal.setText("Erro no cálculo");
            }
        } else {
            TFValorFinal.setText("");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                javax.swing.JFrame framePaiParaTeste = new javax.swing.JFrame();
                java.util.ArrayList<Comprador> listaTeste = new java.util.ArrayList<>();
                Compradores compradoresParaTeste = new Compradores(listaTeste);
                java.util.ArrayList<Tecnologia> listaTeste2 = new java.util.ArrayList<>();
                Tecnologias tecnologiasParaTeste = new Tecnologias(listaTeste2);
                java.util.ArrayList<Venda> listaTeste3 = new java.util.ArrayList<>();
                Vendas vendasParaTeste = new Vendas(listaTeste3);

                CadastroVendaDialog dialog = new CadastroVendaDialog(framePaiParaTeste, true, compradoresParaTeste, tecnologiasParaTeste, vendasParaTeste);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaCompradores;
    private javax.swing.JList<String> ListaTecnologias;
    private javax.swing.JTextArea TALog;
    private javax.swing.JTextField TFCodComprador;
    private javax.swing.JTextField TFData;
    private javax.swing.JTextField TFDescTec;
    private javax.swing.JTextField TFEmailComprador;
    private javax.swing.JTextField TFFornTec;
    private javax.swing.JTextField TFId;
    private javax.swing.JTextField TFIdTec;
    private javax.swing.JTextField TFModeloTec;
    private javax.swing.JTextField TFNomeComprador;
    private javax.swing.JTextField TFPaisComprador;
    private javax.swing.JTextField TFPesoTec;
    private javax.swing.JTextField TFQtdVendas;
    private javax.swing.JTextField TFTempTec;
    private javax.swing.JTextField TFValorFinal;
    private javax.swing.JTextField TFValorTec;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
