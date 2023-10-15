package org.example.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPanel extends JPanel implements ActionListener {
    private JPanel telas;
    private CardLayout controleTela;
    private JFrame janela;
    public TelaPanel(JPanel telas, JFrame janela) {

        this.telas = telas;
        this.controleTela = (CardLayout) telas.getLayout();
        this.janela = janela;
        this.setBackground(Color.decode("#0D0324"));
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        executarBotao(e);
    }

    protected void executarBotao(ActionEvent e) {

    }

    protected void trocarTela(String identificador) {
        controleTela.show(telas,identificador);
    }
























}
