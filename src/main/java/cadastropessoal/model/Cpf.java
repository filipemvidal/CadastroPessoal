package cadastropessoal.model;

import cadastropessoal.exception.*;

public class Cpf {
    private String cpf;
    
    public Cpf()
    {    
    }
    
    public Cpf(String cpf) throws CpfException, EmptyStrException
    {
        setCpf(cpf);
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) throws CpfException, EmptyStrException
    {
        if(cpf.equals("   .   .   -  ") || cpf.isBlank())
            throw new EmptyStrException();
        cpf = cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11) + cpf.substring(12);

        if(!cpfValido(cpf))
            throw new CpfException();
        
        this.cpf = cpf;
    }
    
    public static boolean cpfValido(String str)
    {
        if(str.length()!=11)
            return false;
        
        int sum = 0;
        int mult = 10;
        //soma os 9 primeiros digitos multiplicando de 10 a 2
        for(int i=0; i<9; i++)
        {
            int digit = Integer.parseInt(Character.toString(str.charAt(i)));
            sum += digit*(mult--);
        }
        
        int verifyDigit1 = Integer.parseInt(Character.toString(str.charAt(9)));
        int resto = sum % 11;
        if(resto <= 1)
        {
            //se o resto da divisão da soma por 11 for menor ou igual a 1
            //o digito verificador deve ser igual a 0
            if(verifyDigit1 != 0)
                return false;
        }else{
            //se o resto da divisão for maior que 1 o digito verificador 
            //deve ser igual a subtração 11 - resto
            int sub = 11 - resto;
            if(verifyDigit1 != sub)
                return false;
        }
        
        sum = 0;
        mult = 11;
        //soma os 10 primeiros digitos multiplicando de 11 a 2
        for(int i=0; i<10; i++)
        {
            int digit = Integer.parseInt(Character.toString(str.charAt(i)));
            sum += digit*(mult--);
        }
        
        int verifyDigit2 = Integer.parseInt(Character.toString(str.charAt(10)));
        resto = sum % 11;
        if(resto <= 1)
        {
            //se o resto da divisão da soma por 11 for menor ou igual a 1
            //o digito verificador deve ser igual a 0
            if(verifyDigit2 != 0)
                return false;
        } else{
            //se o resto da divisão for maior que 1 o digito verificador 
            //deve ser igual a subtração 11 - resto
            int sub = 11 - resto;
            if(verifyDigit2 != sub)
                return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-" + cpf.substring(9);
    } 
}
