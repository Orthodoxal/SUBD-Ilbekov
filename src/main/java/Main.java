import logics.*;
import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Topic.class)
                .addAnnotatedClass(Librarian.class)
                .addAnnotatedClass(PrintedProducts.class)
                .addAnnotatedClass(Registry.class)
                .buildSessionFactory();

        /*TestPerformance testPerformance = new TestPerformance();
        testPerformance.startTest(sessionFactory);*/

        boolean isFinish = false;
        Scanner scanner = new Scanner(System.in);
        while (!isFinish) {
            System.out.println("Выберите сущность: ");
            System.out.println("1 — Билет читателя");
            System.out.println("2 — Читатель");
            System.out.println("3 — Тематика");
            System.out.println("4 — Библиотекарь");
            System.out.println("5 — Печатная продукция");
            System.out.println("6 — Регистр");
            System.out.println("7 — Выход\n");
            switch (scanner.nextInt()) {
                case 1 -> {
                    TicketLogic ticketLogic = new TicketLogic();
                    ticketLogic.start(sessionFactory);
                }
                case 2 -> {
                    ReaderLogic readerLogic = new ReaderLogic();
                    readerLogic.start(sessionFactory);
                }
                case 3 -> {
                    TopicLogic topicLogic = new TopicLogic();
                    topicLogic.start(sessionFactory);
                }
                case 4 -> {
                    LibrarianLogic librarianLogic = new LibrarianLogic();
                    librarianLogic.start(sessionFactory);
                }
                case 5 -> {
                    PrintedProductsLogic printedProductsLogic = new PrintedProductsLogic();
                    printedProductsLogic.start(sessionFactory);
                }
                case 6 -> {
                    RegistryLogic registryLogic = new RegistryLogic();
                    registryLogic.start(sessionFactory);
                }
                case 7 -> isFinish = true;
            }
        }
        sessionFactory.close();
    }
}

