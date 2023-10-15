package org.example.gui.components;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {
        public Botao(String texto) {
            super(texto);
            this.setBounds(520,470,200,60);
            this.setBackground(Color.decode("#E87E1C"));
            this.setForeground(Color.decode("#dcdcdc"));
            this.setFocusPainted(false);
            this.setBorderPainted(false);

        }
    }


