# TrueOnlineBank
GRS CS611 Final Project - A GUI Bank ATM with specified features

By: Rachel Peng, Jack Giunta, Yuan Wei

** Since the presentation, we've made some bug fixes: **
- Creating a new account always uses updated accountIDs.
- Cannot withdraw or deposit a negative amount.

Files:
- GUI:
    - GUIAccount: GUIAccount creates the GUI that shows account details.
    - GUIAccountManager: GUIAccountManager creates the GUI that shows account details to the manager
    - GUIAccountsHome: GUIAccountsHome creates the GUI that shows the account tab.
    - GUIAccountsOverview: GUIAccountsOverview creates the GUI that shows all the user's accounts.
    - GUIBank: GUIBank creates GUI for the online Bank
    - GUIDailyTransactions: GUIDailyTransactions creates the GUI that shows daily transactions to the manager.
    - GUIDeposit: GUIDeposit creates the GUI that allows users to deposit money.
    - GUIHome: GUIHome creates the GUI that allows users to view accounts, take out loans, invest, and manage accounts (if user is a manager).
    - GUIInvestmentHome: GUIInvestmentHome creates the GUI that allows users to play in the stock market.
    - GUILoan: GUILoan creates the GUI that allows users to manage their loans. Our implementation only allows users to take out one loan at a time.
    - GUILogin: GUILogin creates the GUI that allows users to login.
    - GUINewAccount: GUINewAccount creates the GUI that allows users to create new accounts.
    - GUIPosition: GUIPosition creates the GUI that allows users to see their investment positions.
    - GUIRegister: GUIRegister creates the GUI that allows new users to register.
    - GUISettings: GUISettings creates the GUI that allows users to manage their username and password.
    - GUIStockOrder: GUIStockOrder creates the GUI that allows users to view a single stock order.
    - GUIStockOrderHistory: GUIStockOrderHistory creates the GUI that allows users to see their entire stock order history.
    - GUIStockPositions: GUIStockPositions creates the GUI that allows users to see all their investment positions.
    - GUIStockTrade: GUIStockTrade creates the GUI that allows users to trade stocks.
    - GUITransaction: GUITransaction creates the GUI that allows users to see a single transaction.
    - GUITransfer: GUITransfer creates the GUI that allows users to transfer money across accounts or to different users.
    - GUIUser: GUIUser creates the GUI that allows managers to see a single user's information.
    - GUIUsersOverview: GUIUsersOverview creates the GUI that allows managers to see all users' information.
    - GUIWithdraw: GUIWithdraw creates the GUI that allows users to withdraw money.
    
- Interfaces:
    - TransactionInterface: TransactionInterface ensures that objects implementing this interface has the transaction functionality.
    
- Objects:
    - Account: Account implements the TransactionInterface and provides a base for all types of accounts.
    - Checking: Checking extends Account to represent a Checking account.
    - Loan: Loan represents a loan that the user could have.
    - Manager: Manager extends Person to represent a Manager of the bank.
    - Person: Person represents a person that can interact at the Bank.
    - Position: Position represent the details of a stock that the buyer have
    - Saving: Saving extends Account to represent a Savings account
    - Security: Security extends Account to represent a Security Account.
    - Stock: Stock represents a stock
    - StockOrder: StockOrder represents a stock order.
    - Transaction: Transaction represents a transaction that the user could have within an account
    - User: User extends Person to represent a user of the bank.
    
- Types:
    - AccountType: AccountType enum enumerates the types of Accounts and their string representations.
    - CurrencyType: CurrencyType enum enumerates the types of currencies and their values.
    - Status: Status enum enumerates the status.
    - StockOrderType: StockOrderType enum enumerates the types of stock orders.
    - TransactionType: TransactionType enum enumerates the types of transactions
    
- Util:
    - AccountManager: AccountManager is a helper class to manage accounts.
    - CurrencyConverter: CurrencyConverter is a helper class to convert money.
    - DataManager: DataManager is a helper class to read and write to text files
    - MD5Util: MD5Util is a helper class to encrypt our passwords

Instructions to Compile and Run:
- Open Terminal
- Navigate to the correct directory.
- Run the command to compile the program: make all
- Use the GUI to navigate.

Other Notes:
- Users are not charged transaction fees on loan payments, interest payments, opening fees, or closing fees.
- Users cannot take out another loan if they already have one active.
- We have a partial implementation completed for encrypting passwords. This can be found in the MD5Util.