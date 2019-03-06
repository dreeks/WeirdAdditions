package xyz.dreeks.weirdadditions.capabilities;

public class HasSpore implements IHasSpore {

    private int amount, maxSpore;

    @Override
    public int getMaxSpore() {
        return this.maxSpore;
    }

    @Override
    public int getAmountLeft() {
        return this.amount;
    }

    @Override
    public void setMaxSpore(int maxSpore) {
        this.maxSpore = maxSpore;
    }

    @Override
    public void setAmountLeft(int amt) {
        this.amount = amt;
    }
}
