package exceptions;

public class ExcecaoTipoIncorreto extends Exception{

    // Construtor

    public ExcecaoTipoIncorreto(){
        super("O dígito informado para o campo de tipo de usuário está incorreto.");
    }
}
