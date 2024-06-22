package ru.otus;

import lombok.ToString;
import ru.otus.exceptions.BusinessException;
import ru.otus.money.Banknote;
import ru.otus.money.Denominations;
import ru.otus.money.Storage;
import ru.otus.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class ATMImpl implements ATM {
    private final Storage storage = new Storage();

    public ATMImpl(List<Denominations> denominations) {
        denominations.forEach(d -> storage.addNewBanknotes(d, 0));
    }

    public ATMImpl(Map<Denominations, Integer> denominations) {
        denominations.forEach(storage::addNewBanknotes);
    }

    @Override
    public void loadBanknotes(Denominations denomination, int count) {
        storage.loadBanknotes(denomination, count);
    }

    @Override
    public Map<Denominations, Integer> getAmount(int amount) {
        //Если не делится полностью на номинал наименьшей банкноты, то значит выдать сумму не можем
        Utils.checkAmount(amount, Denominations.getMinDenomination().getDenomination());

        Map<Denominations, Integer> result = new HashMap<>();

        for (Banknote banknote : storage.getBanknotes()) {
            if (amount > 0) {
                Map.Entry<Denominations, Integer> banknotesByAmount = banknote.issueBanknotesByAmount(amount);
                if (banknotesByAmount.getValue() != 0) {
                    result.put(banknotesByAmount.getKey(), banknotesByAmount.getValue());
                    amount -= banknotesByAmount.getKey().getDenomination() * banknotesByAmount.getValue();
                }
            } else {
                break;
            }
        }

        if (amount != 0) {
            result.forEach(storage::loadBanknotes);

            throw new BusinessException("There are not enough banknotes to issue the requested amount");
        }

        return result;
    }
}
