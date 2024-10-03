package br.com.fiap.fintech.model;

public class Usuario {

    private Long codusu;
    private String nome;
    private String sobrenome;
    private String login;
    private String email;
    private String senha;

    public Usuario(Long codusu, String nome, String sobrenome, String login) {
        this.codusu = codusu;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.email = email;
    }

    public Usuario(String nome, String sobrenome, String email, String login, String senha) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Long getCodusu() {
        return codusu;
    }

    public void setCodusu(Long codusu) {
        this.codusu = codusu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
