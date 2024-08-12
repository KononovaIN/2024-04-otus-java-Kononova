package ru.otus.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Table(name = "address")
public class Address {
    @Id
    private final Long id;

    private final String street;

    public Address(String street) {
        this(null, street);
    }

    @PersistenceCreator
    public Address(Long id, String street) {
        this.id = id;
        this.street = street;
    }
}
