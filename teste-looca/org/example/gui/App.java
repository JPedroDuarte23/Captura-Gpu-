package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame {
    public App() {
        this.setBounds(0, 0, 1200, 700);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        CardLayout controleTela = new CardLayout();
        JPanel telas = new JPanel(controleTela);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        LoginPanel loginPanel = new LoginPanel(telas, this);
        CapturaPanel capturaPanel = new CapturaPanel(telas, this);
        capturaPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                capturaPanel.iniciarCapturas();
            }
        });

        telas.add(loginPanel, "Tela Login");
        telas.add(capturaPanel, "Tela Principal");


        this.add(telas);

        this.setVisible(true);


        // janela - TV
        // login - canal 1
        // principal canal 2
    }
}
