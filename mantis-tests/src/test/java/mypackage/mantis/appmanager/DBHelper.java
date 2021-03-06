package mypackage.mantis.appmanager;

import mypackage.mantis.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper(ApplicationManager app) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    public List<UserData> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where access_level=25" ).list();
        for ( UserData user : result ) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
