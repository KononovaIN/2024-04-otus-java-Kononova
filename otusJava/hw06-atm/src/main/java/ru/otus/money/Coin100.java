package ru.otus.money;

public class Coin100 extends Banknote{
    public Coin100(){
        super(0, Denomitations.HUNDRED);
    }

    public Coin100(int count){
        super(count, Denomitations.HUNDRED);
    }
}
