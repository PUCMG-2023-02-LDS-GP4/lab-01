import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

import business.Sistema;
import exceptions.ExcecaoUsuarioNaoEncontrado;

public class Main {


    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws InvalidParameterException, IOException, ExcecaoUsuarioNaoEncontrado {
        Sistema s = new Sistema();
        
        System.out.println(" _   _       _                    _     _           _           ______           ");
        System.out.println("| | | |     (_)                  (_)   | |         | |          |  _  \\          ");
        System.out.println("| | | |_ __  ___   _____ _ __ ___ _  __| | __ _  __| | ___      | | | |_____   __");
        System.out.println("| | | | '_ \\| \\ \\ / / _ \\ '__/ __| |/ _` |/ _` |/ _` |/ _ \\     | | | / _ \\ \\ / /");
        System.out.println("| |_| | | | | |\\ V /  __/ |  \\__ \\ | (_| | (_| | (_| |  __/  _  | |/ /  __/\\ V / ");
        System.out.println(" \\___/|_| |_|_| \\_/ \\___|_|  |___/_|\\__,_|\\__,_|\\__,_|\\___| (_) |___/ \\___| \\_/  ");
        System.out.println("                                                                                ");
        System.out.println("Para utilizar o sistema realize o login.");
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("(1) Digite o nome de usuário:");
        String nomeUsuario = scanner.nextLine();

        System.out.println("(2) Digite a senha:");
        String senha = scanner.nextLine();       
        s.logar(nomeUsuario, senha);
    }

    public static void menu(Sistema sistema) throws Exception{
    }

}










                                                                                 
