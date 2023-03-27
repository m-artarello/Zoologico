package utfpr.oo24s.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utfpr.oo24s.model.Profissional;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-19T20:44:24", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Animal.class)
public class Animal_ { 

    public static volatile SingularAttribute<Animal, Profissional> treinador;
    public static volatile SingularAttribute<Animal, String> raca;
    public static volatile SingularAttribute<Animal, String> nome;
    public static volatile SingularAttribute<Animal, Long> animalid;

}