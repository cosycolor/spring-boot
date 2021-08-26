package com.example.mythymleaf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    //@JsonIgnore 하면 LAZY라더라도 검색 안함 return 을 할 필요 없으니까
    //LAZY는 필요할때만 쿼리를 날려 가져온다.
    private List<Board> boards = new ArrayList<>();
}
