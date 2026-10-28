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

        Usuario userAtivo = null;
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
            else {
                System.out.println("1 - Buscar obras \n2 - Criar obras \n3 - Consumir obras \n4 - Obras criadas \n0 - Sair");
                String escolha = s.nextLine();

                if(escolha.trim().equals("1")){
                    buscarObras(s, obras);
                }

                else if(escolha.trim().equals("0")){
                    userAtivo = null;
                    loggedIn = false;
                }
            }
        }
        s.close();
    }

    static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static String coletarTipo(Obra o){
        String tipo = new String("Null");
        if(o instanceof Filme)
            tipo = new String("Filme");
        else if(o instanceof Serie)
            tipo = new String("Serie");
        else if(o instanceof Jogo)
            tipo = new String("Jogo");

        return tipo;
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

    static void buscarObras(Scanner s, LinkedList<Obra> obras){
        limparTela();

        System.out.println("1 - Busca livre \n2 - Buscar por gênero");
        String escolha = s.nextLine();

        //Busca livre
        if(escolha.trim().equals("1")){
            String pesquisa = s.nextLine();
            LinkedList<Obra> titulo = new LinkedList<>();
            LinkedList<Obra> descricao = new LinkedList<>();
            LinkedList<Obra> diretor = new LinkedList<>();
            LinkedList<Obra> desenvolvedora = new LinkedList<>();
            
            //filtrar
            for(Obra o : obras){
                //titulo
                if (o.getTitulo().toLowerCase().contains(pesquisa.toLowerCase()))
                    titulo.add(o);
                
                //descrição
                if (o.getDescricao().toLowerCase().contains(pesquisa.toLowerCase()))
                    descricao.add(o);

                //diretor
                if(o instanceof Filme f && f.getDiretor().toLowerCase().contains(pesquisa.toLowerCase()))
                    diretor.add(o);

                if(o instanceof Serie serie && serie.getDiretor().toLowerCase().contains(pesquisa.toLowerCase()))
                    diretor.add(o);

                //desenvolvedora
                if(o instanceof Jogo j && j.getDesenvolvedora().toLowerCase().contains(pesquisa.toLowerCase()))
                    desenvolvedora.add(o);
            }

            //mostrar
            //título
            System.out.printf("Titulo: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : titulo){
                //pegar tipo
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }
            
            //descrição
            //...

            //diretor
            //...

            //desenvolvedora
            //...
        }

        //Busca por gênero
        else if(escolha.trim().equals("2")){
            System.out.println("Em breve...");
        }
    }
}
