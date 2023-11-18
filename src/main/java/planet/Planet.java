package planet;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "planet")
public class Planet {
    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private String id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;
}
