package models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reader", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reader {
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

    public Reader(String firstName, String lastName, String phone, Ticket ticket){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.ticket = ticket;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticketid", referencedColumnName = "id")
    private Ticket ticket;

    @Override
    public String toString() {
        return "\nID: " + id + "\nЧитатель: " + firstName + " " + lastName + "\nТелефон: " + phone +
                "\nБилет: " + ticket.toString();
    }
}
