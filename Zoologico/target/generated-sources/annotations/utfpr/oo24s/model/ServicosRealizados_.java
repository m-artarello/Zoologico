package utfpr.oo24s.model;

import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;
import utfpr.oo24s.model.Servico;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-19T20:43:16", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ServicosRealizados.class)
public class ServicosRealizados_ { 

    public static volatile SingularAttribute<ServicosRealizados, Animal> animal;
    public static volatile SingularAttribute<ServicosRealizados, Servico> servico;
    public static volatile SingularAttribute<ServicosRealizados, Calendar> datahora;
    public static volatile SingularAttribute<ServicosRealizados, Long> servicosrealizadosid;
    public static volatile SingularAttribute<ServicosRealizados, Profissional> profissional;

}