package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comentario.class)
public abstract class Comentario_ {

	public static volatile SingularAttribute<Comentario, Post> post;
	public static volatile SingularAttribute<Comentario, Date> data;
	public static volatile SingularAttribute<Comentario, String> mensagem;
	public static volatile SingularAttribute<Comentario, Usuario> usuario;
	public static volatile SingularAttribute<Comentario, Long> id;

}

