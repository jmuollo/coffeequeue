package com.assurity.coffee;

public class CoffeeOrder implements Comparable {

    private int customerType;
    private int productCode;
    private int quantity;
    private CoffeeSize size;
    private int timeStamp;

    public CoffeeOrder(int customerType, int productCode, int quantity, CoffeeSize size, int timeStamp) {
        this.customerType = customerType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.size = size;
        this.timeStamp = timeStamp;
    }


    public int getCustomerType() {
        return customerType;
    }

    @Override
    public int compareTo(Object o) {
        return this.customerType - ((CoffeeOrder)o).customerType;
    }
}
