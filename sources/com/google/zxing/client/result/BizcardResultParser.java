package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class BizcardResultParser extends AbstractDoCoMoResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("BIZCARD:")) {
            return null;
        }
        String fullName = buildName(matchSingleDoCoMoPrefixedField("N:", rawText, true), matchSingleDoCoMoPrefixedField("X:", rawText, true));
        String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("C:", rawText, true);
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("A:", rawText, true);
        String phoneNumber1 = matchSingleDoCoMoPrefixedField("B:", rawText, true);
        String phoneNumber2 = matchSingleDoCoMoPrefixedField("M:", rawText, true);
        String phoneNumber3 = matchSingleDoCoMoPrefixedField("F:", rawText, true);
        String email = matchSingleDoCoMoPrefixedField("E:", rawText, true);
        String str = phoneNumber3;
        String str2 = phoneNumber2;
        String str3 = phoneNumber1;
        return new AddressBookParsedResult(maybeWrap(fullName), (String[]) null, (String) null, buildPhoneNumbers(phoneNumber1, phoneNumber2, phoneNumber3), (String[]) null, maybeWrap(email), (String[]) null, (String) null, (String) null, matchDoCoMoPrefixedField, (String[]) null, matchSingleDoCoMoPrefixedField, (String) null, matchSingleDoCoMoPrefixedField("T:", rawText, true), (String[]) null, (String[]) null);
    }

    private static String[] buildPhoneNumbers(String number1, String number2, String number3) {
        List<String> numbers = new ArrayList<>(3);
        if (number1 != null) {
            numbers.add(number1);
        }
        if (number2 != null) {
            numbers.add(number2);
        }
        if (number3 != null) {
            numbers.add(number3);
        }
        int size = numbers.size();
        int size2 = size;
        if (size == 0) {
            return null;
        }
        return (String[]) numbers.toArray(new String[size2]);
    }

    private static String buildName(String firstName, String lastName) {
        if (firstName == null) {
            return lastName;
        }
        return lastName == null ? firstName : firstName + ' ' + lastName;
    }
}
