package Logica_Negocio;

import Logica_Negocio.MateriaUsuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-03T16:59:08")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile SingularAttribute<Materia, BigDecimal> idMateria;
    public static volatile ListAttribute<Materia, MateriaUsuario> materiaUsuarioList;
    public static volatile SingularAttribute<Materia, String> materia;

}