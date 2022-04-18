package logics;
import models.Librarian;
import models.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class LibrarianLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления библиотекаря: ");
        System.out.println("Введите 2 для вывода библиотекарей: ");
        System.out.println("Введите 3 для изменения данных библиотекаря: ");
        System.out.println("Введите 4 для удаления библиотекаря: ");
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
        System.out.println("Введите номер темы: ");
        int topicId = scanner.nextInt();

        Librarian librarian = new Librarian(firstName, lastName, phone, session.get(Topic.class, topicId));
        session.save(librarian);
    }

    private void read(){
        List<Librarian> librarianList = session.createQuery("SELECT a from Librarian a", Librarian.class).getResultList();
        System.out.println(librarianList);
    }

    private void update(){
        System.out.println("Введите id библиотекаря: ");
        int id = scanner.nextInt();

        System.out.println("Введите имя: ");
        String firstName = scanner.next();
        System.out.println("Введите фамилию: ");
        String lastName = scanner.next();
        System.out.println("Введите телефон: ");
        String phone = scanner.next();

        Librarian librarian = session.get(Librarian.class, id);
        librarian.setFirstName(firstName);
        librarian.setLastName(lastName);
        librarian.setPhone(phone);
        session.save(librarian);
    }

    private void delete(){
        System.out.println("Введите id библиотекаря: ");
        int id = scanner.nextInt();

        Librarian librarian = session.get(Librarian.class, id);
        session.delete(librarian);
    }

    private  void  filterRead(){
        System.out.println("Введите 1 для фильтра по имени: ");
        System.out.println("Введите 2 для фильтра по фамилии: ");
        System.out.println("Введите 3 для фильтра по телефону: ");
        List<Librarian> librarianList = null;
        switch (scanner.nextInt()){
            case 1:
                System.out.println("Введите имя: ");
                String name = scanner.next();
                librarianList = session.createQuery("SELECT a from Librarian a where firstName = \'" + name + "\'", Librarian.class).getResultList();
                break;
            case 2:
                System.out.println("Введите фамилию");
                String lastName = scanner.next();
                librarianList = session.createQuery("SELECT a from Librarian a where lastName = \'" + lastName + "\'", Librarian.class).getResultList();
                break;
            case 3:
                System.out.println("Введите телефон: ");
                String patronymic = scanner.next();
                librarianList = session.createQuery("SELECT a from Librarian a where phone = \'" + patronymic + "\'", Librarian.class).getResultList();
                break;
        }
        System.out.println(librarianList);
    }
}