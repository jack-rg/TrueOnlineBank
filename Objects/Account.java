package Objects;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Util.*;

public abstract class Account {
	private String accountID;
	private String userID;
	private double value;
	private String name;
	private State status;

	public Account(String userName, String accountID, String userID) {
		this.name = userName;
		this.accountID = accountID;
		this.userID = userID;
		value = 0;
		status = State.ACTIVE;
	}

	public Account(String userName, String accountID, String userID, double value) {
		this(userName, accountID, userID);
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String newID) {
		accountID = newID;
	}

	public double getValue() {
		return value;
	}

	public void setValue(int newValue) {
		value = newValue;
	}

	public void deposit(int funds) {
		value += funds;
	}

	public int withdrawal(int funds) {
		try {
			value -= funds;
			return funds;
		} catch (Exception e) {
			return -1;
		}
	}

	public void addToAccountLog() {
		String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";
		try {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
			String accountFormatter = "%s | %s | %s | %s | %f | %s \n";
			out.printf(accountFormatter, dtf.format(LocalDateTime.now()), userID, name, accountID, value, "ACTIVE");
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void deactivateAccount() {
		status = State.INACTIVE;
		String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> traceFile = new ArrayList<String>();
			String line;

			while ((line = br.readLine()) != null) {
				if (line.contains("| " + userID + " |") && line.contains("| " + accountID + " |")) {
					String[] accountInfo = line.split(" \\| ");
					accountInfo[accountInfo.length - 1] = "INACTIVE";
					String replaced = String.join(" | ", accountInfo);
					traceFile.add(replaced);
				}
				else {
				traceFile.add(line);
				}
			}
			br.close();
			FileOutputStream fileOut = new FileOutputStream(file);
			for(String output : traceFile) {
	        fileOut.write((output+"\n").toString().getBytes());
			}
			fileOut.flush();
	        fileOut.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}