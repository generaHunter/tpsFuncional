package Logica_Negocio;

import Logica_Negocio.Encargado;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-25T11:47:11")
@StaticMetamodel(TelefonoEncargado.class)
public class TelefonoEncargado_ { 

    public static volatile SingularAttribute<TelefonoEncargado, BigDecimal> idTelefono;
    public static volatile SingularAttribute<TelefonoEncargado, Encargado> idEncargado;
    public static volatile SingularAttribute<TelefonoEncargado, String> telefono;

}