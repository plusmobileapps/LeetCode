
import java.util.HashSet


/**
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */


fun main(args: Array<String>) {
    val strings = arrayListOf("abcabcbb", "bbbbb", "pwwkew")
    strings.forEach {  string ->
        println("Length of longest substring in $string is ${lengthOfLongestSubstring(string)}")
    }

}

fun lengthOfLongestSubstring(s: String): Int {
    val n = s.length
    var ans = 0
    for (i in 0 until n)
        for (j in i + 1..n)
            if (allUnique(s, i, j)) ans = Math.max(ans, j - i)
    return ans
}

fun allUnique(s: String, start: Int, end: Int): Boolean {
    val set = HashSet<Char>()
    for (i in start until end) {
        val ch = s[i]
        if (set.contains(ch)) return false
        set.add(ch)
    }
    return true
}



