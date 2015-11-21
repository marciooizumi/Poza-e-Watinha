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
import model.Usuario;

/**
 *
 * @author Márcio
 */
public class UsuarioJSON {

    public String convert(List<Usuario> lista) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Usuario u : lista) {
            builder.add(Json.createObjectBuilder()
                    .add("id", u.getId())
                    .add("nome", u.getNome())
                    .add("email", u.getEmail()));
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
