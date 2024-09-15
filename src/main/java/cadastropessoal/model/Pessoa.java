package cadastropessoal.model;

import java.time.LocalDate;

import cadastropessoal.exception.CpfException;
import cadastropessoal.exception.EmptyStrException;
import cadastropessoal.exception.InvalidDataException;

public final class Pessoa {

    private String nome;
    private Data nascimento;
    private Cpf cpf;
    private int idade;

    public Pessoa(){
        nascimento = new Data();
        cpf = new Cpf();
    }

    public Pessoa(String nome, String nascimento, String cpf) 
    throws CpfException, EmptyStrException, InvalidDataException
    {
        this.nascimento = new Data();
        this.cpf = new Cpf();
        
        setNome(nome);
        setNascimento(nascimento);
        setCpf(cpf);
    }

    public void setNome(String nome) throws EmptyStrException {
        if(nome.isBlank())
            throw new EmptyStrException();
        this.nome = nome;
    }

    public void setNascimento(String nascimento) throws InvalidDataException, EmptyStrException {
        if(nascimento.equals("  /  /    "))
            throw new EmptyStrException();
        
        int[] parser = Data.parser(nascimento);
        
        LocalDate dataAtual = LocalDate.now();
        int diaAtual = dataAtual.getDayOfMonth();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();
        
        if(parser[2]==anoAtual && 
          (parser[1]>mesAtual || 
          (parser[1]==mesAtual && parser[0]>diaAtual))){
            throw new InvalidDataException();
        }
        
        this.nascimento.set(parser[0], parser[1], parser[2]);
        calculaIdade();
    }


    public void setCpf(String cpf) throws CpfException, EmptyStrException {
        this.cpf.setCpf(cpf);
    }

    private void calculaIdade()
    {
        LocalDate dataAtual = LocalDate.now();
        int diaAtual = dataAtual.getDayOfMonth();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();

        this.idade = anoAtual - nascimento.getAno();

        if(mesAtual<nascimento.getMes() || 
        (mesAtual==nascimento.getMes() && diaAtual<nascimento.getDia()))
            this.idade--;
    }

    public Object[] toArray()
    {
        Object[] obj = {nome, nascimento, idade, cpf};
        return obj;
    }

    public String getNome() {
        return nome;
    }

    public Data getNascimento() {
        return nascimento;
    }

    public Cpf getCpf() {
        return cpf;
    }
    
    public int getIdade()
    {
        return idade;
    }
}
