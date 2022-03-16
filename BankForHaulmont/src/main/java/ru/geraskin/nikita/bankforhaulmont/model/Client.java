package ru.geraskin.nikita.bankforhaulmont.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;



    @Column(name = "first_name")
    @NotEmpty
    private String first_name;

    @Column(name = "second_name")
    @NotEmpty
    private String second_name;

    @Column(name = "third_name")
    @NotEmpty
    private String third_name;

    @Column(name = "telephone")
    @NotEmpty
    private String telephone;

    @Column(name = "passport")
    @NotEmpty
    private String passport;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bank> banks = new HashSet<>();

    public Set<Bank> getBanks() {
        return banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setSecondName(String secondName) {
        this.second_name = secondName;
    }

    public void setThirdName(String thirdName) {
        this.third_name = thirdName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getSecondName() {
        return this.second_name;
    }

    public String getThirdName() {
        return this.third_name;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getPassport() {
        return this.passport;
    }

    public String getEmail() {
        return this.email;
    }
}
