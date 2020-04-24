package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap<>();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList<>();
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap<>();

    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter _filter, BroadcastReceiver _receiver) {
            this.filter = _filter;
            this.receiver = _receiver;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(128);
            builder.append("Receiver{");
            builder.append(this.receiver);
            builder.append(" filter=");
            builder.append(this.filter);
            if (this.dead) {
                builder.append(" DEAD");
            }
            builder.append("}");
            return builder.toString();
        }
    }

    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent _intent, ArrayList<ReceiverRecord> _receivers) {
            this.intent = _intent;
            this.receivers = _receivers;
        }
    }

    @NonNull
    public static LocalBroadcastManager getInstance(@NonNull Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message msg) {
                if (msg.what != 1) {
                    super.handleMessage(msg);
                } else {
                    LocalBroadcastManager.this.executePendingBroadcasts();
                }
            }
        };
    }

    public void registerReceiver(@NonNull BroadcastReceiver receiver, @NonNull IntentFilter filter) {
        synchronized (this.mReceivers) {
            ReceiverRecord entry = new ReceiverRecord(filter, receiver);
            ArrayList<ReceiverRecord> filters = this.mReceivers.get(receiver);
            if (filters == null) {
                filters = new ArrayList<>(1);
                this.mReceivers.put(receiver, filters);
            }
            filters.add(entry);
            for (int i = 0; i < filter.countActions(); i++) {
                String action = filter.getAction(i);
                ArrayList<ReceiverRecord> entries = this.mActions.get(action);
                if (entries == null) {
                    entries = new ArrayList<>(1);
                    this.mActions.put(action, entries);
                }
                entries.add(entry);
            }
        }
    }

    public void unregisterReceiver(@NonNull BroadcastReceiver receiver) {
        synchronized (this.mReceivers) {
            ArrayList<ReceiverRecord> filters = this.mReceivers.remove(receiver);
            if (filters != null) {
                for (int i = filters.size() - 1; i >= 0; i--) {
                    ReceiverRecord filter = filters.get(i);
                    filter.dead = true;
                    for (int j = 0; j < filter.filter.countActions(); j++) {
                        String action = filter.filter.getAction(j);
                        ArrayList<ReceiverRecord> receivers = this.mActions.get(action);
                        if (receivers != null) {
                            for (int k = receivers.size() - 1; k >= 0; k--) {
                                ReceiverRecord rec = receivers.get(k);
                                if (rec.receiver == receiver) {
                                    rec.dead = true;
                                    receivers.remove(k);
                                }
                            }
                            if (receivers.size() <= 0) {
                                this.mActions.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x015f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0162, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendBroadcast(@android.support.annotation.NonNull android.content.Intent r19) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$ReceiverRecord>> r3 = r1.mReceivers
            monitor-enter(r3)
            java.lang.String r5 = r19.getAction()     // Catch:{ all -> 0x0164 }
            android.content.Context r0 = r1.mAppContext     // Catch:{ all -> 0x0164 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x0164 }
            java.lang.String r0 = r2.resolveTypeIfNeeded(r0)     // Catch:{ all -> 0x0164 }
            android.net.Uri r8 = r19.getData()     // Catch:{ all -> 0x0164 }
            java.lang.String r4 = r19.getScheme()     // Catch:{ all -> 0x0164 }
            r11 = r4
            java.util.Set r9 = r19.getCategories()     // Catch:{ all -> 0x0164 }
            int r4 = r19.getFlags()     // Catch:{ all -> 0x0164 }
            r4 = r4 & 8
            if (r4 == 0) goto L_0x002d
            r4 = 1
            goto L_0x002e
        L_0x002d:
            r4 = 0
        L_0x002e:
            r14 = r4
            if (r14 == 0) goto L_0x0057
            java.lang.String r4 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r6.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = "Resolving type "
            r6.append(r7)     // Catch:{ all -> 0x0164 }
            r6.append(r0)     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = " scheme "
            r6.append(r7)     // Catch:{ all -> 0x0164 }
            r6.append(r11)     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = " of intent "
            r6.append(r7)     // Catch:{ all -> 0x0164 }
            r6.append(r2)     // Catch:{ all -> 0x0164 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0164 }
            android.util.Log.v(r4, r6)     // Catch:{ all -> 0x0164 }
        L_0x0057:
            java.util.HashMap<java.lang.String, java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$ReceiverRecord>> r4 = r1.mActions     // Catch:{ all -> 0x0164 }
            java.lang.String r6 = r19.getAction()     // Catch:{ all -> 0x0164 }
            java.lang.Object r4 = r4.get(r6)     // Catch:{ all -> 0x0164 }
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ all -> 0x0164 }
            r15 = r4
            if (r15 == 0) goto L_0x0161
            if (r14 == 0) goto L_0x007e
            java.lang.String r4 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r6.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = "Action list: "
            r6.append(r7)     // Catch:{ all -> 0x0164 }
            r6.append(r15)     // Catch:{ all -> 0x0164 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0164 }
            android.util.Log.v(r4, r6)     // Catch:{ all -> 0x0164 }
        L_0x007e:
            r4 = 0
            r10 = r4
            r4 = 0
        L_0x0081:
            r7 = r4
            int r4 = r15.size()     // Catch:{ all -> 0x0164 }
            if (r7 >= r4) goto L_0x012e
            java.lang.Object r4 = r15.get(r7)     // Catch:{ all -> 0x0164 }
            android.support.v4.content.LocalBroadcastManager$ReceiverRecord r4 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r4     // Catch:{ all -> 0x0164 }
            r6 = r4
            if (r14 == 0) goto L_0x00a9
            java.lang.String r4 = "LocalBroadcastManager"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r12.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r13 = "Matching against filter "
            r12.append(r13)     // Catch:{ all -> 0x0164 }
            android.content.IntentFilter r13 = r6.filter     // Catch:{ all -> 0x0164 }
            r12.append(r13)     // Catch:{ all -> 0x0164 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0164 }
            android.util.Log.v(r4, r12)     // Catch:{ all -> 0x0164 }
        L_0x00a9:
            boolean r4 = r6.broadcasting     // Catch:{ all -> 0x0164 }
            if (r4 == 0) goto L_0x00bd
            if (r14 == 0) goto L_0x00b6
            java.lang.String r4 = "LocalBroadcastManager"
            java.lang.String r12 = "  Filter's target already added"
            android.util.Log.v(r4, r12)     // Catch:{ all -> 0x0164 }
        L_0x00b6:
            r17 = r0
            r16 = r7
            r0 = r10
            goto L_0x0127
        L_0x00bd:
            android.content.IntentFilter r4 = r6.filter     // Catch:{ all -> 0x0164 }
            java.lang.String r12 = "LocalBroadcastManager"
            r13 = r6
            r6 = r0
            r16 = r7
            r7 = r11
            r17 = r0
            r0 = r10
            r10 = r12
            int r4 = r4.match(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0164 }
            if (r4 < 0) goto L_0x00fd
            if (r14 == 0) goto L_0x00ec
            java.lang.String r6 = "LocalBroadcastManager"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r7.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r10 = "  Filter matched!  match=0x"
            r7.append(r10)     // Catch:{ all -> 0x0164 }
            java.lang.String r10 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0164 }
            r7.append(r10)     // Catch:{ all -> 0x0164 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0164 }
            android.util.Log.v(r6, r7)     // Catch:{ all -> 0x0164 }
        L_0x00ec:
            if (r0 != 0) goto L_0x00f5
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0164 }
            r6.<init>()     // Catch:{ all -> 0x0164 }
            r10 = r6
            r0 = r10
        L_0x00f5:
            r0.add(r13)     // Catch:{ all -> 0x0164 }
            r6 = 1
            r13.broadcasting = r6     // Catch:{ all -> 0x0164 }
            r10 = r0
            goto L_0x0128
        L_0x00fd:
            if (r14 == 0) goto L_0x0127
            switch(r4) {
                case -4: goto L_0x010e;
                case -3: goto L_0x010b;
                case -2: goto L_0x0108;
                case -1: goto L_0x0105;
                default: goto L_0x0102;
            }     // Catch:{ all -> 0x0164 }
        L_0x0102:
            java.lang.String r6 = "unknown reason"
            goto L_0x0110
        L_0x0105:
            java.lang.String r6 = "type"
            goto L_0x0110
        L_0x0108:
            java.lang.String r6 = "data"
            goto L_0x0110
        L_0x010b:
            java.lang.String r6 = "action"
            goto L_0x0110
        L_0x010e:
            java.lang.String r6 = "category"
        L_0x0110:
            java.lang.String r7 = "LocalBroadcastManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r10.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r12 = "  Filter did not match: "
            r10.append(r12)     // Catch:{ all -> 0x0164 }
            r10.append(r6)     // Catch:{ all -> 0x0164 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0164 }
            android.util.Log.v(r7, r10)     // Catch:{ all -> 0x0164 }
        L_0x0127:
            r10 = r0
        L_0x0128:
            int r4 = r16 + 1
            r0 = r17
            goto L_0x0081
        L_0x012e:
            r17 = r0
            r0 = r10
            if (r0 == 0) goto L_0x0161
            r4 = 0
        L_0x0134:
            int r6 = r0.size()     // Catch:{ all -> 0x0164 }
            if (r4 >= r6) goto L_0x0146
            java.lang.Object r6 = r0.get(r4)     // Catch:{ all -> 0x0164 }
            android.support.v4.content.LocalBroadcastManager$ReceiverRecord r6 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r6     // Catch:{ all -> 0x0164 }
            r7 = 0
            r6.broadcasting = r7     // Catch:{ all -> 0x0164 }
            int r4 = r4 + 1
            goto L_0x0134
        L_0x0146:
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$BroadcastRecord> r4 = r1.mPendingBroadcasts     // Catch:{ all -> 0x0164 }
            android.support.v4.content.LocalBroadcastManager$BroadcastRecord r6 = new android.support.v4.content.LocalBroadcastManager$BroadcastRecord     // Catch:{ all -> 0x0164 }
            r6.<init>(r2, r0)     // Catch:{ all -> 0x0164 }
            r4.add(r6)     // Catch:{ all -> 0x0164 }
            android.os.Handler r4 = r1.mHandler     // Catch:{ all -> 0x0164 }
            r6 = 1
            boolean r4 = r4.hasMessages(r6)     // Catch:{ all -> 0x0164 }
            if (r4 != 0) goto L_0x015e
            android.os.Handler r4 = r1.mHandler     // Catch:{ all -> 0x0164 }
            r4.sendEmptyMessage(r6)     // Catch:{ all -> 0x0164 }
        L_0x015e:
            monitor-exit(r3)     // Catch:{ all -> 0x0164 }
            r3 = 1
            return r3
        L_0x0161:
            monitor-exit(r3)     // Catch:{ all -> 0x0164 }
            r0 = 0
            return r0
        L_0x0164:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0164 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.sendBroadcast(android.content.Intent):boolean");
    }

    public void sendBroadcastSync(@NonNull Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r2 >= r0.length) goto L_0x0001;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r3 = r0[r2];
        r4 = r3.receivers.size();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r5 >= r4) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r6 = r3.receivers.get(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r6.dead != false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        r6.receiver.onReceive(r10.mAppContext, r3.intent);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executePendingBroadcasts() {
        /*
            r10 = this;
            r0 = 0
        L_0x0001:
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$ReceiverRecord>> r1 = r10.mReceivers
            monitor-enter(r1)
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$BroadcastRecord> r2 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0048 }
            int r2 = r2.size()     // Catch:{ all -> 0x0048 }
            if (r2 > 0) goto L_0x000e
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            return
        L_0x000e:
            android.support.v4.content.LocalBroadcastManager$BroadcastRecord[] r3 = new android.support.v4.content.LocalBroadcastManager.BroadcastRecord[r2]     // Catch:{ all -> 0x0048 }
            r0 = r3
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$BroadcastRecord> r3 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0048 }
            r3.toArray(r0)     // Catch:{ all -> 0x0048 }
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$BroadcastRecord> r3 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0048 }
            r3.clear()     // Catch:{ all -> 0x0048 }
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            r1 = 0
            r2 = r1
        L_0x001e:
            int r3 = r0.length
            if (r2 >= r3) goto L_0x0047
            r3 = r0[r2]
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$ReceiverRecord> r4 = r3.receivers
            int r4 = r4.size()
            r5 = r1
        L_0x002a:
            if (r5 >= r4) goto L_0x0044
            java.util.ArrayList<android.support.v4.content.LocalBroadcastManager$ReceiverRecord> r6 = r3.receivers
            java.lang.Object r6 = r6.get(r5)
            android.support.v4.content.LocalBroadcastManager$ReceiverRecord r6 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r6
            boolean r7 = r6.dead
            if (r7 != 0) goto L_0x0041
            android.content.BroadcastReceiver r7 = r6.receiver
            android.content.Context r8 = r10.mAppContext
            android.content.Intent r9 = r3.intent
            r7.onReceive(r8, r9)
        L_0x0041:
            int r5 = r5 + 1
            goto L_0x002a
        L_0x0044:
            int r2 = r2 + 1
            goto L_0x001e
        L_0x0047:
            goto L_0x0001
        L_0x0048:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.executePendingBroadcasts():void");
    }
}
