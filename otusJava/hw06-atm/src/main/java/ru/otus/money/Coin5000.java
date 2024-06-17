package ru.otus.money;

public class Coin5000  extends Banknote{
    public Coin5000(){
        super(0, Denomitations.FIVE_THOUSAND);
    }

    public Coin5000(int count){
        super(count, Denomitations.FIVE_THOUSAND);
    }
}
