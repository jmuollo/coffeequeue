package com.assurity.coffee;

import org.junit.Before;
import org.junit.Test;

import static com.assurity.coffee.CoffeeSize.SIX_OZ;
import static org.junit.Assert.assertEquals;

public class OrderQueueTest {

    private static final int HIGH_PRIORITY_CUSTOMER = 0;
    private static final int MEDIUM_PRIORITY_CUSTOMER = 10;
    private OrderQueue testQueue;
    private CoffeeOrder mediumPriorityOrder;
    private CoffeeOrder highPriorityOrder;

    @Before
    public void setup() {
        testQueue = new OrderQueue();
        mediumPriorityOrder = new CoffeeOrder(MEDIUM_PRIORITY_CUSTOMER,3,1, SIX_OZ, 0);
        highPriorityOrder = new CoffeeOrder(HIGH_PRIORITY_CUSTOMER,3,1, SIX_OZ, 0);
    }

    @Test
    public void addOrderTest() {
        CoffeeOrder coffeeOrder = new CoffeeOrder(HIGH_PRIORITY_CUSTOMER,3,1,SIX_OZ, 0);
        assertEquals(0, testQueue.size());
        testQueue.add(coffeeOrder);
        assertEquals(1, testQueue.size());
    }

    @Test
    public void testPriorityAddMediumThenHigh() {
        testQueue.add(mediumPriorityOrder);
        testQueue.add(highPriorityOrder);
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(HIGH_PRIORITY_CUSTOMER, nextOrder.getCustomerType());
    }

    @Test
    public void testPriorityAddHighThenMedium() {
        testQueue.add(highPriorityOrder);
        testQueue.add(mediumPriorityOrder);
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(HIGH_PRIORITY_CUSTOMER, nextOrder.getCustomerType());
    }
}
