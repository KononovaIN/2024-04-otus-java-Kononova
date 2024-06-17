package ru.otus.money;

public class Coin500  extends Banknote{
    public Coin500(){
        super(0, Denomitations.FIVE_HUNDRED);
    }

    public Coin500(int count){
        super(count, Denomitations.FIVE_HUNDRED);
    }
}
