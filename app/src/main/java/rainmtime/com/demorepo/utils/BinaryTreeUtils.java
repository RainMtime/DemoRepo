package rainmtime.com.demorepo.utils;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by chunyu on 2019/2/18 10:10 AM.
 * Email: 746431278@qq.com
 */
public class BinaryTreeUtils {

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;
    }

    //计算节点个数
    public int getNodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getNodeNum(root.left) + getNodeNum(root.right) + 1;
    }

    public int getNodeNum2(TreeNode root) {
        int num = 0;
        if (root == null) {
            num = 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            num++;
            TreeNode node = queue.peek();
            queue.remove();

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return num;
    }

    //获取二叉树高度
    public int getHeight(TreeNode root) {
        int height = 0;

        if (root == null) {
            return height;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currentLevelNum = 1;
        while (!queue.isEmpty()) {
            int childSize = 0;
            while (currentLevelNum > 0) {
                TreeNode node = queue.peek();

                if (node.left != null) {
                    queue.add(node.left);
                    childSize++;
                }

                if (node.right != null) {
                    queue.add(node.right);
                    childSize++;
                }
                currentLevelNum--;
                queue.remove();
            }
            currentLevelNum = childSize;

            height++;
        }
        return height;
    }

    public void traverseTree(TreeNode node) {

        if (node != null) {
            Log.i("chunyu-test", node.value + "");
            if (node.left != null) {
                traverseTree(node.left);
            }

            if (node.right != null) {
                traverseTree(node.right);
            }

        }
    }


    public TreeNode binaryTreeSearch(TreeNode node, int targetValue) {

        if (node != null) {
            if (node.value == targetValue) {
                return node;
            }

            if (node.value > targetValue) {
                binaryTreeSearch(node.left, targetValue);
            }

            if (node.value < targetValue) {
                binaryTreeSearch(node.right, targetValue);
            }
        }
        return null;
    }

    public int binaryChildNumOnKLevel(TreeNode root) {
        int height = 0;

        if (root == null) {
            return height;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currentLevelNum = 1;
        while (!queue.isEmpty()) {
            int childSize = 0;
            while (currentLevelNum > 0) {
                TreeNode node = queue.peek();

                if (node.left != null) {
                    queue.add(node.left);
                    childSize++;
                }

                if (node.right != null) {
                    queue.add(node.right);
                    childSize++;
                }
                currentLevelNum--;
                queue.remove();
            }
            height++;
            Log.i("chunyu-test", "Level :" + height + " childNum:" + currentLevelNum);
            currentLevelNum = childSize;

        }
        return height;

    }

    public boolean isSameBinaryTree(TreeNode root1, TreeNode root2) {
        if ((root1 == null && root2 == null)) {
            return true;
        }

        if (root1 == null && root2 != null) {
            return false;
        }

        if (root1 != null && root2 == null) {
            return false;
        }


        boolean leftResult = isSameBinaryTree(root1.left, root2.left);
        boolean rightResult = isSameBinaryTree(root1.right, root2.right);
        return (leftResult && rightResult);
    }

    public boolean isAVL(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftResult = isAVL(root.left);
        boolean rightResult = isAVL(root.right);

        return leftResult && rightResult && Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1;
    }

    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int currentSize = 1;
        while (!queue.isEmpty()) {
            int childSize = 0;
            while (currentSize < 1) {

                TreeNode node = queue.peek();
                queue.remove();

                if (node.left != null) {
                    queue.add(node.left);
                    childSize++;
                }

                if (node.right != null) {
                    queue.add(node.right);
                    childSize++;
                }

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                currentSize--;
            }
            currentSize = childSize;
        }
    }


}
