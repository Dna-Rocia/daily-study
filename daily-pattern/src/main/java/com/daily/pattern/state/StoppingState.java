package com.daily.pattern.state;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class StoppingState extends LiftState {

    //停下了要开门
    @Override
    public void open() {
        super.context.setLiftState(Context.OPENNING_STATE);
        super.context.getLiftState().open();
    }

    //停止后可以再运行
    @Override
    public void run() {
        super.context.setLiftState(Context.RUNNING_STATE);
        super.context.getLiftState().run();
    }

    //门本来就是关着的
    @Override
    public void close() {
        //什么都不做
    }

    //执行停止方法
    @Override
    public void stop() {
        System.out.println("电梯停止了");
    }
}
