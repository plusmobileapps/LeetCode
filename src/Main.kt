
import java.util.HashSet
import java.util.HashMap




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

    printLongestSubstring(strings = strings, method = "All Unique") { string ->
        lengthOfLongestSubstring(string)
    }

    printLongestSubstring(strings = strings, method = "Sliding Window") { string ->
        slidingWindow(string)
    }

    printLongestSubstring(strings = strings, method = "Sliding Window Optimized") { string ->
        slidingWindowOptimized(string)
    }

}

fun printLongestSubstring(strings: ArrayList<String>, method: String, block: (String) -> Int) {
    strings.forEach {  string ->
        val length = block(string)
        println("Length of longest substring in $string is solved with $method is $length")
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

fun slidingWindow(s: String): Int {
    val n = s.length
    val set = HashSet<Char>()
    var ans = 0
    var i = 0
    var j = 0
    while (i < n && j < n) {
        // try to extend the range [i, j]
        if (!set.contains(s[j])) {
            set.add(s[j++])
            ans = Math.max(ans, j - i)
        } else {
            set.remove(s[i++])
        }
    }
    return ans
}

fun slidingWindowOptimized(s: String): Int {
    val n = s.length
    var ans = 0
    val map = HashMap<Char, Int>() // current index of character
    // try to extend the range [i, j]
    var j = 0
    var i = 0
    while (j < n) {
        if (map.containsKey(s[j])) {
            i = Math.max(map[s[j]]!!, i)
        }
        ans = Math.max(ans, j - i + 1)
        map[s[j]] = j + 1
        j++
    }
    return ans
}



