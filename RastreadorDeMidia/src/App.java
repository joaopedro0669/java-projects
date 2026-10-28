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

        String msg = null;
        boolean ativo = true;
        Scanner s = new Scanner(System.in);
        while(ativo){
            limparTela();

            if(msg != null){
                System.out.println(msg);
                msg = null;
            }
            
            //não está logado
            if(!loggedIn){
                
                System.out.println("1 - Cadastrar\n2 - Entrar");
                String escolha = s.nextLine();

                //Sistema de cadastrar
                if (escolha.trim().equals("1")){
                    cadastrarUsuario(s, console, usuarios);

                    //Informar ao usuário
                    msg = new String("Usuário criado com sucesso!");
                }

                //sistema de entrar
                else if (escolha.trim().equals("2")){
                    String nome = null;
                    String senhaString = null;

                    System.out.print("Nome de usuário: ");
                    nome = s.nextLine();
                    char[] senha = console.readPassword("Senha: ");
                    senhaString = new String(senha);

                    for(Usuario u : usuarios){
                        u.login(nome, senhaString);
                        if(u.isLoggedIn()){
                            userAtivo = u;
                            loggedIn = true;
                            break;
                        }
                    }
                    if(!loggedIn){
                        msg = new String("Nome de usuário ou senha incorretos.");
                    }
                }
                
            }
        }
        s.close();
    }

    static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void cadastrarUsuario(Scanner s, Console console, LinkedList<Usuario> usuarios){
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
