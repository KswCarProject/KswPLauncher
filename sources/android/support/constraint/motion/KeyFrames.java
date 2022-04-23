package android.support.constraint.motion;

import android.content.Context;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class KeyFrames {
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;
    static HashMap<String, Constructor<? extends Key>> sKeyMakers;
    private HashMap<Integer, ArrayList<Key>> mFramesMap = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends Key>> hashMap = new HashMap<>();
        sKeyMakers = hashMap;
        try {
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyPosition", KeyPosition.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyCycle", KeyCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyTrigger", KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "unable to load", e);
        }
    }

    private void addKey(Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.mTargetId))) {
            this.mFramesMap.put(Integer.valueOf(key.mTargetId), new ArrayList());
        }
        this.mFramesMap.get(Integer.valueOf(key.mTargetId)).add(key);
    }

    public KeyFrames(Context context, XmlPullParser parser) {
        Key key = null;
        try {
            int eventType = parser.getEventType();
            while (eventType != 1) {
                switch (eventType) {
                    case 0:
                        break;
                    case 2:
                        String tagName = parser.getName();
                        if (!sKeyMakers.containsKey(tagName)) {
                            if (!(!tagName.equalsIgnoreCase("CustomAttribute") || key == null || key.mCustomConstraints == null)) {
                                ConstraintAttribute.parse(context, parser, key.mCustomConstraints);
                                break;
                            }
                        } else {
                            try {
                                key = (Key) sKeyMakers.get(tagName).newInstance(new Object[0]);
                                key.load(context, Xml.asAttributeSet(parser));
                                addKey(key);
                                break;
                            } catch (Exception e) {
                                Log.e(TAG, "unable to create ", e);
                                break;
                            }
                        }
                    case 3:
                        if ("KeyFrameSet".equals(parser.getName())) {
                            return;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> list = this.mFramesMap.get(Integer.valueOf(motionController.mId));
        if (list != null) {
            motionController.addKeys(list);
        }
        ArrayList<Key> list2 = this.mFramesMap.get(-1);
        if (list2 != null) {
            Iterator<Key> it = list2.iterator();
            while (it.hasNext()) {
                Key key = it.next();
                if (key.matches(((ConstraintLayout.LayoutParams) motionController.mView.getLayoutParams()).constraintTag)) {
                    motionController.addKey(key);
                }
            }
        }
    }

    static String name(int viewId, Context context) {
        return context.getResources().getResourceEntryName(viewId);
    }

    public Set<Integer> getKeys() {
        return this.mFramesMap.keySet();
    }

    public ArrayList<Key> getKeyFramesForView(int id) {
        return this.mFramesMap.get(Integer.valueOf(id));
    }
}
