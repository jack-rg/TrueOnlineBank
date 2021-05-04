package Types;

/**
 * AccountType enum enumerates the types of Accounts and their string representations.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public enum AccountType {
    CHECKING {
        @Override
        public String toString() {
            return "Checking";
        }
    }, SAVING {
        @Override
        public String toString() {
            return "Savings";
        }
    }, SECURITY {
        @Override
        public String toString() {
            return "Security";
        }
    };
}
