package MobileServer.models;

import MobileServer.models.enums.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "achievements")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = {"id"})
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @ToString.Include
    private String title;

    @Column(name = "description")
    @ToString.Include
    private String description;

    @Column(name = "category")
    @ToString.Include
    private Category category;
}
