package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class App extends JFrame {
    public App() {
        this.setBounds(0, 0, 1200, 700);


        CardLayout controleTela = new CardLayout();
        JPanel telas = new JPanel(controleTela);

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
