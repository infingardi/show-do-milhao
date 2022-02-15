/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import showDoMilhao.controller.ChamaTelaCadastro;
import showDoMilhao.controller.ChamaTelaTabela;
import showDoMilhao.controller.LoginUsuario;

/**
 *
 * @author igori
 */
public class TelaLogin extends JFrame{
    
    private JPanel painelPrincipal;
    private JTextField jtUsuario;
    private JTextField jtSenha;
    
    public TelaLogin() {
        super("Show do Milhão");
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        desenhaLogin();
        desenhaBotaoTabela();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaLogin() {
        JPanel painelLogin = new JPanel();
        
        JPanel painelUsuario = new JPanel();
        JLabel jlUsuario = new JLabel("Usuario: ");
        jtUsuario = new JTextField(25);
        painelUsuario.add(jlUsuario);
        painelUsuario.add(jtUsuario);
        painelLogin.add(painelUsuario);

        JPanel painelSenha = new JPanel();        
        JLabel jlSenha = new JLabel("Senha: ");
        jtSenha = new JTextField(25);
        painelSenha.add(jlSenha);
        painelSenha.add(jtSenha);
        painelLogin.add(painelSenha);
        
        JPanel jpBotao = new JPanel();
        
        JButton btnEntra = new JButton("Entrar");
        btnEntra.addActionListener(new LoginUsuario(this));
        jpBotao.add(btnEntra);
        
        JButton btnCadastra = new JButton("Cadastrar");
        btnCadastra.addActionListener(new ChamaTelaCadastro(this));
        jpBotao.add(btnCadastra);
        painelLogin.add(jpBotao);
        
        // Cria uma margem em relação ao topo da página
        painelLogin.setBorder(BorderFactory.createEmptyBorder(120,0,0,0));
        
        this.painelPrincipal.add(painelLogin, BorderLayout.CENTER);
    }
    
    public void desenhaBotaoTabela() {
        JButton btnTabela = new JButton("Tabela");
        btnTabela.addActionListener(new ChamaTelaTabela(this));
        this.painelPrincipal.add(btnTabela, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        tela.desenha();
    }

    public JTextField getJtUsuario() {
        return jtUsuario;
    }

    public void setJtUsuario(JTextField jtUsuario) {
        this.jtUsuario = jtUsuario;
    }

    public JTextField getJtSenha() {
        return jtSenha;
    }

    public void setJtSenha(JTextField jtSenha) {
        this.jtSenha = jtSenha;
    }
}
