package org.sqli.authentification.entitie;


import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String login;

    private String password;

    private boolean enabled;

    private int loginattempts;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UserGroup group;
}