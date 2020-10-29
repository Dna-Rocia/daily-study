package com.daily.pattern.state;

/**
 * @Description
 * 对于开门状态，除去自身的开启电梯门的方法之外，在打开门之后应该还具备关闭电梯门的功能，而门开着的时候是不能运行也不能停止的。
 *
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class OpenningState extends LiftState{


    @Override
    public void open() {
        System.out.println("电梯门开启");
    }

    //门开着不能运行
    @Override
    public void run() {
    //什么都不做
    }

    @Override
    public void close() {
         //状态修改
         super.context.setLiftState(Context.CLOSING_STATE);
         //动作委托为CLOSING_STATE执行
         super.context.getLiftState().close();
    }

    //门开着不能运行
    @Override
    public void stop() {
    //什么都不做
    }
}
