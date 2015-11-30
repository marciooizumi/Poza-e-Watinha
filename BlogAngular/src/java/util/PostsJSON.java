/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import model.Post;
import model.Usuario;

/**
 *
 * @author Márcio
 */
public class PostsJSON {

    public String convertPosts(List<Post> lista) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Post p : lista) {
            builder.add(Json.createObjectBuilder()
                    .add("id", p.getId())
                    .add("autor",Json.createObjectBuilder()
                        .add("id", p.getAutor().getId())
                        .add("nome", p.getAutor().getNome())
                        .add("email", p.getAutor().getEmail()))
                    .add("data", p.getData().toString())
                    .add("titulo", p.getTitulo())
                    .add("conteudo", p.getConteudo()));
        }
        return builder.build().toString();
    }
    
    public String convertUser(Long id, String nome) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
       
            builder.add(Json.createObjectBuilder()
                    .add("id", id)
                    .add("nome", nome));
        
        return builder.build().toString();
    }
}
