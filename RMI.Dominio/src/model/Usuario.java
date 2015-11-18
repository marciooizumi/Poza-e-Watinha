package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2424827977506827647L;
    
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String senha;
    private String email;
    @OneToMany(mappedBy = "autor", targetEntity = Post.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;

    public Usuario() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    private List<Post> getPosts() {
        return posts;
    }

    private void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
