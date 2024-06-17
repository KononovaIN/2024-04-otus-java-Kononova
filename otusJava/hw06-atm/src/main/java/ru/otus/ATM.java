package ru.otus;

import lombok.ToString;
import ru.otus.exceptions.BusinessException;
import ru.otus.exceptions.InvalidParameterException;
import ru.otus.money.*;
import ru.otus.utils.Utils;

import java.util.*;

import static ru.otus.money.Denomitations.*;

public class ATM {
    private TreeSet<Banknote> banknotes = new TreeSet<>(Comparator.comparingInt(Banknote::getDenomination));

    public ATM() {
        banknotes.add(new Coin100());
        banknotes.add(new Coin200());
        banknotes.add(new Coin500());
        banknotes.add(new Coin1000());
        banknotes.add(new Coin2000());
        banknotes.add(new Coin5000());

        banknotes = (TreeSet<Banknote>) banknotes.descendingSet();
    }

    public void loadBanknotes(int count, int denomination) {
        if (HUNDRED.getDenomination() == denomination) {
            addBanknote(new Coin100(), count);
        } else if (TWO_HUNDRED.getDenomination() == denomination) {
            addBanknote(new Coin200(), count);
        } else if (FIVE_HUNDRED.getDenomination() == denomination) {
            addBanknote(new Coin500(), count);
        } else if (THOUSAND.getDenomination() == denomination) {
            addBanknote(new Coin1000(), count);
        } else if (TWO_THOUSAND.getDenomination() == denomination) {
            addBanknote(new Coin2000(), count);
        } else if (FIVE_THOUSAND.getDenomination() == denomination) {
            addBanknote(new Coin5000(), count);
        }
    }

    public Map<Denomitations, Integer> getAmount(int amount) {
        //Если не делится полностью на номинал наименьшей банкноты, то значит выдать сумму не можем
        Utils.checkAmount(amount, Denomitations.getMinDenomination().getDenomination());

        Map<Denomitations, Integer> result = new HashMap<>();

        for (Banknote banknote : banknotes) {
            if (amount > 0) {
                Map.Entry<Denomitations, Integer> banknotesByAmount = banknote.getBanknotesByAmount(amount);
                if(banknotesByAmount.getValue() != 0) {
                    result.put(banknotesByAmount.getKey(), banknotesByAmount.getValue());
                    amount -= banknotesByAmount.getKey().getDenomination() * banknotesByAmount.getValue();
                }
            } else {
                break;
            }
        }

        if(amount != 0){
            throw new BusinessException("There are not enough banknotes to issue the requested amount");
        }

        return result;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder("ATM has: \n");
        for (Banknote next : banknotes) {
           sb.append("\tDenomination: ").append(next.getDenomination())
                   .append(" count: ").append(next.getCount()).append("\n");
        }

        return sb.toString();
    }

    private void addBanknote(Banknote banknote, int count) {
        Banknote banknoteOpt = findBanknote(banknote);
        banknoteOpt.addBanknotes(count);
    }

    private Banknote findBanknote(Banknote banknote) {
        if (banknotes.contains(banknote)) {
            for (Banknote next : banknotes) {
                if (banknote.equals(next)) {
                    return next;
                }
            }
        }

        throw new InvalidParameterException("Unknown denomination");
    }
}
