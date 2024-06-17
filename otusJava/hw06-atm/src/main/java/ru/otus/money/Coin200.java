package ru.otus.money;

public class Coin200 extends Banknote {
    public Coin200() {
        super(0, Denomitations.TWO_HUNDRED);
    }

    public Coin200(int count) {
        super(count, Denomitations.TWO_HUNDRED);
    }
}
