package models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "librarian", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Librarian {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    public Librarian(String firstName, String lastName, String phone, Topic topic){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.topic = topic;
    }

    @OneToOne()
    @JoinColumn(name = "topicsid", referencedColumnName = "id")
    private Topic topic;

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registry> registries;

    @Override
    public String toString() {
        return "\nID: " + id + "\nБиблиотекарь: " + firstName + " " + lastName + "\nТелефон: " + phone +
                "\nТематика: " + topic.toString();
    }
}