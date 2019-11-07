package Logica_Negocio;

import Logica_Negocio.Matricula;
import Logica_Negocio.UsuarioGrado;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-03T16:59:08")
@StaticMetamodel(Grado.class)
public class Grado_ { 

    public static volatile ListAttribute<Grado, UsuarioGrado> usuarioGradoList;
    public static volatile SingularAttribute<Grado, String> grado;
    public static volatile SingularAttribute<Grado, BigDecimal> idGrado;
    public static volatile ListAttribute<Grado, Matricula> matriculaList;

}