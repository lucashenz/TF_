/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author arthurzimmer
 */
public class ConsultasDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConsultasDialog.class.getName());

    /**
     * Creates new form ConsultasDialog
     */
    public ConsultasDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // ====================== CORES E FONTES (igual à TelaInicial) ======================
        Color corFundo = Color.WHITE;
        Color corPrimaria = new Color(0, 80, 160);
        Color corHover = new Color(0, 60, 130);
        Color corTextoBotao = Color.WHITE;
        Color corFechar = new Color(220, 220, 220);
        Color corFecharHover = new Color(200, 200, 200);
        Color corTextoFechar = new Color(50, 50, 50);

        Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 26);
        Font fonteBotao = new Font("Segoe UI", Font.PLAIN, 18);
        Font fonteBotaoVoltar = new Font("Segoe UI", Font.PLAIN, 14);

        // ====================== LAYOUT MANUAL ======================
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(corFundo);

        // ====================== TÍTULO ======================
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(corFundo);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(40, 30, 30, 30));

        JLabel lblTitulo = new JLabel("O que você deseja consultar?");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setForeground(corPrimaria);
        panelTitulo.add(lblTitulo);

        // ====================== PAINEL DOS BOTÕES ======================
        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(corFundo);
        panelBotoes.setLayout(new GridLayout(4, 1, 0, 30)); // 4 botões, espaçamento maior
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 300, 50, 300)); // centraliza perfeitamente

        Dimension tamBotao = new Dimension(400, 60); // mesmo tamanho da TelaInicial (ajustado para caber bem)

        // Estilo dos 4 botões principais
        java.util.function.Consumer<JButton> estiloBotao = btn -> {
            btn.setPreferredSize(tamBotao);
            btn.setMinimumSize(tamBotao);
            btn.setMaximumSize(tamBotao);
            btn.setFont(fonteBotao);
            btn.setBackground(corPrimaria);
            btn.setForeground(corTextoBotao);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(true);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { btn.setBackground(corHover); }
                public void mouseExited(MouseEvent e)  { btn.setBackground(corPrimaria); }
            });
        };

        estiloBotao.accept(btnTecnologiaComMaiorValor);
        estiloBotao.accept(btnFornecedorComMaiorNumeroDeTec);
        estiloBotao.accept(btnCompradorComMaiorNumeroDeVendas);
        estiloBotao.accept(btnVendaComMaiorNumero);

        panelBotoes.add(btnTecnologiaComMaiorValor);
        panelBotoes.add(btnFornecedorComMaiorNumeroDeTec);
        panelBotoes.add(btnCompradorComMaiorNumeroDeVendas);
        panelBotoes.add(btnVendaComMaiorNumero);

        // ====================== BOTÃO VOLTAR (igual ao "Fechar" da TelaInicial) ======================
        JPanel panelSul = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSul.setBackground(corFundo);
        panelSul.setBorder(BorderFactory.createEmptyBorder(10, 30, 40, 40));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(fonteBotaoVoltar);
        btnVoltar.setBackground(corFechar);
        btnVoltar.setForeground(corTextoFechar);
        btnVoltar.setPreferredSize(new Dimension(110, 38));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setOpaque(true);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVoltar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnVoltar.setBackground(corFecharHover); }
            public void mouseExited(MouseEvent e)  { btnVoltar.setBackground(corFechar); }
        });

        btnVoltar.addActionListener(e -> dispose());

        panelSul.add(btnVoltar);

        // ====================== MONTAGEM FINAL ======================
        getContentPane().add(panelTitulo, BorderLayout.NORTH);
        getContentPane().add(panelBotoes, BorderLayout.CENTER);
        getContentPane().add(panelSul, BorderLayout.SOUTH);

        // ====================== CONFIGURAÇÕES DA JANELA ======================
        setSize(1024, 600);
        setMinimumSize(new Dimension(800, 500));
        setResizable(false);
        setLocationRelativeTo(parent);
        setTitle("Consultas");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTecnologiaComMaiorValor = new javax.swing.JButton();
        btnFornecedorComMaiorNumeroDeTec = new javax.swing.JButton();
        btnCompradorComMaiorNumeroDeVendas = new javax.swing.JButton();
        btnVendaComMaiorNumero = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnTecnologiaComMaiorValor.setText("Tecnologia com maior valor");

        btnFornecedorComMaiorNumeroDeTec.setText("Fornecedor com maior número de tecnologias");

        btnCompradorComMaiorNumeroDeVendas.setText("Comprador com maior número de vendas");

        btnVendaComMaiorNumero.setText("Venda com maior valor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFornecedorComMaiorNumeroDeTec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCompradorComMaiorNumeroDeVendas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVendaComMaiorNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTecnologiaComMaiorValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnTecnologiaComMaiorValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFornecedorComMaiorNumeroDeTec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCompradorComMaiorNumeroDeVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVendaComMaiorNumero)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                ConsultasDialog dialog = new ConsultasDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCompradorComMaiorNumeroDeVendas;
    private javax.swing.JButton btnFornecedorComMaiorNumeroDeTec;
    private javax.swing.JButton btnTecnologiaComMaiorValor;
    private javax.swing.JButton btnVendaComMaiorNumero;
    // End of variables declaration//GEN-END:variables
}
