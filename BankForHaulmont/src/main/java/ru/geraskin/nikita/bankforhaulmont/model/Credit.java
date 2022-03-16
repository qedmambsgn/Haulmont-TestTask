package ru.geraskin.nikita.bankforhaulmont.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "percent")
    private float percent;

    @Column(name = "limit")
    private float limit;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bank> banks = new HashSet<>();

    public Set<Bank> getBanks() {
        return banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }

    public UUID getId() {
        return this.id;
    }

    public float getPercent() {
        return this.percent;
    }

    public float getLimit() {
        return this.limit;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }
}
