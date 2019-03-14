/**
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

fun main(args: Array<String>) {
    val nums1 = intArrayOf(1,3)
    val nums2 = intArrayOf(2)

    printMedianOfTwoSortedArrays(nums1 = nums1, nums2 = nums2, method = "Kotlin collections") { pair ->
        findMedianWithCollections(pair.first, pair.second)
    }
    printMedianOfTwoSortedArrays(nums1 = nums1, nums2 = nums2, method = "Recursive Approach") { pair ->
        findMedianSortedArrays(pair.first, pair.second)
    }

}

fun printMedianOfTwoSortedArrays(nums1: IntArray, nums2: IntArray, method: String, block: (Pair<IntArray, IntArray>) -> Double) {
    val numbers = block(Pair(nums1, nums2))
    print("Median using $method method for numbers ")
    nums1.forEachIndexed { index, i ->
        print(i)
        if (index != nums1.lastIndex) {
            print(", ")
        } else {
            print(" and ")
        }
    }
    nums2.forEachIndexed { index, i ->
        print(i)
        if (index != nums2.lastIndex) {
            print(", ")
        }
    }
    println(" is $numbers")
}


fun findMedianWithCollections(nums1: IntArray, nums2: IntArray): Double {
    val nums = mutableListOf<Double>()
    nums1.forEach { nums.add(it.toDouble()) }
    nums2.forEach { nums.add(it.toDouble()) }
    return median(nums)
}

fun median(l: List<Double>) = l.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    var A = nums1
    var B = nums2
    var m = A.size
    var n = B.size
    if (m > n) { // to ensure m<=n
        val temp = A
        A = B
        B = temp
        val tmp = m
        m = n
        n = tmp
    }
    var iMin = 0
    var iMax = m
    val halfLen = (m + n + 1) / 2
    while (iMin <= iMax) {
        val i = (iMin + iMax) / 2
        val j = halfLen - i
        if (i < iMax && B[j - 1] > A[i]) {
            iMin = i + 1 // i is too small
        } else if (i > iMin && A[i - 1] > B[j]) {
            iMax = i - 1 // i is too big
        } else { // i is perfect
            var maxLeft = 0
            maxLeft = when {
                i == 0 -> B[j - 1]
                j == 0 -> A[i - 1]
                else -> Math.max(A[i - 1], B[j - 1])
            }
            if ((m + n) % 2 == 1) {
                return maxLeft.toDouble()
            }

            var minRight = 0
            minRight = when {
                i == m -> B[j]
                j == n -> A[i]
                else -> Math.min(B[j], A[i])
            }

            return (maxLeft + minRight) / 2.0
        }
    }
    return 0.0
}




