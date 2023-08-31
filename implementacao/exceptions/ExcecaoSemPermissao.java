package exceptions;

public class ExcecaoSemPermissao extends Exception{
    public ExcecaoSemPermissao() {
        super("Você não tem permissão para fazer isso.");
    }
}
