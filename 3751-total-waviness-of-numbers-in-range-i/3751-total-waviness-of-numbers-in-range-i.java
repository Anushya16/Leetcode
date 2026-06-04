public class Solution {
    public static int totalWaviness(int num1, int num2) {
        int totalSum = 0;
        
        for (int i = num1; i <= num2; i++) {
            totalSum += getWaviness(i);
        }
        
        return totalSum;
    }

    private static int getWaviness(int num) {
        // Convert number to string to easily access individual digits
        String s = Integer.toString(num);
        int len = s.length();
        
        // Any number with fewer than 3 digits has a waviness of 0
        if (len < 3) {
            return 0;
        }
        
        int waviness = 0;
        
        // Loop from the second digit to the second-to-last digit
        for (int i = 1; i < len - 1; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);
            
            // Check for Peak
            if (curr > prev && curr > next) {
                waviness++;
            }
            // Check for Valley
            else if (curr < prev && curr < next) {
                waviness++;
            }
        }
        
        return waviness;
    }

    public static void main(String[] args) {
        // Test Example 1
        System.out.println("Output for [120, 130]: " + totalWaviness(120, 130)); // Expected: 3

        // Test Example 2
        System.out.println("Output for [198, 202]: " + totalWaviness(198, 202)); // Expected: 3

        // Test Example 3
        System.out.println("Output for [4848, 4848]: " + totalWaviness(4848, 4848)); // Expected: 2
    }
}