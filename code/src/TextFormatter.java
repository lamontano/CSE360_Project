import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TextFormatter {
    private static int COL_WIDTH;
    private Stats stats;

    public TextFormatter() {
        this.stats = new Stats();
    }

    public void format(JPanel panel, int line_length, String input_path, String output_path, String direction, String spacing) {
        COL_WIDTH = line_length;
        ArrayList<String> result = new ArrayList<>();

        if (direction.equals("left")) {
            result = leftJustify(panel, input_path, spacing, stats);
        }
        if (direction.equals("right")) {
            result = rightJustify(panel, input_path, spacing, stats);
        }
        if (direction.equals("full")) {
            result = fullJustify(panel, input_path, spacing, stats); // function to be implemented
        }
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output_path)));
			for (int i = 0; i < result.size() - 1; i++) {
			    writer.write(result.get(i) + "\n");
			}
	        writer.write(result.get(result.size() - 1));
			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
    }


	private ArrayList<String> leftJustify(JPanel panel, String PATH, String spacing, Stats stats) {
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
                    ++empty_line;
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
                            ++spaces_added;
                            --free_space;
                        }
                        sb.append(word);
                        free_space -= word.length();
                    } else {
                        total_length += sb.length();
                        line_cnt++;
                        lines.add(sb.toString());
                        if ("double".equals(spacing)) {
                            lines.add("");
                            ++line_cnt;
                        }

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
        	JOptionPane.showMessageDialog(panel, "ERROR: Input file not found.");
        }
        return lines;
    }


    private ArrayList<String> rightJustify(JPanel panel, String PATH, String spacing, Stats stats) {
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
                    ++empty_line;
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
                            ++spaces_added;
                            --free_space;
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
                        if ("double".equals(spacing)) {
                            lines.add("");
                            ++line_cnt;
                        }

                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                        sb.append(word);
                        free_space -= word.length();
                    }
                }
            }

            // wrap last line
            total_length += sb.length();
            ++line_cnt;
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
        	JOptionPane.showMessageDialog(panel, "ERROR: Input file not found.");
        }
        return lines;
    }

    private ArrayList<String> fullJustify(JPanel panel, String PATH, String spacing, Stats stats) {
        ArrayList<String> lines = new ArrayList<String>();

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
                    ++empty_line;
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
                            ++spaces_added;
                            --free_space;
                        }
                        sb.append(word);
                        free_space -= word.length();
                    } else {
                        spaces_added = appendSpace(sb, free_space, spaces_added);
                        total_length += sb.length();
                        ++line_cnt;
                        lines.add(sb.toString());
                        if ("double".equals(spacing)) {
                            lines.add("");
                            ++line_cnt;
                        }

                        sb = new StringBuffer();
                        free_space = COL_WIDTH;
                        sb.append(word);
                        free_space -= word.length();
                    }
                }
            }

            // wrap last line
            spaces_added = appendSpace(sb, COL_WIDTH - sb.length(), spaces_added);
            total_length += sb.length();
            ++line_cnt;
            lines.add(sb.toString());

            // close file
            line_reader.close();

            // update stats
            stats.update(line_cnt, word_cnt, empty_line, total_length, spaces_added);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, "ERROR: Input file not found.");
        }
        return lines;
    }

    private int appendSpace(StringBuffer sb, int free_space, int space_added) {
        // split words
        String line = sb.toString();
        String[] words = line.split(" ");
        if (words.length == 1) {
            return space_added;
        }

        // calculate stats for adding spaces evenly
        int total_space_num = (words.length - 1) + free_space;
        int avg_space_num = total_space_num / (words.length - 1);
        int remainder = total_space_num % (words.length - 1);

        // clear StringBuffer
        sb.delete(0, sb.length());

        // add spaces evenly
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i]);
            for (int j = 0; j < avg_space_num; j++) {
                sb.append(" ");
            }
            if (remainder != 0) {
                sb.append(" ");
                --remainder;
            }
        }

        // add last word
        sb.append(words[words.length - 1]);
        space_added += free_space;
        return space_added;
    }

    public Stats getStats() {
        return this.stats;
    }

}