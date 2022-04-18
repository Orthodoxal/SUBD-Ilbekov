package logics;

import models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TicketLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления билета: ");
        System.out.println("Введите 2 для вывода билетов: ");
        System.out.println("Введите 3 для изменения данных билета: ");
        System.out.println("Введите 4 для удаления билета: ");
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
        Date receivingDate = new Date(date);
        System.out.println("Введите дату истечения: ");
        date = scanner.next();
        Date expirationDate = new Date(date);

        Ticket ticket = new Ticket(new java.sql.Date(receivingDate.getTime()), new java.sql.Date(expirationDate.getTime()));
        session.save(ticket);
    }

    private void read(){
        List<Ticket> ticketList = session.createQuery("SELECT a from Ticket a", Ticket.class).getResultList();
        System.out.println(ticketList);
    }

    private void update(){
        System.out.println("Введите id билета: ");
        int id = scanner.nextInt();

        System.out.println("Введите дату выдачи: ");
        String date = scanner.next();
        Date receivingDate = new Date(date);
        System.out.println("Введите дату истечения: ");
        date = scanner.next();
        Date expirationDate = new Date(date);

        Ticket ticket = session.get(Ticket.class, id);
        ticket.setReceivingDate(new java.sql.Date(receivingDate.getTime()));
        ticket.setExpirationDate(new java.sql.Date(expirationDate.getTime()));
        session.save(ticket);
    }

    private void delete(){
        System.out.println("Введите id билета: ");
        int id = scanner.nextInt();

        Ticket ticket = session.get(Ticket.class, id);
        session.delete(ticket);
    }

    private  void  filterRead(){
        System.out.println("Введите 1 для фильтра по дате выдачи: ");
        System.out.println("Введите 2 для фильтра по дате истечения: ");
        List<Ticket> ticketList = null;
        String date;
        switch (scanner.nextInt()){
            case 1:
                System.out.println("Введите дату выдачи: ");
                date = scanner.next();
                Date receivingDate = new Date(date);
                ticketList = session.createQuery("SELECT a from Ticket a where firstName = \'"
                        + new java.sql.Date(receivingDate.getTime()) + "\'", Ticket.class).getResultList();
                break;
            case 2:
                System.out.println("Введите дату выдачи: ");
                date = scanner.next();
                Date expirationDate = new Date(date);
                ticketList = session.createQuery("SELECT a from Ticket a where lastName = \'"
                        + new java.sql.Date(expirationDate.getTime()) + "\'", Ticket.class).getResultList();
                break;
        }
        System.out.println(ticketList);
    }
}
