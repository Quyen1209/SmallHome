package com.vti.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 16, unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", length = 8, nullable = false)
    private String password;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "address", length = 1023, nullable = false)
    private String address;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone;


    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role = Role.USER;

    @Column(name = "status",length = 10)
    @ColumnDefault("TRUE")
    private Boolean status = Boolean.TRUE;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Order> orders;

    public enum  Role {
        ADMIN, MANAGER, USER
    }
}
