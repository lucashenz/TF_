package ui;

import entidades.*;
import javax.swing.*;

public class DadosDialog extends JDialog {

    private Fornecedores fornecedores;
    private Tecnologias tecnologias;
    private Compradores compradores;
    private Vendas vendas;

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
    }

    private void initComponents() {

        setTitle("Gerenciamento de Dados");
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JButton btnSalvar = new JButton("Salvar Dados");

        btnSalvar.addActionListener(e -> {
            salvarDados();
        });

        JPanel painel = new JPanel();
        painel.add(btnSalvar);

        add(painel);
    }

    // 👉 Aqui você implementará o método real depois
    private void salvarDados() {
        JOptionPane.showMessageDialog(this, "Método de salvar ainda será implementado.");
    }
}
