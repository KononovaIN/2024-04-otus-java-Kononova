package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phone")
public class Phone {
    @Id
    private final Long phoneId;

    private final Long clientId;

    private final String number;

    @PersistenceCreator
    public Phone(Long phoneId, Long clientId, String number) {
        this.phoneId = phoneId;
        this.clientId = clientId;
        this.number = number;
    }

    public Phone(String number) {
        this(null, null, number);
    }
}
