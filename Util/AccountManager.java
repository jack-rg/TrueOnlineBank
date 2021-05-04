package Util;

import Objects.Account;
import Objects.Person;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * AccountManager is a helper class to manage accounts.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class AccountManager {
    /**
     * Maps the string represenation of the account to the account itself.
     *
     * @param person the person to generate this mapping for
     * @return hashmap of the mapping
     */
    public static HashMap<String, Account> getAccMap(Person person) {
        ArrayList<Account> accounts = person.getActiveAccounts();
        HashMap<String, Account> accMap = new HashMap<String, Account>();

        for (Account a : accounts) {
            accMap.put(a.toString(), a);
        }

        return accMap;
    }

    /**
     * Gets the keys of the above mapping in a string array.
     *
     * @param accMap the mapping
     * @return string array
     */
    public static String[] getAccKeys(HashMap<String, Account> accMap) {
        return accMap.keySet().toArray(new String[0]);
    }
}
