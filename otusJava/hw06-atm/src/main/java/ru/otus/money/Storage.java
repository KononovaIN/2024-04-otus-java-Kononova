package ru.otus.money;

import lombok.Getter;

import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;

@Getter
public class Storage {
    private final TreeSet<Banknote> banknotes = new TreeSet<>(Comparator.comparingInt(Banknote::getDenomination).reversed());

    public void addNewBanknotes(Denominations denomination, int count) {
        banknotes.add(new Banknote(count, denomination));
    }

    public void loadBanknotes(Denominations denomination, int count) {
        Optional<Banknote> banknoteOpt = findBanknote(denomination);
        banknoteOpt.ifPresent(banknote -> banknote.addBanknotes(count));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Storage has: \n");
        for (Banknote next : banknotes) {
            sb.append("\tDenomination: ").append(next.getDenomination())
                    .append(" count: ").append(next.getCount()).append("\n");
        }

        return sb.toString();
    }

    private Optional<Banknote> findBanknote(Denominations denomination) {
        for (Banknote next : banknotes) {
            if (next.getDenomination() == denomination.getDenomination()) {
                return Optional.of(next);
            }
        }

        return Optional.empty();
    }
}
