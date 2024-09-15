package cadastropessoal.controller;

import cadastropessoal.view.Tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author filip
 */
public class Remover implements ActionListener{
    
    Tela tela;
    
    public Remover(Tela tela)
    {
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        tela.RemoverPessoa();
    }
    
}