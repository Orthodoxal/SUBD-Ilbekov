package logics;

import models.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class TopicLogic {
    Scanner scanner = new Scanner(System.in);
    Session session = null;

    public void start(SessionFactory sessionFactory){
        System.out.println("Введите 1 для добавления темы: ");
        System.out.println("Введите 2 для вывода тематик: ");
        System.out.println("Введите 3 для изменения данных темы: ");
        System.out.println("Введите 4 для удаления темы: ");
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
        System.out.println("Введите название тематики: ");
        String name = scanner.next();

        Topic topic = new Topic(name);
        session.save(topic);
    }

    private void read(){
        List<Topic> topicList = session.createQuery("SELECT a from Topic a", Topic.class).getResultList();
        System.out.println(topicList);
    }

    private void update(){
        System.out.println("Введите id тематики: ");
        int id = scanner.nextInt();

        System.out.println("Введите название тематики: ");
        String name = scanner.next();

        Topic topic = session.get(Topic.class, id);
        topic.setName(name);
        session.save(topic);
    }

    private void delete(){
        System.out.println("Введите id тематики: ");
        int id = scanner.nextInt();

        Topic topic = session.get(Topic.class, id);
        session.delete(topic);
    }

    private  void  filterRead(){
        System.out.println("Фильтрация по имени: ");
        System.out.println("Введите имя: ");
        String name = scanner.next();
        System.out.println(session.createQuery("SELECT a from Topic a where name = \'"
                + name + "\'", Topic.class).getResultList());
    }
}
