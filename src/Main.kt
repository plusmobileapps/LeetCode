
/**
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */

class ListNode(var value: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        var result = "ListNode: "
        var nextNode: ListNode? = this
        while (nextNode != null) {
            result += nextNode.value
            nextNode = nextNode.next
        }
        return result
    }
}

fun main(args: Array<String>) {
    val listNode1 = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(3)
        }
    }
    val listNode2 = ListNode(5).apply {
        next = ListNode(6).apply {
            next = ListNode(4)
        }
    }

    val sum = addTwoNumbers(listNode1, listNode2)

    print("The sum of $listNode1 and $listNode2 is $sum")


}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummyHead = ListNode(0)
    var p = l1
    var q = l2
    var curr: ListNode? = dummyHead
    var carry = 0
    while (p != null || q != null) {
        val x = p?.value ?: 0
        val y = q?.value ?: 0
        val sum = carry + x + y
        carry = sum / 10
        curr!!.next = ListNode(sum % 10)
        curr = curr.next
        p?.let { p = it.next }
        q?.let { q = it.next }
    }
    if (carry > 0) {
        curr!!.next = ListNode(carry)
    }
    return dummyHead.next
}



