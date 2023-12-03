import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class Day3 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day3 <filename>");
            return;
        }

        // read file
        BufferedReader reader;
        List<String> input = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String l = reader.readLine();

            while (l != null) {
                input.add(l);
                l = reader.readLine();
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("File not found: " + args[0]);
            return;
        }

        // sum nums
        int sum = 0;
        for (Integer i : findNums(input)) {
            sum += i;
        }
        
        System.out.println("Sum of part nums: " + sum);
    }

    private static List<Integer> findNums(List<String> input){
        Pattern numRegex = Pattern.compile("\\d+");
        Pattern symRegex = Pattern.compile("[^.0-9]");
        List<Integer> nums = new ArrayList<>();

        // loop through each line in input (Each element of list)
        for (int i = 0; i < input.size(); i++) {
            
            // look for numbers in each line
            Matcher numMatcher = numRegex.matcher(input.get(i));
            while (numMatcher.find()) {

                // need to search 1 to left and right of num but not go out of bounds
                int left = numMatcher.start() - 1 > 0 ? numMatcher.start() - 1 : 0;
                int right = numMatcher.end() + 1 <= input.size() ? numMatcher.end() + 1 : input.size();
                boolean addNum = false;
                
                // look for symbols on the left or right of number
                Matcher symMatcher = symRegex.matcher(input.get(i).substring(left, right));
                if (symMatcher.find()) {
                    addNum = true;
                }

                if (i > 0) {
                    symMatcher = symRegex.matcher(input.get(i - 1).substring(left, right));
                    if (symMatcher.find()) {
                        addNum = true;
                    }
                }
                
                if (i < input.size() - 1) {
                    symMatcher = symRegex.matcher(input.get(i + 1).substring(left, right));
                    if (symMatcher.find()) {
                        addNum = true;
                    }
                }

                if (addNum) {
                    nums.add(Integer.parseInt(numMatcher.group()));
                }
            }
        }

        return nums;
    }
}
