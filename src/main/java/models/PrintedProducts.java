package models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "printed_products", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintedProducts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne()
    @JoinColumn(name = "topicsid", referencedColumnName = "id")
    private Topic topic;

    @Column(name = "amount")
    private int amount;

    public PrintedProducts(String title, String firstName, String lastName, int amount, Topic topic){
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.topic = topic;
    }

    @OneToMany(mappedBy = "printedProducts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registry> registries;

    @Override
    public String toString() {
        return "\nID: " + id + "\nПечатная продукция: " + title + (firstName != null && lastName != null ?
                ("\nАвтор" + firstName + " " + lastName) : "") + "\nКоличество: " + amount +
                (topic != null ? ("\nТематика: " + topic.toString()) : "");
    }
}
