package com.google.zxing.client.result;

public final class WifiParsedResult extends ParsedResult {
    private final String anonymousIdentity;
    private final String eapMethod;
    private final boolean hidden;
    private final String identity;
    private final String networkEncryption;
    private final String password;
    private final String phase2Method;
    private final String ssid;

    public WifiParsedResult(String networkEncryption2, String ssid2, String password2) {
        this(networkEncryption2, ssid2, password2, false);
    }

    public WifiParsedResult(String networkEncryption2, String ssid2, String password2, boolean hidden2) {
        this(networkEncryption2, ssid2, password2, hidden2, (String) null, (String) null, (String) null, (String) null);
    }

    public WifiParsedResult(String networkEncryption2, String ssid2, String password2, boolean hidden2, String identity2, String anonymousIdentity2, String eapMethod2, String phase2Method2) {
        super(ParsedResultType.WIFI);
        this.ssid = ssid2;
        this.networkEncryption = networkEncryption2;
        this.password = password2;
        this.hidden = hidden2;
        this.identity = identity2;
        this.anonymousIdentity = anonymousIdentity2;
        this.eapMethod = eapMethod2;
        this.phase2Method = phase2Method2;
    }

    public String getSsid() {
        return this.ssid;
    }

    public String getNetworkEncryption() {
        return this.networkEncryption;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getAnonymousIdentity() {
        return this.anonymousIdentity;
    }

    public String getEapMethod() {
        return this.eapMethod;
    }

    public String getPhase2Method() {
        return this.phase2Method;
    }

    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(80);
        maybeAppend(this.ssid, result);
        maybeAppend(this.networkEncryption, result);
        maybeAppend(this.password, result);
        maybeAppend(Boolean.toString(this.hidden), result);
        return result.toString();
    }
}
