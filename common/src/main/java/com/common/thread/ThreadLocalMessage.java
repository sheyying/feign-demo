package com.common.thread;

import com.common.entity.ResponseMsg;

/**
 * Created by sheying on 2018/06/20.
 */
public class ThreadLocalMessage {
    private  ThreadLocal<ResponseMsg> messages;

    private ThreadLocalMessage(){
        messages = new ThreadLocal<ResponseMsg>();
    }

    public ResponseMsg getMessage() {
        if (null == messages.get()){
            return new ResponseMsg();
        }
        return messages.get();
    }

    public void setMessage(ResponseMsg message) {
        messages.set(message);
    }

    public void removeMessage(){
        messages.remove();
    }

    private static ThreadLocalMessage threadLocalMessage = new ThreadLocalMessage();

    public static ThreadLocalMessage getInstance(){
        return threadLocalMessage;
    }
}
