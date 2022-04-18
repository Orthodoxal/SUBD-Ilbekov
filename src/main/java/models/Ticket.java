package models;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "receiving_date")
    private Date receivingDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    public Ticket(Date receivingDate, Date expirationDate){
        this.receivingDate = receivingDate;
        this.expirationDate = expirationDate;
    }

    @OneToOne(mappedBy = "ticket")
    private Reader reader;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registry> registries;

    @Override
    public String toString(){
        return "\nID: " + id + "\nДата выдачи: " + receivingDate + "\nДата истечения срока действия: "
                + expirationDate + "\n";
    }
}
