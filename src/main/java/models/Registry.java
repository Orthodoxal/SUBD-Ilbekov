package models;
import javax.persistence.*;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "registry", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Registry {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "returned")
    private boolean returned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketid")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "librarianid")
    private Librarian librarian;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "printed_productsid")
    private PrintedProducts printedProducts;

    public Registry(Date issueDate, boolean returned, Ticket ticket, Librarian librarian, PrintedProducts printedProducts){
        this.issueDate = issueDate;
        this.returned = returned;
        this.ticket = ticket;
        this.librarian = librarian;
        this.printedProducts = printedProducts;
    }

    @Override
    public String toString() {
        return "\n\n\n\nID: " + id + "\nДата выдачи: " + issueDate + "\nВозващено: " + (returned ? "Да" : "Нет")
                + "\nБилет читателя: " + ticket.toString()
                + "\nБиблиотекарь: " + librarian.toString()
                + "\nКнига: " + printedProducts.toString() + "\n\n\n\n";
    }
}
