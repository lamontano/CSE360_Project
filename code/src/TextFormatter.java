import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TextFormatter {
    private static int COL_WIDTH;
    private Stats stats;

    public TextFormatter() {
        this.stats = new Stats();
    }

    public ArrayList<String> format(int line_length, String PATH, String outputDirPath, JPanel panel, String direction) {
        COL_WIDTH = line_length;
        ArrayList<String> result = new ArrayList<>();
        String outputFile = outputDirPath;

        if (direction.equals("left")) {
            result = leftJustify(PATH, panel, stats);
        }
        if (direction.equals("right")) {
            result = rightJustify(PATH, panel, stats);
        }
        if (direction.equals("full")) {
            result = fullJustify(PATH, panel, stats); // function to be implemented
        }
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFile)));
			for(int i=0;i<result.size();i++) {
				writer.write(result.get(i)+"\n");
	        }
			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
        
        return result;
        
    }


	private ArrayList<String> leftJustify(String PATH, JPanel panel, Stats stats) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int total_length = 0;
        int spaces_added = 0;

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
            	line = line.replaceAll("\\s+", " ");
                line = line.replaceAll("\t", " ");
                if (line.isEmpty()) {
                    empty_line++;
                    continue;
                }
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
                            spaces_added++;
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
            stats.update(line_cnt, word_cnt, empty_line, total_length, spaces_added);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(panel, "ERROR: Input file not found");
        }
        return lines;
    }


    private ArrayList<String> rightJustify(String PATH, JPanel panel, Stats stats) {
        // initialization
        ArrayList<String> lines = new ArrayList<>();
        int free_space = COL_WIDTH;
        StringBuffer sb = new StringBuffer();

        int word_cnt = 0;
        int line_cnt = 0;
        int empty_line = 0;
        int total_length = 0;
        int spaces_added = 0;

        try {
            // open text file
            BufferedReader line_reader = new BufferedReader(new FileReader(PATH));
            String line = null;

            // read line by line
            while ((line = line_reader.readLine()) != null) {
                // split line into words
                line = line.replaceAll("\\s+", " ");
                line = line.replaceAll("\t", " ");
                if (line.isEmpty()) {
                    empty_line++;
                    continue;
                }
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
                            spaces_added++;
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
            stats.update(line_cnt, word_cnt, empty_line, total_length, spaces_added);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(panel, "ERROR: Input file not found");
        }
        return lines;
    }

    private ArrayList<String> fullJustify(String PATH, JPanel panel, Stats stats) {
        ArrayList<String> results = new ArrayList<>();
        return results;
    }

    public Stats getStats() {
        return this.stats;
    }

}