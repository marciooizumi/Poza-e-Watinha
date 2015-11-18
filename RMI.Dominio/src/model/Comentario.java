package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Comentario implements Serializable{
    
    private static long serialVersionUID = 2524827577506827647L;
    
    @Id
    @GeneratedValue
    private long id;   
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data;
    @Column(columnDefinition="TEXT")
    private String mensagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
  
}
