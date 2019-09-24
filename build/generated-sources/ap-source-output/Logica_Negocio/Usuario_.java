package Logica_Negocio;

import Logica_Negocio.TelefonoUsuario;
import Logica_Negocio.TipoUsuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-24T12:01:02")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Date> fechaNac;
    public static volatile SingularAttribute<Usuario, Character> estado;
    public static volatile ListAttribute<Usuario, TelefonoUsuario> telefonoUsuarioList;
    public static volatile SingularAttribute<Usuario, String> pass;
    public static volatile SingularAttribute<Usuario, BigDecimal> idUsuario;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> dui;
    public static volatile SingularAttribute<Usuario, TipoUsuario> idTipo;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> username;

}