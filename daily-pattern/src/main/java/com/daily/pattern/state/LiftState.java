package com.daily.pattern.state;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public abstract class LiftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();

    public abstract void run();

    public abstract void close();

    public abstract void stop();

}
