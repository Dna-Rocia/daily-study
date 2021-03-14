package com.daily.pattern.state;

/**
 * @Description 对于闭门状态，除去自身外，电梯门关闭之后还可以再度打开，所以有open()方法；而门关了之后是可以运行的，所以有run()方法；如果关了门没有按楼层的话，此时电梯处于停止状态，所以有stop()方法。
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class ClosingState extends LiftState {

    //电梯门关了可以再开
    @Override
    public void open() {//置为敞门状态
        super.context.setLiftState(Context.OPENNING_STATE);
        super.context.getLiftState().open();
    }

    //电梯门关了就运行
    @Override
    public void run() {
        super.context.setLiftState(Context.RUNNING_STATE);
        super.context.getLiftState().run();
    }

    // * 执行电梯门关闭方法
    @Override
    public void close() {
        System.out.println("电梯门关闭");
    }

    //电梯门关了但没有按楼层
    @Override
    public void stop() {
        super.context.setLiftState(Context.STOPPING_STATE);
        super.context.getLiftState().stop();
    }
}
