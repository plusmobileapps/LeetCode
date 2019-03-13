
import java.util.HashMap

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

fun main(args: Array<String>) {
    val nums = intArrayOf(2,7,11,15)
    val target = 9

    val twoSumBrute = twoSumBrute(nums, target)
    val twoSumTwoPassHash = twoSumTwoPassHashTable(nums, target)
    val twoSumOnePassHash = twoSumOnePassHashTable(nums, target)

    printTwoSum(nums, twoSumBrute, "Brute Force")
    printTwoSum(nums, twoSumTwoPassHash, "Two Pass Hash Table")
    printTwoSum(nums, twoSumOnePassHash, "One Pass Hash Table")

}

fun printTwoSum(nums: IntArray, twoSum: IntArray, method: String) {
    print("$method: Two sum of numbers ")
    nums.forEachIndexed { index, i ->
        print("$i, ")
        if (index == nums.lastIndex) {
            println("is ${twoSum[0]} and ${twoSum[1]}")
        }
    }
}

fun twoSumBrute(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            if (nums[j] == target - nums[i]) {
                return intArrayOf(i, j)
            }
        }
    }
    throw IllegalArgumentException("No two sum solution")
}

fun twoSumTwoPassHashTable(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for (i in nums.indices) {
        map[nums[i]] = i
    }
    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement) && map[complement] != i) {
            return intArrayOf(i, map[complement]!!)
        }
    }
    throw IllegalArgumentException("No two sum solution")
}

fun twoSumOnePassHashTable(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement)) {
            return intArrayOf(map[complement]!!, i)
        }
        map[nums[i]] = i
    }
    throw IllegalArgumentException("No two sum solution")
}