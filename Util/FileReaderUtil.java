package Util;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReaderUtil {

    public static ArrayList<String> readUserPosFileByLine(String UserID) throws IOException {
        String file = Paths.get("").toAbsolutePath() + "/Logs/positionLog_" + UserID + ".txt";
        System.out.println(file);
        File deleteFile = new File(file);

        if(!deleteFile.exists()){
            throw new IOException("file not exist");
        }
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        ArrayList<String> results = new ArrayList<>();
        while((str = bufferedReader.readLine()) != null)
        {
            results.add(str);
            System.out.println(str);
        }

        //close
        inputStream.close();
        bufferedReader.close();

        return results;
    }

    public static void main(String[] args) throws IOException {
        FileReaderUtil.readUserPosFileByLine("Yuan");
    }
}
