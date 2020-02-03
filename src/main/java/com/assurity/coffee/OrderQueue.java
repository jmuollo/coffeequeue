package com.assurity.coffee;

import java.util.PriorityQueue;

public class OrderQueue {

    private PriorityQueue<CoffeeOrder> priorityQueue = new PriorityQueue<>();

    public int size() {
        return priorityQueue.size();
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public QueueResult addOrder(CoffeeOrder coffeeOrder) {
        if (coffeeOrder.getCustomerType() < 0) {
            return new QueueResult(-1, "Invalid customer order type");
        }
        // TODO other validation checks
        priorityQueue.add(coffeeOrder);
        return new QueueResult(0, "Success");
    }

    public CoffeeOrder getNextOrder() {
        return priorityQueue.poll();
    }

    public boolean cancelOrder(CoffeeOrder coffeeOrder) {
        return priorityQueue.remove(coffeeOrder);
    }
}
