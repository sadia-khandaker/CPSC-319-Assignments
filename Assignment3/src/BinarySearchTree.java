import java.util.NoSuchElementException;

/**
 * Binary Search Tree Class
 * --------------------------------------------------------------------------------------------------------------------
 * Code Adapted From:
 * --------------------------------------------------------------------------------------------------------------------
 * Title: BinarySearchTree1.java
 * Author: Md Shopon
 * --------------------------------------------------------------------------------------------------------------------
 * @param <T>
 */

class TreeNode<T extends Comparable<T>> {
    T data;
    int size;
    TreeNode<T> left; // Left Child
    TreeNode<T> right; // Right Child

    public TreeNode(T data) {
        this.data = data;
    }
}


public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root; // Root of the tree

    /**
     * Checks for an empty binary search tree
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Computes the number of items within the tree
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    /**
     * Adds a value to the binary search tree
     * @param data
     */
    public void add(T data) {
        root = add(data,root);
    }

    private TreeNode<T> add(T value, TreeNode<T> node) {
        if (node == null) {
            return new TreeNode<T> (value);
        }
        if (value.compareTo(node.data) == 0) {
            node.data = value;
        } else {
            if (value.compareTo(node.data) < 0) {
                node.left = add(value, node.left);
            }
            else {
                node.right = add(value, node.right);
            }
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * Removes the smallest item
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);

    }

    private TreeNode<T> deleteMin(TreeNode<T> node) {
        if (node.left != null) {
            node.left = deleteMin (node.left);
            node.size = size (node.left) + size (node.right) + 1;
            return node;
        } else {
            return node.right;
        }
    }

    /**
     * Delete the specified node and its associated value from this tree
     * @param data
     */
    public void delete(T data) {
        if (data != null) {
            root = delete (root, data);
        } else {
            throw new IllegalArgumentException ();
        }
    }

    private TreeNode<T> delete(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left  = delete(node.left,  data);
        } else if (compare > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left  == null) {
                return node.right;
            }
            TreeNode<T> temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Returns the smallest data
     * @return
     */
    public T min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return min(root).data;
    }

    private TreeNode<T> min(TreeNode<T> node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }


    /**
     * Performs level-order transversal in binary search tree
     * @return
     */
    public String levelOrder() {
        String output = "";
        Queue<TreeNode<T>> queue = new Queue<> ();
        TreeNode<T> temp = root;
        queue.enqueue(temp);

        while (!queue.isEmpty()) {
            temp = queue.dequeue();
            output += temp.data.toString() + "\n";
            if (temp.left != null) {
                queue.enqueue(temp.left);
            }
            if (temp.right != null) {
                queue.enqueue(temp.right);
            }
        }

        return output.substring(0, output.length() - 1);
    }
    /**
     * Performs in-order transversal
     * @return
     */
    public String inOrder() {
        String output = inOrder(root);
        return output.substring(0, output.length() - 1);
    }
    /**
     * Recursive Method to perform in-order transversal
     * @param node
     * @return
     */
    public String inOrder(TreeNode<T> node) {
        String output = "";
        if (node != null) {
            output += inOrder (node.left);
            output += node.data.toString() + "\n";
            output += inOrder (node.right);
        }
        return output;
    }


}
