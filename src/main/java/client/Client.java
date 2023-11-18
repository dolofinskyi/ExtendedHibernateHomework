package client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ticket.Ticket;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;
}
