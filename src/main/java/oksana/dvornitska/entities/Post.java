package oksana.dvornitska.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "posts")
public class Post {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String text;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    Date plannedDate;
    Boolean privateCheck;
    @ManyToOne
    @JoinColumn(name = "location_id") // Связь будет установлена через поле location_id в таблице постов
    Location location;
    @ManyToOne
    @JoinColumn(name = "user_id") // Связь будет установлена через поле user_id в таблице постов
    User user;
}
