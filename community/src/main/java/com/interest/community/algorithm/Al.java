package com.interest.community.algorithm;

import java.util.*;

public class Al {
    public static void main(String[] args) {

    }

    int[] meno;
    int coinChange(int[] coins , int amount){
        meno = new int[amount +1];
        Arrays.fill(meno , -666);
        return dp(coins,amount);
    }

    private int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount == -1) return -1;
        if (meno[amount] != -666){
            return meno[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins){
            int subProblem = dp(coins, amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res,subProblem+1);
        }
        meno[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return meno[amount];
    }

    void levelTraverse(TreeNode root){
        if (root == null){
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int sz = q.size();
            for (int i=0;i<sz;i++){
                TreeNode cur = q.poll();
                if (cur.left != null){
                    q.offer(cur.left);
                }
                if (cur.right != null){
                    q.offer(cur.right);
                }
            }
        }
    }


    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root){
        maxDepth(root);
        return maxDiameter;
    }

    int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);

        int myMaxDepth = leftMax + rightMax;
        maxDiameter = Math.max(myMaxDepth,maxDiameter);

        return 1 + Math.max(leftMax,rightMax);
    }


    private static final Map<Character,Character> map =
            new HashMap<Character,Character>(){{
                put('}','{');put(']','['); put(')','('); put('?','?');
            }};
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1)
            return false;

        Deque<Character> stack = new LinkedList<Character>();
        for (Character ch : s.toCharArray()){
            //pop
            if (map.containsKey(ch)){
                if (stack.isEmpty() || stack.peek() != map.get(ch)){
                    return false;
                }
                stack.pop();
            }
            //push
            else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        else if (list2 == null){
            return list1;
        }
        else if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    int removeDuplicates(int[] num){
        if (num.length == 0){
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < num.length){
            if (num[fast] != num[slow]){
                slow++;
                num[slow] = num[fast];
            }

            fast++;
        }
        return slow+1;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return null;
        }
        if (head.next==null){
            return head;
        }
        ListNode slow=head;
        ListNode fast=head;
        while (fast != null){
            if (slow.val != fast.val){
                slow.next=fast;
                slow=slow.next;
            }
            fast=fast.next;
        }
        slow.next=null;
        return head;
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0){
            return 0;
        }
        int slow = 0;
        int fast= 0;
        while (fast < nums.length) {
            if (nums[fast] != val){
                slow++;
                if (nums[slow]==val){
                    nums[slow]=nums[fast];
                }
            }

            fast++;
        }
        return slow+1;

    }
}
