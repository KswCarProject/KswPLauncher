package androidx.versionedparcelable;

public abstract class CustomVersionedParcelable implements VersionedParcelable {
    public void onPreParceling(boolean isStream) {
    }

    public void onPostParceling() {
    }
}
