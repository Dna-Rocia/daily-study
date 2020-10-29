package com.daily.pattern.state;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
//        context.setLiftState(new RunningState());
//        context.setLiftState(new OpenningState());
//        context.setLiftState(new StoppingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}



