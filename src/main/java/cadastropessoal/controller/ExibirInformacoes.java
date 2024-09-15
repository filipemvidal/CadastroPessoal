package cadastropessoal.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import cadastropessoal.view.Tela;

/**
 *
 * @author filip
 */
public class ExibirInformacoes implements MouseListener {

    private Tela tela;

    public ExibirInformacoes(Tela tela)
    {
        this.tela = tela;
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        if(e.getClickCount() == 2 && table.getSelectedRow() != -1)
            tela.exibirInformacoes();    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

}
