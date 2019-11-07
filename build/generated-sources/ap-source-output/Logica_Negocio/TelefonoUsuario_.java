package Logica_Negocio;

import Logica_Negocio.Usuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-03T16:59:08")
@StaticMetamodel(TelefonoUsuario.class)
public class TelefonoUsuario_ { 

    public static volatile SingularAttribute<TelefonoUsuario, BigDecimal> idTelefono;
    public static volatile SingularAttribute<TelefonoUsuario, Usuario> idUsuario;
    public static volatile SingularAttribute<TelefonoUsuario, String> telefono;

}