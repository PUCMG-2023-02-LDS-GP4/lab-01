package exceptions;

public class ExcecaoUsuarioNaoEncontrado extends Exception{


    // Construtor

    public ExcecaoUsuarioNaoEncontrado(){
        super("Usuário não encontrado ou não cadastrado no sistema.");
    }

}
