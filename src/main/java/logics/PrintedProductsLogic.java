package logics;

import models.PrintedProducts;
import models.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class PrintedProductsLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления печатной продукции: ");
        System.out.println("Введите 2 для вывода печатной продукции: ");
        System.out.println("Введите 3 для изменения данных печатной продукции: ");
        System.out.println("Введите 4 для удаления печатной продукции: ");
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
        System.out.println("Введите название печатной продукции: ");
        String title = scanner.next();
        System.out.println("Введите имя автора: ");
        String firstName = scanner.next();
        System.out.println("Введите фамилию автора: ");
        String lastName = scanner.next();
        System.out.println("Введите количество: ");
        int amount = scanner.nextInt();
        System.out.println("Введите номер темы: ");
        int topicId = scanner.nextInt();

        PrintedProducts printedProducts = new PrintedProducts(title, firstName, lastName, amount, session.get(Topic.class, topicId));
        session.save(printedProducts);
    }

    private void read(){
        List<PrintedProducts> printedProductsList = session.createQuery("SELECT a from PrintedProducts a", PrintedProducts.class).getResultList();
        System.out.println(printedProductsList);
    }

    private void update(){
        System.out.println("Введите id печатной продукции: ");
        int id = scanner.nextInt();

        System.out.println("Введите название печатной продукции: ");
        String title = scanner.next();
        System.out.println("Введите имя автора: ");
        String firstName = scanner.next();
        System.out.println("Введите фамилию автора: ");
        String lastName = scanner.next();
        System.out.println("Введите количество: ");
        int amount = scanner.nextInt();

        PrintedProducts printedProducts = session.get(PrintedProducts.class, id);
        printedProducts.setTitle(title);
        printedProducts.setFirstName(firstName);
        printedProducts.setLastName(lastName);
        printedProducts.setAmount(amount);
        session.save(printedProducts);
    }

    private void delete(){
        System.out.println("Введите id печатной продукции: ");
        int id = scanner.nextInt();

        var pp = session.get(PrintedProducts.class, id);
        PrintedProducts printedProducts = session.get(PrintedProducts.class, id);
        session.delete(printedProducts);
    }

    private  void  filterRead(){
        System.out.println("Введите 1 для фильтра по названию: ");
        System.out.println("Введите 2 для фильтра по имени автора: ");
        System.out.println("Введите 3 для фильтра по фамилии автора: ");
        System.out.println("Введите 4 для фильтра по количеству: ");
        List<PrintedProducts> printedProductsList = null;
        switch (scanner.nextInt()){
            case 1:
                System.out.println("Введите название: ");
                String title = scanner.next();
                printedProductsList = session.createQuery("SELECT a from PrintedProducts a where title = \'" + title + "\'", PrintedProducts.class).getResultList();
                break;
            case 2:
                System.out.println("Введите имя: ");
                String name = scanner.next();
                printedProductsList = session.createQuery("SELECT a from PrintedProducts a where firstName = \'" + name + "\'", PrintedProducts.class).getResultList();
                break;
            case 3:
                System.out.println("Введите фамилию");
                String lastName = scanner.next();
                printedProductsList = session.createQuery("SELECT a from PrintedProducts a where lastName = \'" + lastName + "\'", PrintedProducts.class).getResultList();
                break;
            case 4:
                System.out.println("Введите количество: ");
                String patronymic = scanner.next();
                printedProductsList = session.createQuery("SELECT a from PrintedProducts a where amount = \'" + patronymic + "\'", PrintedProducts.class).getResultList();
                break;
        }
        System.out.println(printedProductsList);
    }
}
