package com.wits.pms.statuscontrol;

import android.os.RemoteException;
import android.text.TextUtils;
import com.wits.pms.statuscontrol.BtPhoneStatus;
import java.util.List;

public class CenterManager {
    public void mediaPrevious() {
        WitsCommand.sendCommand(1, 103);
    }

    public void mediaNext() {
        WitsCommand.sendCommand(1, 104);
    }

    public void radioFreq(int freq) {
        WitsCommand.sendCommand(8, 101, freq + "");
    }

    public List<BtPhoneStatus.PhoneBookBean> getContacts() {
        try {
            String json = PowerManagerApp.getStatusString("phonebook");
            if (!TextUtils.isEmpty(json)) {
                return BtPhoneStatus.getContactsByJson(json);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public void fmOpen() {
    }

    public void fmClose() {
    }

    public void fmPrevious() {
        WitsCommand.sendCommand(1, 103);
    }

    public void fmNext() {
        WitsCommand.sendCommand(1, 104);
    }

    public void btPhoneCall(String number) {
        WitsCommand.sendCommand(3, 108, "{number:" + number + "}");
    }

    public void handUpPhone() {
    }

    public void acceptPhone() {
    }

    public void btMusicPrev() {
    }

    public void btMusicNext() {
    }

    public void btMusicStop() {
    }

    public void btMusicPlayPause() {
    }
}
