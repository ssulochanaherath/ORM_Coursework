package lk.ijse.config;

import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();

        try {
            FileReader fileReader = new FileReader("src/main/resources/hibernate.properties");
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Configuration configuration = (new Configuration()).addProperties(properties).addAnnotatedClass(Admin.class).addAnnotatedClass(Book.class).addAnnotatedClass(Branch.class).addAnnotatedClass(User.class).addAnnotatedClass(BorrowedBooks.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getFactoryConfiguration(){
        return factoryConfiguration == null ? (factoryConfiguration = new FactoryConfiguration()) : factoryConfiguration;
    }

    public Session getSession(){
        return this.sessionFactory.openSession();
    }
}
