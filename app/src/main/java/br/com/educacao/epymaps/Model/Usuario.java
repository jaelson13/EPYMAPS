package br.com.educacao.epymaps.Model;

import java.util.Date;

public class Usuario {

    private Long idUsuario;
    private String nome;
    private String senha;
    private String email;
    private String sexo;
    private String cidade;
    private String estado;

    public String getSexo() {
       return sexo;
    }

    public void setSexo(String sexo) {
        if(sexo == "Feminino"){
            this.sexo = "F";
        }else{
            this.sexo = "M";
        }
    }

    public Long getIdUsuario() {return idUsuario;}

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
