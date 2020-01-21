package com.assurity.coffee;

public class CoffeeOrder implements Comparable {

    private int customerType;
    private int productCode;
    private byte quantity;
    private CoffeeSize size;
    private int timeStamp;

    public CoffeeOrder(int customerType, int productCode, byte quantity, CoffeeSize size, int timeStamp) {
        this.customerType = customerType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.size = size;
        this.timeStamp = timeStamp;
    }


    public int getCustomerType() {
        return customerType;
    }

    private int getCustomerPriority() {
        if (customerType < 11) {
            return 0;
        } else if (customerType < 91) {
            return 10;
        } else {
            return 20;
        }
    }

    @Override
    public int compareTo(Object o) {
        CoffeeOrder other = (CoffeeOrder)o;
        int thisPriority = getCustomerPriority();
        int otherPriority = other.getCustomerPriority();
        // If the customer type priorities are the same then the coffee order with the earlier timestamp takes priority.
        if (thisPriority == otherPriority) {
            return this.timeStamp - other.timeStamp;
        } else {
            return thisPriority - otherPriority;
        }
    }

    public int getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public CoffeeSize getSize() {
        return size;
    }

    public int getTimeStamp() {
        return timeStamp;
    }
}
