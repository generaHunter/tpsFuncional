package Logica_Negocio;

import Logica_Negocio.Encargado;
import Logica_Negocio.Matricula;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-03T16:59:08")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, Date> fechaNac;
    public static volatile SingularAttribute<Alumno, BigDecimal> idAlumno;
    public static volatile SingularAttribute<Alumno, Character> estado;
    public static volatile SingularAttribute<Alumno, Encargado> idEncargado;
    public static volatile SingularAttribute<Alumno, String> apellido;
    public static volatile ListAttribute<Alumno, Matricula> matriculaList;
    public static volatile SingularAttribute<Alumno, String> nombre;

}