package com.zhangzl.study.resourcepool;

import java.util.concurrent.TimeUnit;

/**
 * Description  : 资源池
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 20:39
 */
public class GenericResourcePool<R> {

    /**
     * 状态：是否打开
     */
    private volatile  boolean open;
    /**
     * 资源队列：可用，不可用队列
     */
    private volatile MyBlockingQueue<R> avaliableResources,unavailableResources;

    public GenericResourcePool(){
        this.open= false;
        this.avaliableResources = new MyBlockingQueue<R>();
        this.unavailableResources = new MyBlockingQueue<R>();
    }

    /**
     * 打开资源池
     */
    public synchronized void open(){
        this.open=true;
    }

    /**
     * 是否打开
     * @return
     */
    public synchronized  boolean isOpen(){
        return this.open;
    }

    /**
     * 关闭资源池
     */
    public synchronized void close(){
        while (this.unavailableResources.size()!=0){
            System.out.println("还有正在使用的资源，等待释放");
        }
        this.open=false;
        System.out.println("资源池关闭成功");
    }

    /**
     * 立刻关闭资源池
     */
    public synchronized void closeNow(){
        this.open=false;
    }

    /**
     * 添加资源
     * @param resource
     * @return
     */
    public synchronized boolean add(R resource){
        if(!this.avaliableResources.contains(resource) && !this.unavailableResources.contains(resource)){
            return this.avaliableResources.safeAdd(resource);
        }else{
            System.out.println("已存在该资源，添加失败");
            return false;
        }
    }

    /**
     * 删除资源
     * @param resource
     * @return
     */
    public synchronized boolean remove(R resource){
        if(this.avaliableResources.contains(resource) || this.unavailableResources.contains(resource)){
            while (this.avaliableResources.safeRemove(resource)){

            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * 立刻删除
     * @param resource
     * @return
     */
    public boolean removeNow(R resource){
        if(this.avaliableResources.contains(resource)){
            return this.avaliableResources.safeRemove(resource);
        }else if(this.unavailableResources.contains(resource)){
            return this.unavailableResources.safeRemove(resource);
        }else{
            return false;
        }

    }

    /**
     * 获取一个资源
     * @return
     * @throws InterruptedException
     */
    public  R acquire() throws InterruptedException{
        if(this.open){
            R temp=null;
            while (temp == null){
                temp = this.avaliableResources.safePoll();
            }
            this.unavailableResources.safeAdd(temp);
            return temp;
        }else{
            return null;
        }
    }

    /**
     * 获取一个资源,设置超时时间
     * @return
     * @throws InterruptedException
     */
    public  R acquire(long timeout, TimeUnit unit) throws InterruptedException{
        if(this.open){
            R temp=this.avaliableResources.safePoll(timeout, unit);
            if(null!=temp){
                this.unavailableResources.safeAdd(temp);
            }
            return temp;
        }else{
            return null;
        }
    }

    /**
     * 释放一个资源
     * @param resource
     */
    public synchronized void release(R resource){
        if(this.unavailableResources.safeRemove(resource)){
            this.avaliableResources.safeAdd(resource);
        }
    }

    /**
     * 可用资源个数
     * @return
     */
    public synchronized int avaliableResourceCount(){
        return this.avaliableResources.size();
    }

    /**
     * 可用资源总数
     * @return
     */
    public synchronized int totalResourceCount(){
        return this.avaliableResources.size()+this.unavailableResources.size();
    }

}
