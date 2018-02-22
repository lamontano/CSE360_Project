import java.io.*;
import java.util.*;


public class TextFormatter {
    private static final int COL_WIDTH = 80;

    public static ArrayList<String> format(String PATH, String direction) {
        ArrayList<String> result = new ArrayList<>();
        if (direction == "left") {
            result = leftJustify(PATH);
        }
        if (direction == "right") {
            result = rightJustify(PATH);
        }
        return result;
    }

    public static ArrayList<String> leftJustify(String PATH) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
                if (line.isEmpty()) {
                    empty_line++;
                }
                String[] words = line.split(" ");

                // stats for analysis
                line_cnt++;
                word_cnt += words.length;

                // wrap text
                for (String word: words) {
                    if (word == null || word.length() == 0) {
                        continue;
                    }
                    if (word.length() < free_space) {
                        if (sb.length() != 0) {
                            sb.append(" ");
                            free_space -= 1;
                        }
                        sb.append(word);
                        free_space -= word.length();
                    } else if (word.length() == free_space) {
                        sb.append(word);
                        lines.add(sb.toString());
                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                    } else {
                        lines.add(sb.toString());
                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                        sb.append(word);
                        free_space -= word.length();
                    }
                }
            }
            line_reader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines;
    }


    public static ArrayList<String> rightJustify(String PATH) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
                if (line.isEmpty()) {
                    empty_line++;
                }
                String[] words = line.split(" ");

                // stats for analysis
                line_cnt++;
                word_cnt += words.length;

                // wrap text
                for (String word: words) {
                    if (word == null || word.length() == 0) {
                        continue;
                    }
                    if (word.length() < free_space - 1) {
                        if (sb.length() != 0) {
                            sb.append(" ");
                            free_space -= 1;
                        }
                        sb.append(word);
                        free_space -= word.length();
                    } else if (word.length() == free_space) {
                        sb.append(word);
                        lines.add(sb.toString());
                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                    } else {
                        sb = sb.reverse();
                        for (int i = 0; i < free_space; i++) {
                            sb.append(" ");
                        }
                        sb = sb.reverse();
                        lines.add(sb.toString());
                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                        sb.append(word);
                        free_space -= word.length();
                    }
                }
            }
            line_reader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines;
    }



    public static void main(String[] args) {
        TextFormatter TF = new TextFormatter();
        String PATH = "E:\\java\\TextFormatter\\src\\test.txt";

        ArrayList<String> left = TF.leftJustify(PATH);
        ArrayList<String> right = TF.rightJustify(PATH);

        for (String line: left) {
            System.out.println(line);
        }
        for (String line: right) {
            System.out.println(line);
        }
    }
}
