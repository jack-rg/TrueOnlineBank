package Util;

import Objects.Account;
import Objects.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {
    public static HashMap<String, Account> getAccMap(Person person) {
        ArrayList<Account> accounts = person.getActiveAccounts();
        HashMap<String, Account> accMap = new HashMap<String, Account>();

        for (Account a : accounts) {
            accMap.put(a.toString(), a);
        }

        return accMap;
    }

    public static String[] getAccKeys(HashMap<String, Account> accMap) {
        return accMap.keySet().toArray(new String[0]);
    }
}
