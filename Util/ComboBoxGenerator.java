package Util;

import Objects.Account;
import Objects.Person;
import Objects.Stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ComboBoxGenerator is a helper class to generate mappings and keys for combo boxes.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class ComboBoxGenerator {
    /**
     * Maps the string representation of the account to the account itself.
     *
     * @param person the person to generate this mapping for
     * @return hashmap of the mapping
     */
    public static HashMap<String, Account> getAccMap(Person person, boolean securityAccount) {
        ArrayList<Account> accounts;
        if (securityAccount) {
            accounts = person.getSecurityAccounts();
        } else {
            accounts = person.getActiveAccounts();
        }

        HashMap<String, Account> accMap = new HashMap<>();

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

    /**
     * Maps the string represenation of the stock to the stock itself.
     *
     * @return hashmap of the mapping
     */
    public static HashMap<String, Stock> getStockMap() {
        ArrayList<Stock> stocks = DataManager.loadStocks();
        HashMap<String, Stock> stockMap = new HashMap<>();

        for (Stock s : stocks) {
            stockMap.put(s.toString(), s);
        }

        return stockMap;
    }

    /**
     * Gets the keys of the above mapping in a string array.
     *
     * @param stockMap the mapping
     * @return string array
     */
    public static String[] getStockKeys(HashMap<String, Stock> stockMap) {
        return stockMap.keySet().toArray(new String[0]);
    }
}
