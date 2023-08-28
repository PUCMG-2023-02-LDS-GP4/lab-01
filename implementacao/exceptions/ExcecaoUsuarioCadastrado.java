package exceptions;

public class ExcecaoUsuarioCadastrado extends Exception{

    // Construtor

    public ExcecaoUsuarioCadastrado(){
        super("Usuário já cadastrado.");
    }
}
