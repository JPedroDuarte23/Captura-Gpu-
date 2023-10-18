package org.example.gui;

import org.example.gui.components.Botao;
import org.example.gui.components.TelaPanel;
import org.example.gui.services.ComponenteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends TelaPanel {
    private JButton botaoLogin;
    private JTextField campoEmail;

    private JPasswordField campoSenha;
    private JLabel textoAlerta;

    public LoginPanel(JPanel telas, JFrame janela) {
        super(telas, janela);

        ImageIcon imagemIcon = new ImageIcon("images/logo2.png");
        JLabel imagemLabel = new JLabel(imagemIcon);
        imagemLabel.setBounds(440, 105, 300, 100);
        JLabel txtLogin = new JLabel("Insira seu Email:");
        txtLogin.setBounds(440, 205, 300, 35);
        txtLogin.setForeground(Color.decode("#dcdcdc"));
        txtLogin.setFont(new Font("Montserrat", Font.BOLD, 20));
        campoEmail = new JTextField();
        campoEmail.setFont(new Font("Montserrat", Font.PLAIN, 18));
        campoEmail.setBounds(450, 250, 300, 35);

        JLabel txtSenha = new JLabel("Insira sua Senha:");
        txtSenha.setBounds(440, 295, 300, 35);
        txtSenha.setForeground(Color.decode("#dcdcdc"));
        txtSenha.setFont(new Font("Montserrat", Font.BOLD, 20));
        campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Montserrat", Font.PLAIN, 18));
        campoSenha.setBounds(450, 330, 300, 35);


        Botao botaoLogin = new Botao("Fazer Login");
        botaoLogin.setFont(new Font("Montserrat", Font.BOLD, 20));
        botaoLogin.setBounds(500,400,200,60);
        botaoLogin.addActionListener(this);

        textoAlerta = new JLabel("Usuário ou senha incorretos");
        textoAlerta.setFont(new Font("Montserrat", Font.PLAIN, 18));
        textoAlerta.setForeground(Color.red);
        textoAlerta.setBounds(495,470,250,60);
        textoAlerta.setVisible(false);

        this.add(imagemLabel);
        this.add(botaoLogin);
        this.add(txtLogin);
        this.add(campoEmail);
        this.add(txtSenha);
        this.add(campoSenha);
        this.add(textoAlerta);
    }
    public void executarBotao(ActionEvent e){
       if(logar()){
           trocarTela("Tela Principal");
       }else{
           textoAlerta.setVisible(true);
       };
    }
    public Boolean logar(){
        if(campoEmail.getText().equals("monitoons@monitoons.com")
                && campoSenha.getText().equals("12345678")){
            return true;
        } else {
            return false;
        }
    }
}


