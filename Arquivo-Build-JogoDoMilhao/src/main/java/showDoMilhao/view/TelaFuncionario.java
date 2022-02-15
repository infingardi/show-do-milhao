/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import showDoMilhao.controller.AdicionaPergunta;
import showDoMilhao.model.Funcionario;

/**
 *
 * @author igori
 */
public class TelaFuncionario extends JFrame{
    private Funcionario funcionario;
    
    private JPanel painelPrincipal;
    
    private JTextField jtPergunta;
    
    private JTextField jtPrimeiraAlternativa;
    private JTextField jtSegundaAlternativa;
    private JTextField jtTerceiraAlternativa;
    private JTextField jtQuartaAlternativa;
    
    private JTextField jtRespCorreta;
    
    public TelaFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        
        this.desenha();
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        desenhaMenu();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaMenu() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario"));
        painelFormulario.setLayout(new GridLayout(0, 2));
        
        JLabel jlPergunta = new JLabel("Pergunta: ");
        jtPergunta = new JTextField(20);
        painelFormulario.add(jlPergunta);
        painelFormulario.add(jtPergunta);
        
        JLabel jlPrimeiraAlternativa = new JLabel("Primeira Alternativa: ");
        jtPrimeiraAlternativa = new JTextField(20);
        painelFormulario.add(jlPrimeiraAlternativa);
        painelFormulario.add(jtPrimeiraAlternativa);
        
        JLabel jlSegundaAlternativa = new JLabel("Segunda Alternativa: ");
        jtSegundaAlternativa = new JTextField(20);
        painelFormulario.add(jlSegundaAlternativa);
        painelFormulario.add(jtSegundaAlternativa);
        
        JLabel jlTerceiraAlternativa = new JLabel("Terceira Alternativa: ");
        jtTerceiraAlternativa = new JTextField(20);
        painelFormulario.add(jlTerceiraAlternativa);
        painelFormulario.add(jtTerceiraAlternativa);
        
        JLabel jlQuartaAlternativa = new JLabel("Quarta Alternativa: ");
        jtQuartaAlternativa = new JTextField(20);
        painelFormulario.add(jlQuartaAlternativa);
        painelFormulario.add(jtQuartaAlternativa);
        
        JLabel jlRespCorreta = new JLabel("Alternativa Correta: ");
        jtRespCorreta = new JTextField(20);
        painelFormulario.add(jlRespCorreta);
        painelFormulario.add(jtRespCorreta);
        
        JButton adicionaPergunta = new JButton("Adiciona");
        adicionaPergunta.addActionListener(new AdicionaPergunta(this));
        painelFormulario.add(adicionaPergunta);
        
        this.painelPrincipal.add(painelFormulario, BorderLayout.CENTER);
    }

    public JTextField getJtPergunta() {
        return jtPergunta;
    }

    public void setJtPergunta(JTextField jtPergunta) {
        this.jtPergunta = jtPergunta;
    }

    public JTextField getJtPrimeiraAlternativa() {
        return jtPrimeiraAlternativa;
    }

    public void setJtPrimeiraAlternativa(JTextField jtPrimeiraAlternativa) {
        this.jtPrimeiraAlternativa = jtPrimeiraAlternativa;
    }

    public JTextField getJtSegundaAlternativa() {
        return jtSegundaAlternativa;
    }

    public void setJtSegundaAlternativa(JTextField jtSegundaAlternativa) {
        this.jtSegundaAlternativa = jtSegundaAlternativa;
    }

    public JTextField getJtTerceiraAlternativa() {
        return jtTerceiraAlternativa;
    }

    public void setJtTerceiraAlternativa(JTextField jtTerceiraAlternativa) {
        this.jtTerceiraAlternativa = jtTerceiraAlternativa;
    }

    public JTextField getJtQuartaAlternativa() {
        return jtQuartaAlternativa;
    }

    public void setJtQuartaAlternativa(JTextField jtQuartaAlternativa) {
        this.jtQuartaAlternativa = jtQuartaAlternativa;
    }

    public JTextField getJtRespCorreta() {
        return jtRespCorreta;
    }

    public void setJtRespCorreta(JTextField jtRespCorreta) {
        this.jtRespCorreta = jtRespCorreta;
    }
    
    
}
