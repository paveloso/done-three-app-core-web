package io.done3app.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public enum ROLE {
        USER,
        ADMIN,
        SUPER_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "systemName")
    @Enumerated(EnumType.STRING)
    private ROLE systemName;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, ROLE systemName) {
        this.name = name;
        this. systemName = systemName;
    }

    public Role(ROLE systemName) {
        new Role(systemName.toString(), systemName);
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ROLE getSystemName() {
        return systemName;
    }

    public void setSystemName(ROLE systemName) {
        this.systemName = systemName;
    }
}
