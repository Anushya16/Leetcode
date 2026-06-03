class Solution {
    public int numberOfSpecialChars(String word) {
        int count = 0;

        for (char ch = 'a'; ch <= 'z'; ch++) {

            int lastLower = word.lastIndexOf(ch);
            int firstUpper = word.indexOf(Character.toUpperCase(ch));

            // Check if both lowercase and uppercase exist
            // and all lowercase come before uppercase
            if (lastLower != -1 && firstUpper != -1 && lastLower < firstUpper) {
                count++;
            }
        }

        return count;
    }
}