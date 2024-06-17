package ru.otus.money;

public class Coin2000 extends Banknote {
    public Coin2000() {
        super(0, Denomitations.TWO_THOUSAND);
    }

    public Coin2000(int count) {
        super(count, Denomitations.TWO_THOUSAND);
    }
}
