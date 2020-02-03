package com.assurity.coffee;

import org.junit.Before;
import org.junit.Test;

import static com.assurity.coffee.CoffeeSize.SIXTEEN_OZ;
import static com.assurity.coffee.CoffeeSize.SIX_OZ;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class OrderQueueTest {

    private static final int HIGH_PRIORITY_CUSTOMER1 = 0;
    private static final int HIGH_PRIORITY_CUSTOMER_EARLIEST = 10;
    private static final int MEDIUM_PRIORITY_CUSTOMER = 50;
    private OrderQueue testQueue;
    private CoffeeOrder mediumPriorityOrder;
    private CoffeeOrder highPriorityOrder;
    private CoffeeOrder highPriorityOrderEarliest;

    @Before
    public void setup() {
        testQueue = new OrderQueue();
        mediumPriorityOrder = new CoffeeOrder(MEDIUM_PRIORITY_CUSTOMER,3, (byte) 1, SIX_OZ, 1000);
        highPriorityOrder = new CoffeeOrder(HIGH_PRIORITY_CUSTOMER1,3, (byte) 1, SIX_OZ, 1000);
        highPriorityOrderEarliest = new CoffeeOrder(HIGH_PRIORITY_CUSTOMER_EARLIEST,3, (byte) 1, SIX_OZ, 0);
    }

    @Test
    public void addOrder() {
        assertTrue(testQueue.isEmpty());
        QueueResult queueResult = testQueue.addOrder(mediumPriorityOrder);
        assertEquals(0, queueResult.code);
        assertEquals(1, testQueue.size());
    }

    @Test
    public void addInvalidOrder() {
        CoffeeOrder invalidOrder = new CoffeeOrder(-1, 0, (byte) 1, SIXTEEN_OZ, 0);
        QueueResult queueResult = testQueue.addOrder(invalidOrder);
        assertEquals(-1, queueResult.code);
        assertEquals("Invalid customer order type", queueResult.message);
    }

    @Test
    public void addMediumThenHighPriority() {
        testQueue.addOrder(mediumPriorityOrder);
        testQueue.addOrder(highPriorityOrder);
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(HIGH_PRIORITY_CUSTOMER1, nextOrder.getCustomerType());
    }

    @Test
    public void addHighThenMediumPriority() {
        testQueue.addOrder(highPriorityOrder);
        testQueue.addOrder(mediumPriorityOrder);
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(HIGH_PRIORITY_CUSTOMER1, nextOrder.getCustomerType());
    }

    @Test
    public void addTwoHighPriority() {
        testQueue.addOrder(highPriorityOrder);
        testQueue.addOrder(highPriorityOrderEarliest);
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(HIGH_PRIORITY_CUSTOMER_EARLIEST, nextOrder.getCustomerType());
    }

    @Test
    public void cancelOrder() {
        testQueue.addOrder(highPriorityOrder);
        testQueue.addOrder(mediumPriorityOrder);
        assertTrue(testQueue.cancelOrder(highPriorityOrder));
        assertEquals(1, testQueue.size());
        CoffeeOrder nextOrder = testQueue.getNextOrder();
        assertEquals(MEDIUM_PRIORITY_CUSTOMER, nextOrder.getCustomerType());
    }


}
