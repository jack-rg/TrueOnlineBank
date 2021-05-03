package Types;

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
