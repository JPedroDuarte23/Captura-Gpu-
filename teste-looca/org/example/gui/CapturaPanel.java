package org.example.gui;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.example.gui.components.Componente;
import org.example.gui.components.TelaPanel;
import org.example.gui.services.ComponenteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CapturaPanel extends TelaPanel {
    public CapturaPanel(JPanel telas, JFrame janela) {
        super(telas, janela);
        JLabel txtMensagem = new JLabel("""
        Fique tranquilo, o Monitoons já está monitorando
        """);
        JLabel txtMensagem2 = new JLabel("""
                os componentes do seu computador!
                """);
        txtMensagem.setBounds(325, 205, 800, 35);
        txtMensagem2.setBounds(395, 240, 500, 35);
        txtMensagem.setForeground(Color.decode("#dcdcdc"));
        txtMensagem2.setForeground(Color.decode("#dcdcdc"));
        txtMensagem.setFont(new Font("Montserrat", Font.BOLD, 25));
        txtMensagem2.setFont(new Font("Montserrat", Font.BOLD, 25));
        this.add(txtMensagem);
        this.add(txtMensagem2);

    }
    public void executarBotao(ActionEvent e) {
        trocarTela("Tela Login");
    }
}
