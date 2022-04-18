package logics;
import models.Reader;
import models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ReaderLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления читателя: ");
        System.out.println("Введите 2 для вывода читателей: ");
        System.out.println("Введите 3 для изменения данных читателя: ");
        System.out.println("Введите 4 для удаления читателя: ");
        System.out.println("Введите 5 для фильтрации: ");
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (scanner.nextInt()) {
            case 1 -> create();
            case 2 -> read();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> filterRead();
        }
        session.getTransaction().commit();
    }

    private void create(){
        System.out.println("Введите имя: ");
        String firstName = scanner.next();
        System.out.println("Введите фамилию: ");
        String lastName = scanner.next();
        System.out.println("Введите телефон: ");
        String phone = scanner.next();
        System.out.println("Введите номер билета");
        int ticketId = scanner.nextInt();

        Reader reader = new Reader(firstName, lastName, phone, session.get(Ticket.class, ticketId));
        session.save(reader);
    }

    private void read(){
        List<Reader> readerList = session.createQuery("SELECT a from Reader a", Reader.class).getResultList();
        System.out.println(readerList);
    }

    private void update(){
        System.out.println("Введите id читателя: ");
        int id = scanner.nextInt();

        System.out.println("Введите имя: ");
        String firstName = scanner.next();
        System.out.println("Введите фамилию: ");
        String lastName = scanner.next();
        System.out.println("Введите телефон: ");
        String phone = scanner.next();

        Reader reader = session.get(Reader.class, id);
        reader.setFirstName(firstName);
        reader.setLastName(lastName);
        reader.setPhone(phone);
        session.save(reader);
    }

    private void delete(){
        System.out.println("Введите id читателя: ");
        int id = scanner.nextInt();

        Reader reader = session.get(Reader.class, id);
        session.delete(reader);
    }

    private  void  filterRead(){
        System.out.println("Введите 1 для фильтра по имени: ");
        System.out.println("Введите 2 для фильтра по фамилии: ");
        System.out.println("Введите 3 для фильтра по телефону: ");
        List<Reader> readerList = null;
        switch (scanner.nextInt()){
            case 1:
                System.out.println("Введите имя: ");
                String name = scanner.next();
                readerList = session.createQuery("SELECT a from Reader a where firstName = \'" + name + "\'", Reader.class).getResultList();
                break;
            case 2:
                System.out.println("Введите фамилию");
                String lastName = scanner.next();
                readerList = session.createQuery("SELECT a from Reader a where lastName = \'" + lastName + "\'", Reader.class).getResultList();
                break;
            case 3:
                System.out.println("Введите телефон: ");
                String phone = scanner.next();
                readerList = session.createQuery("SELECT a from Reader a where phone = \'" + phone + "\'", Reader.class).getResultList();
                break;
        }
        System.out.println(readerList);
    }
}
