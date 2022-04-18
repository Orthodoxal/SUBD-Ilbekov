package logics;

import models.Librarian;
import models.PrintedProducts;
import models.Registry;
import models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RegistryLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления записи: ");
        System.out.println("Введите 2 для вывода записей: ");
        System.out.println("Введите 3 для изменения данных записи: ");
        System.out.println("Введите 4 для удаления записи: ");
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
        System.out.println("Введите дату выдачи: ");
        String date = scanner.next();
        Date issueDate = new Date(date);
        System.out.println("Введите статус возврата");
        boolean returned = scanner.nextBoolean();
        System.out.println("Введите номер билета");
        int ticketId = scanner.nextInt();
        System.out.println("Введите номер библиотекаря");
        int librarianId = scanner.nextInt();
        System.out.println("Введите номер печатной продукции");
        int printedProductsId = scanner.nextInt();

        Registry registry = new Registry(new java.sql.Date(issueDate.getTime()), returned,
                session.get(Ticket.class, ticketId),
                session.get(Librarian.class, librarianId),
                session.get(PrintedProducts.class, printedProductsId));
        session.save(registry);
    }

    private void read(){
        List<Registry> registryList = session.createQuery("SELECT a from Registry a", Registry.class).getResultList();
        System.out.println(registryList);
    }

    private void update(){
        System.out.println("Введите id записи: ");
        int id = scanner.nextInt();

        System.out.println("Введите дату выдачи: ");
        String date = scanner.next();
        Date issueDate = new Date(date);
        System.out.println("Введите статус возврата");
        boolean returned = scanner.nextBoolean();
        System.out.println("Введите номер билета");
        int ticketId = scanner.nextInt();
        System.out.println("Введите номер библиотекаря");
        int librarianId = scanner.nextInt();
        System.out.println("Введите номер печатной продукции");
        int printedProductsId = scanner.nextInt();

        Registry reader = session.get(Registry.class, id);
        reader.setIssueDate(new java.sql.Date(issueDate.getTime()));
        reader.setReturned(returned);
        reader.setTicket(session.get(Ticket.class, ticketId));
        reader.setLibrarian(session.get(Librarian.class, librarianId));
        reader.setPrintedProducts(session.get(PrintedProducts.class, printedProductsId));
        session.save(reader);
    }

    private void delete(){
        System.out.println("Введите id записи: ");
        int id = scanner.nextInt();

        Registry registry = session.get(Registry.class, id);
        session.delete(registry);
    }

    private  void  filterRead(){
        System.out.println("Введите 1 для фильтра по имени: ");
        System.out.println("Введите 2 для фильтра по фамилии: ");
        System.out.println("Введите 3 для фильтра по телефону: ");
        List<Registry> registryList = null;
        switch (scanner.nextInt()){
            case 1:
                System.out.println("Введите статус возврата: ");
                boolean returned = scanner.nextBoolean();
                registryList = session.createQuery("SELECT a from Registry a where returned = \'" + returned + "\'", Registry.class).getResultList();
                break;
            case 2:

                break;
            case 3:

                break;
        }
        System.out.println(registryList);
    }
}