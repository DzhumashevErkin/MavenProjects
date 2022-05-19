package entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_post")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class T_Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "text", nullable = false)
    String text;

    @ManyToOne
    @JoinColumn(name = "t_user_id", nullable = false)
    T_User username;

    @ManyToMany
    @JoinTable(name = "t_likes",
            joinColumns = @JoinColumn(name = "t_post_id"),
            inverseJoinColumns = @JoinColumn(name ="t_user_id")
    )
    List<T_User> likedUsers;
}
