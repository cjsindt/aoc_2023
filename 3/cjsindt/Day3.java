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

        // sum part nums
        int partNumSum = 0;
        for (Integer i : findPartNums(input)) {
            partNumSum += i;
        }

        System.out.println("Sum of part nums: " + partNumSum);

        // sum gear ratios
        int gearRatioSum = 0;
        for (Integer i : findGearRatios(input)) {
            gearRatioSum += i;
        }

        System.out.println("Sum of gear ratios: " + gearRatioSum);
    }

    private static List<Integer> findPartNums(List<String> input){
        List<Integer> nums = new ArrayList<>();
        
        Pattern numRegex = Pattern.compile("\\d+");
        Pattern symRegex = Pattern.compile("[^.0-9]");

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

    private static List<Integer> findGearRatios(List<String> input) {
        List<Integer> ratios = new ArrayList<>();

        Pattern gearRegex = Pattern.compile("[*]");    
        Pattern numRegex = Pattern.compile("\\d+");

        // loop through each line in input (Each element of list)
        for (int i = 0; i < input.size(); i++) {

            // find each *
            Matcher gearMatcher = gearRegex.matcher(input.get(i));
            while (gearMatcher.find()) {
                int left = gearMatcher.start() - 1 > 0 ? gearMatcher.start() - 1 : 0;
                int right = gearMatcher.end() + 1 <= input.size() ? gearMatcher.end() + 1 : input.size();
                List<Integer> numsTouchingGear = new ArrayList<>();

                // find all nums that could be next to the gear
                Matcher numMatcher = numRegex.matcher(input.get(i));
                while (numMatcher.find()) {
                    // ranges overlap; num touching gear
                    if (numMatcher.start() < right && numMatcher.end() > left) {
                        numsTouchingGear.add(Integer.parseInt(numMatcher.group()));
                    }
                }

                if (i > 0) {
                    numMatcher = numRegex.matcher(input.get(i - 1));
                    while (numMatcher.find()) {
                        // ranges overlap; num touching gear
                        if (numMatcher.start() < right && numMatcher.end() > left) {
                            numsTouchingGear.add(Integer.parseInt(numMatcher.group()));
                        }
                    }
                }

                if (i < input.size() - 1) {
                    numMatcher = numRegex.matcher(input.get(i + 1));
                    while (numMatcher.find()) {
                        // ranges overlap; num touching gear
                        if (numMatcher.start() < right && numMatcher.end() > left) {
                            numsTouchingGear.add(Integer.parseInt(numMatcher.group()));
                        }
                    }
                }

                if (numsTouchingGear.size() == 2) {
                    ratios.add(numsTouchingGear.get(0) * numsTouchingGear.get(1));
                }

            }
        }
        return ratios;
    }
}
