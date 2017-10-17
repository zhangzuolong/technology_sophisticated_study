package com.zhangzl.study.resourcepool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description  : 自定义阻塞队列
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 20:29
 * LinkedBlockingQueue是一个链表实现的阻塞队列，在链表一头加入元素，如果队列满，就会阻塞，另一头取出元素，如果队列为空，就会阻塞。
   LinkedBlockingQueue内部使用ReentrantLock实现插入锁(putLock)和取出锁(takeLock)。putLock上的条件变量是notFull，即可以用notFull唤醒阻塞在putLock上的线程。
   takeLock上的条件变量是notEmtpy，即可用notEmpty唤醒阻塞在takeLock上的线程。
   知道了LinkedBlockingQueue，再来理解ArrayBlockingQueue就比较好理解了。类似LinkList和ArrayList的区别。
   如果知道队列的大小，那么使用ArrayBlockIngQueue就比较合适了，因为它使用循环数组实现，
   但是如果不知道队列未来的大小，那么使用ArrayBlockingQueue就必然会导致数组的来回复制，降低效率
 */
public class MyBlockingQueue<R> extends LinkedBlockingQueue<R> {
    private static final long serialVersionUID = 1L;
    /**
     *
     1. public：public表明该数据成员、成员函数是对所有用户开放的，所有用户都可以直接进行调用
     2、private：private表示私有，私有的意思就是除了class自己之外，任何人都不可以直接使用，私有财产神圣不可侵犯嘛，即便是子女，朋友，都不可以使用。
     3、protected：protected对于子女、朋友来说，就是public的，可以自由使用，没有任何限制，而对于其他的外部class，protected就变成private。
     作用域       当前类    同一package   子孙类     其他package
     public        √         √             √           √
     protected     √          √             √           ×
     friendly      √          √             ×           ×
     private       √          ×             ×           ×
     */
    protected synchronized boolean safeAdd(R resource){
        return this.add(resource);
    }

    protected synchronized  R safePoll() throws InterruptedException{
        return this.poll();
    }

    protected synchronized R safePoll(long timeout,java.util.concurrent.TimeUnit unit) throws InterruptedException{
        return this.safePoll(timeout,unit);
    }

    protected synchronized boolean safeRemove(R resource){
        return this.remove(resource);
    }

}
