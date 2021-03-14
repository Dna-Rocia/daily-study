package com.daily.pattern.state;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class Context {
    //定义出电梯的所有状态
    public final static LiftState OPENNING_STATE = new OpenningState();
    public final static LiftState CLOSING_STATE = new ClosingState();
    public final static LiftState RUNNING_STATE = new RunningState();
    public final static LiftState STOPPING_STATE = new StoppingState();

    //定义一个当前电梯状态
    private LiftState liftState;


    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        //通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}
