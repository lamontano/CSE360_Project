import java.io.*;
import java.util.*;

public class TextFormatter {
    private static final int COL_WIDTH = 80;
    private Stats stats = new Stats();

    public ArrayList<String> format(String PATH, String outputDirPath, JPanel panel, String direction) {
        ArrayList<String> result = new ArrayList<>();
        String outputFile = outputDirPath+"/output.txt";
        if (direction.equals("left")) {
            result = leftJustify(PATH, panel, stats);
        }
        if (direction.equals("right")) {
            result = rightJustify(PATH, panel, stats);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFile)));
            for(int i=0;i<result.size();i++){
                writer.write(result.get(i)+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        
        return result;
    }

    private ArrayList<String> leftJustify(String PATH, Stats stats) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int total_length = 0;

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
                if (line.isEmpty()) {
                    empty_line++;
                    continue;
                }
                line = line.replace('\t', ' ');
                String[] words = line.split(" ");
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
                    } else {
                        total_length += sb.length();
                        line_cnt++;
                        lines.add(sb.toString());

                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                        sb.append(word);
                        free_space -= word.length();
                    }
                }
            }

            // wrap last line
            total_length += sb.length();
            line_cnt++;
            lines.add(sb.toString());

            // close file
            line_reader.close();

            // update stats
            stats.update(line_cnt, word_cnt, empty_line, total_length);
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines;
    }


    private ArrayList<String> rightJustify(String PATH, Stats stats) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int total_length = 0;

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
                if (line.isEmpty()) {
                    empty_line++;
                    continue;
                }
                line = line.replace('\t', ' ');
                String[] words = line.split(" ");
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
                    } else {
                        total_length += sb.length();
                        line_cnt++;
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

            // wrap last line
            total_length += sb.length();
            line_cnt++;
            sb = sb.reverse();
            for (int i = 0; i < free_space; i++) {
                sb.append(" ");
            }
            sb = sb.reverse();
            lines.add(sb.toString());

            // close file
            line_reader.close();

            // update stats
            stats.update(line_cnt, word_cnt, empty_line, total_length);
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines;
    }

    public Stats getStats() {
        return this.stats;
    }
}