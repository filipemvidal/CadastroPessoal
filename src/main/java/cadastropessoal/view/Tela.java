package cadastropessoal.view;

import net.miginfocom.swing.MigLayout;
import cadastropessoal.exception.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import cadastropessoal.model.Pessoa;
import cadastropessoal.persistence.PessoaPersistence;
import java.util.List;
import cadastropessoal.controller.*;
import javax.swing.table.DefaultTableModel;

public class Tela {
    private JFrame tela;

    private JTextField tfNome;
    private JFormattedTextField tfData;
    private JFormattedTextField tfCpf;
    private Font font;
    private Container backPanel;
    private List<Pessoa> listaPessoas;
    private JTable table;
    private DefaultTableModel tableModel;

    public void draw()
    {
        tela = new JFrame();
        tela.getContentPane().setLayout(new MigLayout("top, center, fill"));
        tela.getContentPane().setPreferredSize(new Dimension(1000, 600));
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);

        backPanel = tela.getContentPane();
        font = new Font("sans-serif", Font.PLAIN, 20);
        drawInput();
        drawTable();

        tela.pack();
    }

    private void drawInput()
    {
        JPanel panel = new JPanel(new MigLayout("center, center, fillx", "20[]20", "[|||||]80[]20[]"));
        panel.setPreferredSize(new Dimension(300, 600));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        backPanel.add(panel, "center, center, grow");
        
        //<Nome>
        JLabel lbNome = new JLabel("Nome:");
        lbNome.setFont(font);
        panel.add(lbNome,"wrap, center");
        
        tfNome = new JTextField(20);
        tfNome.setFont(font);
        panel.add(tfNome, "wrap, center");
        //</Nome>
        
        //<Data>
        JLabel lbData = new JLabel("Data de nascimento:");
        lbData.setFont(font);
        panel.add(lbData,"wrap, center");
        
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            tfData = new JFormattedTextField(maskData);
        } catch (ParseException e) {}
        tfData.setColumns(20);
        tfData.setFont(font);
        panel.add(tfData, "wrap, center");
        //</Data>
        
        //<Cpf>
        JLabel lbCpf = new JLabel("CPF:");
        lbCpf.setFont(font);
        panel.add(lbCpf, "wrap, center");
        
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            tfCpf = new JFormattedTextField(maskCpf);
        } catch (ParseException e) {}
        tfCpf.setColumns(20);
        tfCpf.setFont(font);
        panel.add(tfCpf, "wrap, center");
        //</Cpf>

        //<BotãoAdiconar>
        JButton jbAdicionar = new JButton("Adicionar");
        jbAdicionar.setFont(font);
        jbAdicionar.addActionListener(new Adicionar(this));
        panel.add(jbAdicionar, "split 2, center");
        //</BotãoAdicionar>
        
        //<BotãoRemover>
        JButton jbRemover = new JButton("Remover");
        jbRemover.setFont(font);
        jbRemover.addActionListener(new Remover(this));
        panel.add(jbRemover, "gapx 50, wrap");
        //</BotãoRemover>
        
        //<BotãoRemover>
        JButton jbEditar = new JButton("Editar");
        jbEditar.setFont(font);
        jbEditar.addActionListener(new Editar(this));
        panel.add(jbEditar, "center");
        //</BotãoRemover>
    }

    private void drawTable()
    {
        JPanel panel = new JPanel(new MigLayout("top, center, fill"));
        panel.setPreferredSize(new Dimension(700, 600));
        backPanel.add(panel, "grow");
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Data de nascimento");
        tableModel.addColumn("Idade");
        tableModel.addColumn("CPF");
        
        PessoaPersistence persistence = new PessoaPersistence();
        listaPessoas = persistence.read();

        for(Pessoa pessoa : listaPessoas)
            tableModel.addRow(pessoa.toArray());

        table = new JTable(tableModel);
        table.setFont(font);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new ExibirInformacoes(this));
        
        
        JScrollPane barraRolagem = new JScrollPane(table);
        barraRolagem.setBorder(BorderFactory.createEmptyBorder());
        panel.add(barraRolagem, "grow");
    }
    
    public void AdicionarPesoa()
    {
        Pessoa pessoa = new Pessoa();
        try{
           pessoa.setNome(tfNome.getText());
           pessoa.setNascimento(tfData.getText());
           pessoa.setCpf(tfCpf.getText());
        }
        catch(InvalidDataException e){
            JOptionPane.showMessageDialog(tela, "Data inválida.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(CpfException e){
            JOptionPane.showMessageDialog(tela, "CPF inválido.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(EmptyStrException e){
            JOptionPane.showMessageDialog(tela, "Um ou mais campos não foram preenchidos.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        tableModel.addRow(pessoa.toArray());
        
        listaPessoas.add(pessoa);
        PessoaPersistence persistence = new PessoaPersistence();
        persistence.save(listaPessoas);
    }
    
    public void RemoverPessoa()
    {
        listaPessoas.remove(table.getSelectedRow());
        PessoaPersistence persistence = new PessoaPersistence();
        persistence.save(listaPessoas);
        tableModel.removeRow(table.getSelectedRow());
    }
    
    public void EditarPessoa()
    {
        Pessoa pessoa = new Pessoa();
        try{
           pessoa.setNome(tfNome.getText());
           pessoa.setNascimento(tfData.getText());
           pessoa.setCpf(tfCpf.getText());
        }
        catch(InvalidDataException e){
            JOptionPane.showMessageDialog(tela, "Data inválida.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(CpfException e){
            JOptionPane.showMessageDialog(tela, "CPF inválido.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(EmptyStrException e){
            JOptionPane.showMessageDialog(tela, "Um ou mais campos não foram preenchidos.", 
                    "ENTRADA INVÀLIDA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        listaPessoas.set(table.getSelectedRow(), pessoa);
        PessoaPersistence persistence = new PessoaPersistence();
        persistence.save(listaPessoas);
        
        tableModel.setValueAt(pessoa.getNome(), table.getSelectedRow(), 0);
        tableModel.setValueAt(pessoa.getNascimento(), table.getSelectedRow(), 1);
        tableModel.setValueAt(pessoa.getIdade(), table.getSelectedRow(), 2);
        tableModel.setValueAt(pessoa.getCpf(), table.getSelectedRow(), 3);
    }
    
    public void exibirInformacoes()
    {
        Pessoa pessoa = listaPessoas.get(table.getSelectedRow());
        tfNome.setText(pessoa.getNome());
        tfData.setText(pessoa.getNascimento().toString());
        tfCpf.setText(pessoa.getCpf().toString());
    }
}
