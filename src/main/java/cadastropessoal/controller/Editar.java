package cadastropessoal.controller;

import cadastropessoal.view.Tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author filip
 */
public class Editar implements ActionListener{
    
    Tela tela;
    
    public Editar(Tela tela)
    {
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        tela.EditarPessoa();
    }
    
}
