package com.google.zxing.client.result;

public final class AddressBookParsedResult extends ParsedResult {
    private final String[] addressTypes;
    private final String[] addresses;
    private final String birthday;
    private final String[] emailTypes;
    private final String[] emails;
    private final String[] geo;
    private final String instantMessenger;
    private final String[] names;
    private final String[] nicknames;
    private final String note;
    private final String org;
    private final String[] phoneNumbers;
    private final String[] phoneTypes;
    private final String pronunciation;
    private final String title;
    private final String[] urls;

    public AddressBookParsedResult(String[] names2, String[] phoneNumbers2, String[] phoneTypes2, String[] emails2, String[] emailTypes2, String[] addresses2, String[] addressTypes2) {
        this(names2, (String[]) null, (String) null, phoneNumbers2, phoneTypes2, emails2, emailTypes2, (String) null, (String) null, addresses2, addressTypes2, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddressBookParsedResult(String[] names2, String[] nicknames2, String pronunciation2, String[] phoneNumbers2, String[] phoneTypes2, String[] emails2, String[] emailTypes2, String instantMessenger2, String note2, String[] addresses2, String[] addressTypes2, String org2, String birthday2, String title2, String[] urls2, String[] geo2) {
        super(ParsedResultType.ADDRESSBOOK);
        String[] strArr = phoneNumbers2;
        String[] strArr2 = phoneTypes2;
        String[] strArr3 = emails2;
        String[] strArr4 = emailTypes2;
        String[] strArr5 = addresses2;
        String[] strArr6 = addressTypes2;
        if (strArr != null && strArr2 != null && strArr.length != strArr2.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        } else if (strArr3 != null && strArr4 != null && strArr3.length != strArr4.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        } else if (strArr5 == null || strArr6 == null || strArr5.length == strArr6.length) {
            this.names = names2;
            this.nicknames = nicknames2;
            this.pronunciation = pronunciation2;
            this.phoneNumbers = strArr;
            this.phoneTypes = strArr2;
            this.emails = strArr3;
            this.emailTypes = strArr4;
            this.instantMessenger = instantMessenger2;
            this.note = note2;
            this.addresses = strArr5;
            this.addressTypes = strArr6;
            this.org = org2;
            this.birthday = birthday2;
            this.title = title2;
            this.urls = urls2;
            this.geo = geo2;
        } else {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
    }

    public String[] getNames() {
        return this.names;
    }

    public String[] getNicknames() {
        return this.nicknames;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public String[] getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public String[] getPhoneTypes() {
        return this.phoneTypes;
    }

    public String[] getEmails() {
        return this.emails;
    }

    public String[] getEmailTypes() {
        return this.emailTypes;
    }

    public String getInstantMessenger() {
        return this.instantMessenger;
    }

    public String getNote() {
        return this.note;
    }

    public String[] getAddresses() {
        return this.addresses;
    }

    public String[] getAddressTypes() {
        return this.addressTypes;
    }

    public String getTitle() {
        return this.title;
    }

    public String getOrg() {
        return this.org;
    }

    public String[] getURLs() {
        return this.urls;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String[] getGeo() {
        return this.geo;
    }

    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(100);
        maybeAppend(this.names, result);
        maybeAppend(this.nicknames, result);
        maybeAppend(this.pronunciation, result);
        maybeAppend(this.title, result);
        maybeAppend(this.org, result);
        maybeAppend(this.addresses, result);
        maybeAppend(this.phoneNumbers, result);
        maybeAppend(this.emails, result);
        maybeAppend(this.instantMessenger, result);
        maybeAppend(this.urls, result);
        maybeAppend(this.birthday, result);
        maybeAppend(this.geo, result);
        maybeAppend(this.note, result);
        return result.toString();
    }
}
