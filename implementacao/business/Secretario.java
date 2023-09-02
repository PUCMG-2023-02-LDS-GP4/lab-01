package business;

import java.util.HashMap;
import java.util.Map;

public class Secretario extends Usuario{
        // Construtores
    public Secretario(String nome, String usuario, String senha, Map<Integer,Disciplina> disciplinasLecionadas) {
        super(nome, usuario, senha);
    }

    public Secretario(String nome, String usuario, String senha) {
        super(nome, usuario, senha);
    }

    

}
