package sonare.api_tcc.Exception;

public class CpfException extends RuntimeException{
    public CpfException(){
        super("Cpf inválido!");
    }
}
