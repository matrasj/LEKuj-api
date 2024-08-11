package pl.matrasj.lekuj.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.matrasj.lekuj.enumtype.FileType;

@Entity
@Table(name = "data_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataFileEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    FileType type;

    @Column(name = "name")
    String name;
}
