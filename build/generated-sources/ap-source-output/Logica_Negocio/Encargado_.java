package Logica_Negocio;

import Logica_Negocio.Alumno;
import Logica_Negocio.TelefonoEncargado;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-24T12:01:02")
@StaticMetamodel(Encargado.class)
public class Encargado_ { 

    public static volatile SingularAttribute<Encargado, BigDecimal> idEncargado;
    public static volatile SingularAttribute<Encargado, String> apellido;
    public static volatile SingularAttribute<Encargado, String> direccion;
    public static volatile SingularAttribute<Encargado, String> dui;
    public static volatile ListAttribute<Encargado, Alumno> alumnoList;
    public static volatile ListAttribute<Encargado, TelefonoEncargado> telefonoEncargadoList;
    public static volatile SingularAttribute<Encargado, String> nombre;

}