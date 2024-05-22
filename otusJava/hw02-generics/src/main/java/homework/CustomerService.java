package homework;

import java.util.*;

public class CustomerService {
    private final NavigableMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return clone(customers.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return clone(customers.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private Map.Entry<Customer, String> clone(Map.Entry<Customer, String> entry) {
        if (entry != null) {
            if (entry.getKey() == null) {
                return new AbstractMap.SimpleImmutableEntry<>(null, entry.getValue());
            } else {
                return new AbstractMap.SimpleImmutableEntry<>(entry.getKey().clone(), entry.getValue());
            }
        } else {
            return null;
        }
    }
}
