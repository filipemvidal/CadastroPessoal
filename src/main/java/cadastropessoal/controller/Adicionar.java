package cadastropessoal.controller;

import cadastropessoal.view.Tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author filip
 */
public class Adicionar implements ActionListener{
    
    Tela tela;
    
    public Adicionar(Tela tela)
    {
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        tela.AdicionarPesoa();
    }
    
}