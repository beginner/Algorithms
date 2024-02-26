package leetcode.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Easy
 * Problem : https://leetcode.com/problems/same-tree/description/
 */
public class SameTree_100 {

    /**
     * DFS approach
     * Time -> O(N) where N is number of nodes in the tree
     * Space -> O(N) for stack space for recursion of unbalanced tree
     */
    public boolean isSameTree__Recursive(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true;
       else if (p == null || q == null) return false;

        return p.val == q.val && isSameTree__Recursive(p.left, q.left) && isSameTree__Recursive(p.right, q.right);
    }

    /**
     * BFS approach
     * Time -> O(N) to traverse the entire tree
     * Space -> O(N) for the BFS queue length at last level since can have N nodes
     */
    public boolean isSameTree__Iterative(TreeNode p, TreeNode q) {
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[]{p, q});
        while (stack.size() > 0) {
            TreeNode[] pop = stack.pop();
            TreeNode first = pop[0];
            TreeNode second = pop[1];
            if (first!= null && second!= null) {
                if (first.val != second.val) { // Trees are un-equal if their value differs
                    return false;
                }
                stack.push(new TreeNode[]{first.left, second.left});
                stack.push(new TreeNode[]{first.right, second.right});

            } else if (first == null || second == null) { // If any one of the TreeNode is null but other is not null that would mean we have un-equal trees.
                return false;
            }
        }
        return true;
    }
}
