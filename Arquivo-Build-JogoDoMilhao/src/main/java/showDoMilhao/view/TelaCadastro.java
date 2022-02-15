/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import showDoMilhao.controller.CadastraUsuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author igori
 */
public class TelaCadastro extends JFrame{
    private JPanel painelPrincipal;
    private JTextField jtUsuario;
    private JTextField jtSenha;
    private String classe = "Jogador";
    
    public TelaCadastro() {
        super("Show do Milhão");
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        desenhaCadastro();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaCadastro() {
        JPanel painelCadastro = new JPanel();
        
        JPanel painelUsuario = new JPanel();
        JLabel jlUsuario = new JLabel("Usuario: ");
        jtUsuario = new JTextField(25);
        painelUsuario.add(jlUsuario);
        painelUsuario.add(jtUsuario);
        painelCadastro.add(painelUsuario);

        JPanel painelSenha = new JPanel();        
        JLabel jlSenha = new JLabel("Senha: ");
        jtSenha = new JTextField(25);
        painelSenha.add(jlSenha);
        painelSenha.add(jtSenha);
        painelCadastro.add(painelSenha);
        
        String[] classStrings = { "Jogador", "Funcionario", "Gerente" };

        JComboBox classList = new JComboBox(classStrings);
        // Realiza a ação de mudança de escolha do ComboBox
        ActionListener handleComboBox = (ActionEvent e) -> {
            setClasse(classList.getSelectedItem().toString());
        };
        classList.setSelectedIndex(0);
        classList.addActionListener(handleComboBox);
        painelCadastro.add(classList);
        
        JPanel jpBotao = new JPanel();
        JButton btnCadastra = new JButton("Cadastrar");
        btnCadastra.addActionListener(new CadastraUsuario(this));
        jpBotao.add(btnCadastra);
        painelCadastro.add(jpBotao);

        // Cria uma margem em relação ao topo da página
        painelCadastro.setBorder(BorderFactory.createEmptyBorder(120,0,0,0));
  
        this.painelPrincipal.add(painelCadastro, BorderLayout.CENTER);
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    
}
