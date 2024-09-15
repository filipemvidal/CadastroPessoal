package cadastropessoal.model;

import cadastropessoal.exception.InvalidDataException;
import java.time.LocalDate;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(){}

    public Data(int dia, int mes, int ano) throws InvalidDataException
    {
        set(dia, mes, ano);
    }

    public void set(int dia, int mes, int ano) throws InvalidDataException
    {
        if(dia<1 || dia>30 || mes<1 || mes > 12 || ano < 0 || ano>LocalDate.now().getYear())
            throw new InvalidDataException();

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAno() {
        return this.ano;
    }

    @Override
    public String toString()
    {   
        return String.format("%1$02d/%2$02d/%3$04d", dia, mes, ano);
    }

    public static int[] parser(String data)
    {
        String strDia = data.substring(0, 2);
        String strMes = data.substring(3, 5);
        String strAno = data.substring(6);

        int[] parseData = {Integer.parseInt(strDia), Integer.parseInt(strMes), Integer.parseInt(strAno)};
        return parseData;
    }
}
