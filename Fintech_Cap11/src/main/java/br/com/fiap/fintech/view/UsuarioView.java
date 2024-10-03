package br.com.fiap.fintech.view;

import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.exception.EntidadeNaoEcontradaException;
import br.com.fiap.fintech.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDao dao;
        System.out.println("Bem vindo ao App Fintech");

        try {
            dao = new UsuarioDao();
            int op;

            do{
                System.out.println("Escolha uma opção: \n1-Cadastrar \n2-Pesquisar por Código \n3-Listar  \n4-Atualizar \n5-Remover \n0-Sair");
                op = scanner.nextInt();
                switch (op) {
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    case 2:
                        pesquisarUsuario(scanner, dao);
                        break;
                    case 3:
                        listar(dao);
                        break;
                    case 4:
                        atualizar(scanner, dao);
                        break;
                    case 5:
                        removerUsuario(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (op != 0);
            dao.fecharConexao();
        }catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

    }

    private static void cadastrar(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o nome do usuario:");
        String nome = scanner.next() + scanner.nextLine();

        System.out.println("Digite o sobrenome:");
        String sobrenome = scanner.next() + scanner.nextLine();

        System.out.println("Digite o login:");
        String login = scanner.next() + scanner.nextLine();

        System.out.println("Digite a senha:");
        String senha = scanner.next() + scanner.nextLine();

        System.out.println("Digite o email:");
        String email = scanner.next() + scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, sobrenome, login, senha, email);
        try {
            dao.cadastrar(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Usuario: " + e.getMessage());
        }
    }

    private static void pesquisarUsuario(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o código do usuario:");
        long codusu = scanner.nextLong();
        try {
            Usuario usuario = dao.pesquisar(codusu);
            System.out.println("Produto encontrado:");
            System.out.println(usuario.getCodusu() + " - " + usuario.getNome() + ", " + usuario.getSobrenome() +  " - Login: " + usuario.getLogin() );
        } catch (SQLException | EntidadeNaoEcontradaException e) {
            System.err.println("Erro ao pesquisar produto: " + e.getMessage());
        }
    }

    private static void listar(UsuarioDao dao) {
        try {
            List<Usuario> usuarios = dao.listar();
            System.out.println("Lista os usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getCodusu() + " - " + usuario.getNome() + " " + usuario.getSobrenome() + ", E-mail: " + usuario.getLogin()  );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuarios: " + e.getMessage());
        }
    }


    private static void removerUsuario(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o código do usuario que deseja remover:");
        long codusu = scanner.nextLong();
        try {
            dao.remover(codusu);
            System.out.println("Usuario removido com sucesso!");
        } catch (SQLException | EntidadeNaoEcontradaException e) {
            System.err.println("Erro ao remover usuario: " + e.getMessage());
        }
    }


    private static void atualizar(Scanner scanner, UsuarioDao dao) {
        System.out.println("Digite o código do usuario que deseja atualizar:");
        long codusu = scanner.nextLong();
        try {
            Usuario usuario = dao.pesquisar(codusu);

            System.out.println("Digite o novo nome do usuario:");
            String nome = scanner.next() + scanner.nextLine();

            System.out.println("Digite o sobrenome:");
            String sobrenome = scanner.next() + scanner.nextLine();

            System.out.println("Digite o novo email:");
            String email = scanner.next() + scanner.nextLine();

            System.out.println("Digite o login:");
            String login = scanner.next() + scanner.nextLine();

            System.out.println("Digite a senha:");
            String senha = scanner.next() + scanner.nextLine();

            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setEmail(email);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            //salvar os dados
            dao.atualizar(usuario);

            System.out.println("Usuario atualizado com sucesso!");
        } catch (SQLException | EntidadeNaoEcontradaException e) {
            System.err.println("Erro ao atualizar usuario: " + e.getMessage());
        }
    }


}
