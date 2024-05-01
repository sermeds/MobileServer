package MobileServer.models;

import MobileServer.models.enums.Rarity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "tips")
@EqualsAndHashCode(of = {"id"})
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @Column(name = "rarity")
    private Rarity rarity;

}
