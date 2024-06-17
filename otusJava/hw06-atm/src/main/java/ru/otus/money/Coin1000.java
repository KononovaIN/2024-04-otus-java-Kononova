package ru.otus.money;

public class Coin1000 extends Banknote{
    public Coin1000(){
        super(0, Denomitations.THOUSAND);
    }

    public Coin1000(int count){
        super(count, Denomitations.THOUSAND);
    }
}
