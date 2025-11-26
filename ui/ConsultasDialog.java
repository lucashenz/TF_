package ui;

import entidades.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ConsultasDialog extends JDialog {

    private JButton btnTecnologiaComMaiorValor;
    private JButton btnFornecedorComMaiorNumeroDeTec;
    private JButton btnCompradorComMaiorNumeroDeVendas;
    private JButton btnVendaComMaiorNumero;
    private Tecnologias tecnologias;

    public ConsultasDialog(Frame parent, boolean modal, Tecnologias tecnologias) {
        super(parent, modal);

        this.tecnologias = tecnologias;

        // ====================== PALETA DE CORES ======================
        Color corFundo = Color.WHITE;
        Color corPrimaria = new Color(0, 80, 160);   // #0050A0
        Color corHover = new Color(0, 60, 130);      // #003C82
        Color corCard = new Color(245, 247, 250);    // #F5F7FA

        Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 30);
        Font fonteBotao = new Font("Segoe UI", Font.BOLD, 17);

        setLayout(new BorderLayout());
        getContentPane().setBackground(corFundo);

        // ====================== HEADER (CENTRALIZADO) ======================
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(corFundo);
        header.setBorder(BorderFactory.createEmptyBorder(35, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Consultas Disponíveis");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setForeground(corPrimaria);

        header.add(lblTitulo);

        // ====================== PAINEL CENTRAL (CARD) ======================
        JPanel card = new JPanel(new GridLayout(4, 1, 0, 20));
        card.setBackground(corCard);
        card.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        btnTecnologiaComMaiorValor =
                criarBotao("Tecnologia com maior valor", fonteBotao, corPrimaria, corHover);

        btnFornecedorComMaiorNumeroDeTec =
                criarBotao("Fornecedor com maior número de tecnologias", fonteBotao, corPrimaria, corHover);

        btnCompradorComMaiorNumeroDeVendas =
                criarBotao("Comprador com maior número de vendas", fonteBotao, corPrimaria, corHover);

        btnVendaComMaiorNumero =
                criarBotao("Venda com maior valor", fonteBotao, corPrimaria, corHover);

        card.add(btnTecnologiaComMaiorValor);
        card.add(btnFornecedorComMaiorNumeroDeTec);
        card.add(btnCompradorComMaiorNumeroDeVendas);
        card.add(btnVendaComMaiorNumero);

        // ====================== BOTÃO VOLTAR ======================
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btnVoltar.setPreferredSize(new Dimension(120, 45));
        btnVoltar.setBackground(new Color(230, 230, 230));
        btnVoltar.setForeground(new Color(50, 50, 50));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVoltar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnVoltar.setBackground(new Color(210, 210, 210)); }
            public void mouseExited(MouseEvent e)  { btnVoltar.setBackground(new Color(230, 230, 230)); }
        });

        btnVoltar.addActionListener(e -> dispose());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(corFundo);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        footer.add(btnVoltar);

        // EVENTOS
        adicionarEventos();

        // MONTAGEM
        add(header, BorderLayout.NORTH);
        add(card, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        // CONFIG JANELA
        setSize(900, 550);
        setLocationRelativeTo(parent);
        setResizable(false);
        setTitle("Consultas");
    }

    // ====================== MÉTODO DE BOTÃO ======================
    private JButton criarBotao(String texto, Font fonte, Color corPrimaria, Color corHover) {
        JButton btn = new JButton(texto);
        btn.setFont(fonte);
        btn.setBackground(corPrimaria);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(400, 60));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(corHover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(corPrimaria); }
        });

        return btn;
    }

    // ====================== EVENTOS ======================
    private void adicionarEventos() {
        btnTecnologiaComMaiorValor.addActionListener(e -> tecnologiaComMaiorValor());
        btnFornecedorComMaiorNumeroDeTec.addActionListener(e -> fornecedorComMaiorNumeroDeTecnologias());
        btnCompradorComMaiorNumeroDeVendas.addActionListener(e -> compradorComMaiorNumeroVendas());
        btnVendaComMaiorNumero.addActionListener(e -> vendaComMaiorValor());
    }

    // ====================== MÉTODOS A IMPLEMENTAR ======================
    private void tecnologiaComMaiorValor() {
        Tecnologia tec = tecnologias.getTecnologiaComMaiorValor();

        if (tec == null) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma tecnologia cadastrada.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Tecnologia com maior valor:\n" + tec.toString());
    }

    private void fornecedorComMaiorNumeroDeTecnologias() {
        JOptionPane.showMessageDialog(this, "Implementar consulta: fornecedor com mais tecnologias.");
    }

    private void compradorComMaiorNumeroVendas() {
        JOptionPane.showMessageDialog(this, "Implementar consulta: comprador com mais vendas.");
    }

    private void vendaComMaiorValor() {
        JOptionPane.showMessageDialog(this, "Implementar consulta: venda com maior valor.");
    }
}
