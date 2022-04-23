package skin.support.design.app;

import skin.support.app.SkinLayoutInflater;

public class SkinMaterialViewInflater implements SkinLayoutInflater {
    /* JADX WARNING: type inference failed for: r0v1, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v2, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v3, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r0v9, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View createView(android.content.Context r4, java.lang.String r5, android.util.AttributeSet r6) {
        /*
            r3 = this;
            r0 = 0
            java.lang.String r1 = "android.support.design.widget."
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L_0x000b
            r1 = 0
            return r1
        L_0x000b:
            r1 = -1
            int r2 = r5.hashCode()
            switch(r2) {
                case -1830764433: goto L_0x0065;
                case -1138726461: goto L_0x005a;
                case -272444186: goto L_0x0050;
                case 170302044: goto L_0x0046;
                case 285085340: goto L_0x003c;
                case 285456578: goto L_0x0032;
                case 796212404: goto L_0x0028;
                case 890321297: goto L_0x001e;
                case 951543143: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x006e
        L_0x0014:
            java.lang.String r2 = "android.support.design.widget.TabLayout"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 1
            goto L_0x006e
        L_0x001e:
            java.lang.String r2 = "android.support.design.widget.NavigationView"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 4
            goto L_0x006e
        L_0x0028:
            java.lang.String r2 = "android.support.design.widget.AppBarLayout"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 0
            goto L_0x006e
        L_0x0032:
            java.lang.String r2 = "android.support.design.widget.CoordinatorLayout"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 7
            goto L_0x006e
        L_0x003c:
            java.lang.String r2 = "android.support.design.widget.BottomNavigationView"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 6
            goto L_0x006e
        L_0x0046:
            java.lang.String r2 = "android.support.design.widget.TextInputEditText"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 3
            goto L_0x006e
        L_0x0050:
            java.lang.String r2 = "android.support.design.widget.FloatingActionButton"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 5
            goto L_0x006e
        L_0x005a:
            java.lang.String r2 = "android.support.design.widget.CollapsingToolbarLayout"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 8
            goto L_0x006e
        L_0x0065:
            java.lang.String r2 = "android.support.design.widget.TextInputLayout"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0013
            r1 = 2
        L_0x006e:
            switch(r1) {
                case 0: goto L_0x00aa;
                case 1: goto L_0x00a3;
                case 2: goto L_0x009c;
                case 3: goto L_0x0095;
                case 4: goto L_0x008e;
                case 5: goto L_0x0087;
                case 6: goto L_0x0080;
                case 7: goto L_0x0079;
                case 8: goto L_0x0072;
                default: goto L_0x0071;
            }
        L_0x0071:
            goto L_0x00b1
        L_0x0072:
            skin.support.design.widget.SkinMaterialCollapsingToolbarLayout r1 = new skin.support.design.widget.SkinMaterialCollapsingToolbarLayout
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x0079:
            skin.support.design.widget.SkinMaterialCoordinatorLayout r1 = new skin.support.design.widget.SkinMaterialCoordinatorLayout
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x0080:
            skin.support.design.widget.SkinMaterialBottomNavigationView r1 = new skin.support.design.widget.SkinMaterialBottomNavigationView
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x0087:
            skin.support.design.widget.SkinMaterialFloatingActionButton r1 = new skin.support.design.widget.SkinMaterialFloatingActionButton
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x008e:
            skin.support.design.widget.SkinMaterialNavigationView r1 = new skin.support.design.widget.SkinMaterialNavigationView
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x0095:
            skin.support.design.widget.SkinMaterialTextInputEditText r1 = new skin.support.design.widget.SkinMaterialTextInputEditText
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x009c:
            skin.support.design.widget.SkinMaterialTextInputLayout r1 = new skin.support.design.widget.SkinMaterialTextInputLayout
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x00a3:
            skin.support.design.widget.SkinMaterialTabLayout r1 = new skin.support.design.widget.SkinMaterialTabLayout
            r1.<init>(r4, r6)
            r0 = r1
            goto L_0x00b1
        L_0x00aa:
            skin.support.design.widget.SkinMaterialAppBarLayout r1 = new skin.support.design.widget.SkinMaterialAppBarLayout
            r1.<init>(r4, r6)
            r0 = r1
        L_0x00b1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.design.app.SkinMaterialViewInflater.createView(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }
}
