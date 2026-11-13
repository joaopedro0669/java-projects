import Obras.*;
import Rastreios.*;
import Usuarios.Usuario;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        Console console = System.console();
        
        LinkedList<Usuario> usuarios = new LinkedList<>();
        LinkedList<Obra> obras = new LinkedList<>();
        exemplo(obras);

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
                    msg = "Usuário criado com sucesso!";
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
                        msg = "Nome de usuário ou senha incorretos.";
                    }
                    else {
                        msg = String.format("Seja bem-vindo, %s!", userAtivo.getNome());
                    }
                }
                
            }
            else {
                System.out.println("1 - Buscar obras \n2 - Criar obras \n3 - Obras consumidas \n4 - Obras criadas \n0 - Sair");
                String escolha = s.nextLine();

                if(escolha.trim().equals("1")){
                    buscarObras(s, obras);
                }

                else if(escolha.trim().equals("2")){
                    msg = criarObra(s, obras, userAtivo);
                }

                else if(escolha.trim().equals("3")){

                }

                else if(escolha.trim().equals("4")){

                }

                else if(escolha.trim().equals("0")){
                    userAtivo.logout();
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
        String tipo = "Null";
        if(o instanceof Filme)
            tipo = "Filme";
        else if(o instanceof Serie)
            tipo = "Serie";
        else if(o instanceof Jogo)
            tipo = "Jogo";

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

        System.out.println("1 - Busca livre \n2 - Busca por gênero \n3 - Busca por ID \n0 - Voltar ao menu");
        String escolha = s.nextLine();

        //Busca livre
        if(escolha.trim().equals("1")){
            System.out.print("Digite: ");
            String pesquisa = s.nextLine();
            LinkedList<Obra> titulo = new LinkedList<>();
            LinkedList<Obra> descricao = new LinkedList<>();
            LinkedList<Obra> diretor = new LinkedList<>();
            LinkedList<Obra> produtora = new LinkedList<>();
            LinkedList<Obra> desenvolvedora = new LinkedList<>();
            
            System.out.println("Pesquisando...");
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

                //produtora
                if(o instanceof Filme f && f.getProdutora().toLowerCase().contains(pesquisa.toLowerCase()))
                    produtora.add(o);

                if(o instanceof Serie serie && serie.getProdutora().toLowerCase().contains(pesquisa.toLowerCase()))
                    produtora.add(o);

                //desenvolvedora
                if(o instanceof Jogo j && j.getDesenvolvedora().toLowerCase().contains(pesquisa.toLowerCase()))
                    desenvolvedora.add(o);
            }
            
            //---mostrar
            limparTela();
            //título
            System.out.printf("Titulo: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : titulo){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }
            
            //descrição
            System.out.printf("\nDescrição: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : descricao){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }

            //diretor
            System.out.printf("\nDiretor: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : diretor){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }

            //produtora
            System.out.printf("\nProdutora: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : produtora){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }

            //desenvolvedora
            System.out.printf("\nDesenvolvedora: \n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : desenvolvedora){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }

            buscaPorID(s, obras);
        }

        //Busca por gênero
        else if(escolha.trim().equals("2")){
            System.out.print("Digite: ");
            String pesquisa = s.nextLine();
            ArrayList<String> generos = Arrays.stream(pesquisa.toLowerCase().split(",")).map(String::trim).collect(Collectors.toCollection(ArrayList::new));
            LinkedList<Obra> obrasBuscadas = new LinkedList<>();
            for(Obra o : obras){
                boolean apto = true;
                for(String gen1 : generos){
                    boolean achou = false;
                    for(String gen2 : o.getGeneros()){
                        if(gen1.equals(gen2))
                            achou = true;
                    }
                    if(!achou)
                        apto = false;
                }
                if(apto)
                    obrasBuscadas.add(o);
            }

            System.out.printf("\n%-4s | %-50s | %-4s | %-4s \n", "ID", "Título", "Nota", "Tipo");
            for(Obra o : obrasBuscadas){
                String tipo = coletarTipo(o);
                System.out.printf("%04d | %-50s | %.2f | %-4s \n", o.getID(), o.getTitulo(), o.getNota(), tipo);
            }

            buscaPorID(s, obras);
        }
        
        //Busca por ID
        else if(escolha.trim().equals("3")){
            buscaPorID(s, obras);
        }
    }

    static void buscaPorID(Scanner s, LinkedList<Obra> obras){
        System.out.print("\nDigite o ID: ");
        String escolhaIDString = s.nextLine();
        long escolhaIDLong = Long.parseLong(escolhaIDString.trim());
        boolean achou = false;
        limparTela();
        for(Obra o : obras){
            if(o.getID() == escolhaIDLong){
                o.mostrarInfo();
                achou = true;
                break;
            }
        }
        if(!achou){
            System.out.println("Obra não encontrada.\nPressione ENTER para prosseguir...");
            s.nextLine();

        }
        else{
            System.out.println("1 - Adicionar ao meu perfil \n0 - Voltar ao menu");
            String escolha = s.nextLine();

            if(escolha.trim().equals("1")){
                System.out.println("Em breve...");
            }
        }
    }

    static String criarObra(Scanner s, LinkedList<Obra> obras, Usuario userAtivo){
        limparTela();
        System.out.println("O que deseja adicionar ao catálogo do software? \n1 - Filme \n2 - Série \n3 - Jogo ");
        String escolha = s.nextLine();
        System.out.print("Digite o nome: ");
        String nome = s.nextLine();
        if(escolha.trim().equals("1")){
            System.out.print("Digite a duração do filme (em minutos): ");
            int duracao = Integer.parseInt(s.nextLine());
            Obra nova = new Filme(nome, userAtivo, duracao);
            obras.add(nova);
            return String.format("Filme %s com ID %d criado.", nome, nova.getID());
        }
        else if(escolha.trim().equals("2")){
            System.out.print("Digite a quantidade de episódios da série: ");
            int eps = Integer.parseInt(s.nextLine());
            Obra nova = new Serie(nome, userAtivo, eps);
            obras.add(nova);
            return String.format("Série %s com ID %d criada.", nome, nova.getID());
        }
        else if(escolha.trim().equals("3")){
            Obra nova = new Jogo(nome, userAtivo);
            obras.add(nova);
            return String.format("Jogo %s com ID %d criado.", nome, nova.getID());
        }
        return "Nenhum tipo válido selecionado.";
    }

    static void exemplo(LinkedList<Obra> obras){
        Obra o1 = new Filme("O Cavalo do Futuro", null, 120);
        Obra o2 = new Serie("Quebrando Ruim", null, 67);
        Obra o3 = new Jogo("Vermelho Morto", null);
        o1.adicionarGenero("Comédia");
        o1.adicionarGenero("ficção ");

        obras.add(o1);
        obras.add(o2);
        obras.add(o3);
    }
}