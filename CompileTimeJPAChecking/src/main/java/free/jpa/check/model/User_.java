package free.jpa.check.model;

import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel(value = free.jpa.check.model.User.class)
//@javax.annotation.Generated(value = "org.apache.openjpa.persistence.meta.AnnotationProcessor6", date = "Mon Mar 04 16:47:46 CET 2013")

public class User_ {
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> privilegeLevel;
    public static volatile SingularAttribute<User, String> userDigestedPasswd;
    public static volatile SingularAttribute<User, Integer> userId;
}
