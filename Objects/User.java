package Objects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User extends Person {

    public User(String userName, String password, int userID) {
        super(userName, password, userID);
        addToUserLog();
    }

    public void addToUserLog() {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
            String accountFormatter = "%s | %s | %s | %d \n";
            out.printf(accountFormatter, dtf.format(LocalDateTime.now()), getUserName(), getPassword(), getUserID());
            out.flush();
            out.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}