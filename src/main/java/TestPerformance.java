import models.Librarian;
import models.PrintedProducts;
import models.Registry;
import models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class TestPerformance {
    private Session session = null;
    public void startTest(SessionFactory sessionFactory){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        testRegistry();
        session.getTransaction().commit();
    }
    private void testRegistry(){
        long timeStart = System.currentTimeMillis();
        session.createQuery("SELECT a from Registry a", Registry.class);
        System.out.println("Запрос на таблицу регистра: " + (System.currentTimeMillis() - timeStart) + "мс");

        Date issueDate = new Date("04/20/2022");
        Registry registry = new Registry(new java.sql.Date(issueDate.getTime()), false,
                session.get(Ticket.class, 8),
                session.get(Librarian.class, 1),
                session.get(PrintedProducts.class, 8));
        timeStart = System.currentTimeMillis();
        session.save(registry);
        System.out.println("Запрос на добавление в таблицу регистра: " + (System.currentTimeMillis() - timeStart) + "мс");

        List<Registry> registryList = session.createQuery("SELECT a from Registry a", Registry.class).getResultList();
        registry = registryList.get(registryList.size() - 1);
        timeStart = System.currentTimeMillis();
        session.delete(registry);
        System.out.println("Запрос на удаление из таблицы регистра: " + (System.currentTimeMillis() - timeStart) + "мс");
    }
}
