package ru.otus.service;

import ru.otus.model.Address;

import java.util.Optional;

public interface DbServiceAddress {
    Address saveAddress(Address address);
    Optional<Address> getAddress(Long id);
}
