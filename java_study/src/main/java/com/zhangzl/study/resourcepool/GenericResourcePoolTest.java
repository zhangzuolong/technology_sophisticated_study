package com.zhangzl.study.resourcepool;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Description  :
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 21:02
 */
public class GenericResourcePoolTest {
    GenericResourcePool<String> testPool1, testPool2;
    String s1, s2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testPool1 = new GenericResourcePool<String>();
        testPool2 = new GenericResourcePool<String>();
        s1 = new String("test1");
        s2 = new String("test2");
    }

    /**
     *
     * Test that new object created and will only allow type specified
     * Pool is empty
     * Acquire returns null
     *
     *
     */
    @Test
    public void testGenericResourcePool() {

        assertTrue(testPool1.getClass() instanceof Object);
        assertFalse(testPool2.equals(testPool1));
        assertTrue(testPool1.totalResourceCount() == 0);
    }

    /**
     *
     * isOpen should return false unless this has been called first.
     */
    @Test
    public void testOpen() {
        assertFalse(testPool1.isOpen());
        testPool1.open();
        assertTrue(testPool1.isOpen());

    }

    /**
     *
     * close pool and block until all resources have been released.
     * Create two objects, add to pool.
     * Acquire both objects, close pool and pool remains open until both
     * resources have been released.
     * @throws InterruptedException
     *
     */
    @Test
    public void testClose() throws InterruptedException {
        testPool1.open();
        assertTrue(testPool1.isOpen());
        testPool1.add(s1);
        String temp = testPool1.acquire();
        new Thread() {
            public void run() {
                testPool1.close();
                assertFalse(testPool1.isOpen());
            }
        }.start();
        assertTrue(testPool1.isOpen());
        testPool1.release(temp);
    }

    /**
     *
     * close pool without waiting for acquired resources to be released.
     * Add resources, and acquire. Pool should return as close before resources
     * have been released.
     * @throws InterruptedException
     *
     */
    @Test
    public void testCloseNow() throws InterruptedException {
        testPool1.open();
        assertTrue(testPool1.isOpen());
        assertTrue(testPool1.add(s1));
        assertTrue(testPool1.add(s2));

        String temp1 = testPool1.acquire();
        String temp2 = testPool1.acquire();
        testPool1.closeNow();
        assertFalse(testPool1.isOpen());
        testPool1.release(temp1);
        assertTrue(testPool1.acquire() == null);

        assertFalse(testPool1.isOpen());
        testPool1.release(temp2);

        assertFalse(testPool1.isOpen());
    }

    /**
     *
     * add resource to pool and add to the checked in structure.
     * return true if new object, false if already in pool.
     * @throws InterruptedException
     *
     */
    @Test
    public void testAdd() throws InterruptedException {
        testPool1.open();
        assertTrue(testPool1.avaliableResourceCount() == 0);
        assertTrue(testPool1.add(s1));
        assertFalse(testPool1.add(s1));
        assertTrue(testPool1.avaliableResourceCount() == 1);
        assertTrue(testPool1.add(s2));
        assertTrue(testPool1.avaliableResourceCount() == 2);

        String temp = testPool1.acquire();
        assertFalse(testPool1.add(temp));

    }

    /**
     *
     * remove resource from pool from checked in structure.
     * successful remove returns true, otherwise false.
     *
     */
    @Test
    public void testRemove() {
        String temp = new String("test");
        assertTrue(testPool1.add(temp));
        assertTrue(testPool1.totalResourceCount() == 1);
        assertTrue(testPool1.remove(temp));
        assertFalse(testPool1.remove("not tracked"));
        assertTrue(testPool1.totalResourceCount() == 0);

    }

    /**
     *
     * remove resource from pool from checked out structure.
     * successful remove returns true, otherwise false.
     * @throws InterruptedException
     *
     */
    @Test
    public void testRemoveNow() throws InterruptedException {
        testPool1.open();
        String temp = new String("test");
        assertTrue(testPool1.add(temp));
        assertTrue(testPool1.totalResourceCount() == 1);
        @SuppressWarnings("unused")
        String temp2 = testPool1.acquire();
        assertTrue(testPool1.removeNow(temp));
        assertTrue(testPool1.totalResourceCount() == 0);

    }

    /**
     *
     * if pool contains resources, get one. Must block until resource is available.
     * return null if pool is closed.
     * @throws InterruptedException
     *
     */
    @Test
    public void testAcquire() throws InterruptedException {
        testPool1.open();

        new Thread() {
            public void run() {
                String temp = "";
                try {
                    temp = testPool1.acquire();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                assertTrue(testPool1.avaliableResourceCount() == 0);
                testPool1.release(temp);
                testPool1.close();
                try {
                    assertTrue(testPool1.acquire() == null);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
        assertTrue(testPool1.avaliableResourceCount() == 0);
        testPool1.add(s1);
    }

    /**
     *
     * attempt to acquire object for specified time, then return null if no resource returned.
     * @throws InterruptedException
     */
    @Test
    public void testAcquireLongTimeUnit() throws InterruptedException {
        testPool1.open();
        @SuppressWarnings("unused")
        String temp = testPool1.acquire(1, TimeUnit.SECONDS);
        testPool1.add(new String("now there's a string to acquire"));
        String temp2 = testPool1.acquire(1, TimeUnit.SECONDS);
        assertTrue(temp2 != null);

    }

    /**
     * return specified object to pool, remove from checked out, add to checked in.
     * @throws InterruptedException
     *
     */
    @Test
    public void testRelease() throws InterruptedException {
        testPool1.open();
        testPool1.add(s1);
        String temp = "";
        temp = testPool1.acquire();
        assertTrue(testPool1.totalResourceCount() == 1);
        assertTrue(testPool1.avaliableResourceCount() == 0);
        testPool1.release(temp);
        assertTrue(testPool1.totalResourceCount() == 1);
        assertTrue(testPool1.avaliableResourceCount() == 1);

    }
}
