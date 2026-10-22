import Obras.*;
import Rastreios.*;
import Usuarios.Usuario;

import java.io.Console;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Console console = System.console();
        
        LinkedList<Usuario> usuarios = new LinkedList<>();
        LinkedList<Obra> obras = new LinkedList<>();
        Usuario userAtivo;
        boolean loggedIn = false;

        boolean ativo = true;
        while(ativo){
            limparTela();
            
            if(!loggedIn){
                Scanner s = new Scanner(System.in);
                System.out.println("1 - Cadastrar\n2 - Entrar");
                String escolha = s.nextLine();

                //Sistema de cadastrar
                if(escolha.trim().equals("1")){
                    boolean nomeAprovado;
                    boolean senhaAprovada;
                    String nome = null;
                    String senhaString = null;

                    limparTela();
                    System.out.println("Cadastro de usuário");

                    //Criar nome de usuário
                    do {
                        nomeAprovado = true;
                        System.out.print("Nome de usuário: ");
                        nome = s.nextLine();
                        for(Usuario u : usuarios){
                            if(u.getNome().equalsIgnoreCase(nome)){
                                System.out.println("Nome de usuário já existente.");
                                nomeAprovado = false;
                                break;
                            }
                        }

                    } while(!nomeAprovado);

                    //Criar senha de usuário
                    do{
                        senhaAprovada = true;
                        char[] senha = console.readPassword("Senha: ");
                        char[] senha2 = console.readPassword("Repita a senha: ");
                        if(!Arrays.equals(senha, senha2)){
                            System.out.println("As senhas não se coincidem.");
                            senhaAprovada = false;
                        }
                        else{
                            senhaString = new String(senha);
                        }
                    } while(!senhaAprovada);

                    //Criar e adicionar usuário
                    usuarios.add(new Usuario(nome, senhaString));
                }
            }
        }
    }

    static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
