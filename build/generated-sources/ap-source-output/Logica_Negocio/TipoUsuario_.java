package Logica_Negocio;

import Logica_Negocio.Usuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-03T16:59:08")
@StaticMetamodel(TipoUsuario.class)
public class TipoUsuario_ { 

    public static volatile SingularAttribute<TipoUsuario, String> tipo;
    public static volatile ListAttribute<TipoUsuario, Usuario> usuarioList;
    public static volatile SingularAttribute<TipoUsuario, BigDecimal> idTipo;

}