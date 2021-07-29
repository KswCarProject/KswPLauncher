package com.wits.ksw.launcher.view;

import android.os.Handler;
import android.os.Message;

public abstract class LoopRotarySwitchViewHandler extends Handler {
    public static final int msgid = 1000;
    private boolean loop = false;
    public long loopTime = 3000;
    private Message msg = createMsg();

    public abstract void doScroll();

    public LoopRotarySwitchViewHandler(int time) {
        this.loopTime = (long) time;
    }

    public void handleMessage(Message msg2) {
        msg2.what = 1000;
        switch (1000) {
            case 1000:
                if (this.loop) {
                    doScroll();
                    sendMsg();
                    break;
                }
                break;
        }
        super.handleMessage(msg2);
    }

    public void setLoop(boolean loop2) {
        this.loop = loop2;
        if (loop2) {
            sendMsg();
            return;
        }
        try {
            removeMessages(1000);
        } catch (Exception e) {
        }
    }

    private void sendMsg() {
        try {
            removeMessages(1000);
        } catch (Exception e) {
        }
        Message createMsg = createMsg();
        this.msg = createMsg;
        sendMessageDelayed(createMsg, this.loopTime);
    }

    public Message createMsg() {
        Message msg2 = new Message();
        msg2.what = 1000;
        return msg2;
    }

    public void setLoopTime(long loopTime2) {
        this.loopTime = loopTime2;
    }

    public long getLoopTime() {
        return this.loopTime;
    }

    public boolean isLoop() {
        return this.loop;
    }
}
