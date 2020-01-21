package com.assurity.coffee;

import java.util.PriorityQueue;

public class OrderQueue {

    private PriorityQueue<CoffeeOrder> priorityQueue = new PriorityQueue<>();

    public int size() {
        return priorityQueue.size();
    }

    public void add(CoffeeOrder coffeeOrder) {
        priorityQueue.add(coffeeOrder);
    }

    public CoffeeOrder getNextOrder() {
        return priorityQueue.poll();
    }
}
