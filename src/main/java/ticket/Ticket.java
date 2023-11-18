package ticket;

import client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import planet.Planet;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Getter
    @Id
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;
}
