package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, String> conteudo;
	public static volatile SingularAttribute<Post, Date> data;
	public static volatile SingularAttribute<Post, String> titulo;
	public static volatile SingularAttribute<Post, Long> id;
	public static volatile ListAttribute<Post, Comentario> comentarios;
	public static volatile SingularAttribute<Post, Usuario> autor;

}

