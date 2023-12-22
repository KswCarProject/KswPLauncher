package com.wits.ksw.launcher.view;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes16.dex */
public abstract class LoopRotarySwitchViewHandler extends Handler {
    public static final int msgid = 1000;
    public long loopTime;
    private boolean loop = false;
    private Message msg = createMsg();

    public abstract void doScroll();

    public LoopRotarySwitchViewHandler(int time) {
        this.loopTime = 3000L;
        this.loopTime = time;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        msg.what = 1000;
        switch (1000) {
            case 1000:
                if (this.loop) {
                    doScroll();
                    sendMsg();
                    break;
                }
                break;
        }
        super.handleMessage(msg);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        if (loop) {
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
        Message msg = new Message();
        msg.what = 1000;
        return msg;
    }

    public void setLoopTime(long loopTime) {
        this.loopTime = loopTime;
    }

    public long getLoopTime() {
        return this.loopTime;
    }

    public boolean isLoop() {
        return this.loop;
    }
}
