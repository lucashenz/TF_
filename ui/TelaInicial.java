/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import entidades.*;


import java.util.ArrayList;

/**
 *
 * @author arthurzimmer
 */
public class TelaInicial extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaInicial.class.getName());
    private Fornecedores listaFornecedores = new Fornecedores(new ArrayList<>());
    private Tecnologias listaTecnologias = new Tecnologias(new ArrayList<>());
    private Compradores listaCompradores = new Compradores(new ArrayList<>());
    private Vendas listaVendas = new Vendas(new ArrayList<>());
    /**
     * Creates new form NewJFrame
     */
    /**
     * Creates new form NewJFrame
     */
    public TelaInicial() {
        initComponents();

        // --- DEFINIÇÃO DA PALETA DE CORES E FONTES ---
        java.awt.Color corFundo = java.awt.Color.WHITE; 
        java.awt.Color corPrimaria = new java.awt.Color(0, 80, 160); // Azul-marinho
        java.awt.Color corHover = new java.awt.Color(0, 60, 130); // Azul mais escuro
        java.awt.Color corTextoBotao = java.awt.Color.WHITE; 
        java.awt.Color corFechar = new java.awt.Color(220, 220, 220); 
        java.awt.Color corFecharHover = new java.awt.Color(200, 200, 200);
        java.awt.Color corTextoFechar = new java.awt.Color(50, 50, 50); 
        
        java.awt.Font fonteTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26);
        java.awt.Font fonteBotao = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18);
        java.awt.Font fonteBotaoFechar = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);

        
        // --- 2. CONFIGURAÇÃO DA JANELA ---
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().setBackground(corFundo);

        
        // --- 3. PAINEL NORTE (TÍTULO) ---
        javax.swing.JPanel panelTitulo = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        panelTitulo.setBackground(corFundo);
        panelTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 30, 30, 30));
        
        jLabel1.setFont(fonteTitulo);
        jLabel1.setForeground(corPrimaria);
        panelTitulo.add(jLabel1);

        
        // --- 4. PAINEL CENTRAL (BOTÕES) ---
        javax.swing.JPanel panelBotoes = new javax.swing.JPanel();
        panelBotoes.setBackground(corFundo);

        panelBotoes.setLayout(new javax.swing.BoxLayout(panelBotoes, javax.swing.BoxLayout.Y_AXIS));
        // Ajuste no padding para focar no centramento
        panelBotoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 100, 20, 100)); // Padding horizontal menor
        panelBotoes.setAlignmentX(CENTER_ALIGNMENT); // Centraliza o painel dentro do BorderLayout.CENTER

        // Definição de tamanho para os botões: 300 de largura por 70 de altura
        java.awt.Dimension tamBotao = new java.awt.Dimension(300, 70);

        java.util.function.Consumer<javax.swing.JButton> aplicarEstilo = (botao) -> {
            botao.setPreferredSize(tamBotao);
            botao.setMinimumSize(tamBotao); // Garante a altura mínima
            botao.setMaximumSize(tamBotao); // Garante que a largura e altura não excedam
            
            botao.setFont(fonteBotao);
            botao.setBackground(corPrimaria);
            botao.setForeground(corTextoBotao);
            botao.setFocusPainted(false);
            botao.setOpaque(true); 
            botao.setBorderPainted(false);
            botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            
            // Adiciona a centralização no eixo X para o BoxLayout
            botao.setAlignmentX(CENTER_ALIGNMENT); 

            // Hover
            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corHover);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(corPrimaria);
                }
            });
        };

        // Aplica o estilo, incluindo os tamanhos e o alinhamento central
        aplicarEstilo.accept(btnFornecedores);
        aplicarEstilo.accept(btnTecnologia);
        aplicarEstilo.accept(btnCompradores);
        aplicarEstilo.accept(btnVendas);
        aplicarEstilo.accept(btnConsulta);
        aplicarEstilo.accept(btnDados);
        

        // adiciona botões com espaçamento vertical
        panelBotoes.add(btnFornecedores);
        panelBotoes.add(javax.swing.Box.createVerticalStrut(20));
        panelBotoes.add(btnTecnologia);
        panelBotoes.add(javax.swing.Box.createVerticalStrut(20));
        panelBotoes.add(btnCompradores);
        panelBotoes.add(javax.swing.Box.createVerticalStrut(20));
        panelBotoes.add(btnVendas);
        panelBotoes.add(javax.swing.Box.createVerticalStrut(20));
        panelBotoes.add(btnConsulta);
        panelBotoes.add(javax.swing.Box.createVerticalStrut(20));
        panelBotoes.add(btnDados);
        
        // --- 5. PAINEL SUL (BOTÃO FECHAR) ---
        javax.swing.JPanel panelFechar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        panelFechar.setBackground(corFundo);
        panelFechar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 30, 30));

        btnFinalizar.setFont(fonteBotaoFechar);
        btnFinalizar.setBackground(corFechar);
        btnFinalizar.setForeground(corTextoFechar);
        btnFinalizar.setPreferredSize(new java.awt.Dimension(100, 35));
        btnFinalizar.setFocusPainted(false);
        
        // --- CORREÇÃO PARA MACOS (BOTÃO FECHAR) ---
        btnFinalizar.setOpaque(true);
        // --- FIM DA CORREÇÃO ---
        
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar.setBackground(corFecharHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar.setBackground(corFechar);
            }
        });
        
        panelFechar.add(btnFinalizar);

        
        // --- 6. MONTAGEM FINAL ---
        getContentPane().add(panelTitulo, java.awt.BorderLayout.NORTH);
        getContentPane().add(panelBotoes, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelFechar, java.awt.BorderLayout.SOUTH);

        
        // --- 7. TAMANHO DA JANELA ---
        
        this.setSize(1024, 800); 
        this.setMinimumSize(new java.awt.Dimension(600, 700)); 
        this.setResizable(true); 
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFinalizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnFornecedores = new javax.swing.JButton();
        btnCompradores = new javax.swing.JButton();
        btnTecnologia = new javax.swing.JButton();
        btnVendas = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnDados = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exercício 3 - POO");

        btnFinalizar.setText("Fechar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Qual categoria deseja gerenciar?");

        btnFornecedores.setText("Fornecedores");
        btnFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedoresActionPerformed(evt);
            }
        });

        btnCompradores.setText("Compradores");
        btnCompradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompradoresActionPerformed(evt);
            }
        });

        btnTecnologia.setText("Tecnologia");
        btnTecnologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTecnologiaActionPerformed(evt);
            }
        });

        btnVendas.setText("Vendas");
        btnVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendasActionPerformed(evt);
            }
        });

        btnDados.setText("Dados");
        btnDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadosActionPerformed(evt);
            }
        });


        btnConsulta.setText("Consulta");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        // Removido o GroupLayout gerado para evitar conflitos com o Border/BoxLayout manual no construtor
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedoresActionPerformed
        CadastroFornecedorDialog janelaFornecedor = new CadastroFornecedorDialog(this, true, this.listaFornecedores);
        
        this.setVisible(false);
        janelaFornecedor.setLocationRelativeTo(null);
        janelaFornecedor.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_btnFornecedoresActionPerformed

    private void btnDadosActionPerformed(java.awt.event.ActionEvent evt) {

        DadosDialog tela = new DadosDialog(this, true, listaFornecedores, listaTecnologias, listaCompradores, listaVendas);

        this.setVisible(false);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.setVisible(true);
    }


    private void btnCompradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompradoresActionPerformed
        CadastroCompradorDialog janelaComprador = new CadastroCompradorDialog(this, true, listaCompradores);
        
        this.setVisible(false);
        janelaComprador.setLocationRelativeTo(null);
        janelaComprador.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_btnCompradoresActionPerformed

    private void btnTecnologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnologiaActionPerformed
        CadastroTecnologiaDialog janelaTecnologia = new CadastroTecnologiaDialog(this, true, this.listaFornecedores, this.listaTecnologias);
        
        this.setVisible(false);
        janelaTecnologia.setLocationRelativeTo(null);
        janelaTecnologia.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_btnTecnologiaActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultasDialog janelaConsulta = new ConsultasDialog(this, true, listaTecnologias);
        
        this.setVisible(false);
        janelaConsulta.setLocationRelativeTo(null);
        janelaConsulta.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        CadastroVendaDialog janelaVenda = new CadastroVendaDialog(this, true, listaCompradores, listaTecnologias, listaVendas);
        
        this.setVisible(false);
        janelaVenda.setLocationRelativeTo(null);
        janelaVenda.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_btnVendasActionPerformed

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Define o Look and Feel para ser o do sistema operacional (mais profissional)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // Cria e centraliza a tela
            TelaInicial tela = new TelaInicial();
            tela.setVisible(true);
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompradores;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnFornecedores;
    private javax.swing.JButton btnTecnologia;
    private javax.swing.JButton btnDados;
    private javax.swing.JButton btnVendas;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}