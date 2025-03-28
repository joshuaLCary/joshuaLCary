package avl;

public class AVL {

  public Node root;

  private int size;

  public int getSize() {
    return size;
  }
/**
 * Author: Joshua L. Cary
 * Date: 2/20/2024
 * Code uses a BST as a base for an AVL tree,
 * and is able to copare words to other words.
 * Words of lower placement in the alphabet are less than higher alphabetical letters.
 * 
 */



  /**
   * find w in the tree. return the node containing w or
   * null if not found
   */
  public Node search(String w) {
    return search(root, w);
  }

  private Node search(Node n, String w) {
    if (n == null) {
      return null;
    }
    if (w.equals(n.word)) {
      return n;
    } else if (w.compareTo(n.word) < 0) {
      return search(n.left, w);
    } else {
      return search(n.right, w);
    }
  }

  /** insert w into the tree as a standard BST, ignoring balance */
  public void bstInsert(String w) {
    if (root == null) {
      root = new Node(w);
      size = 1;
      root.height = 0; // Added this
      return;
    }
    bstInsert(root, w);
  }

  /*
   * insert w into the tree rooted at n, ignoring balance
   * pre: n is not null
   */
  private void bstInsert(Node n, String w) {
    if (w.equals(n.word)) {
      return;
    }
    // When w is less than n.word, code checks if n.left is empty
    else if (w.compareTo(n.word) < 0) {
      // If empty, input node, update heights accordingly
      if (n.left == null) {
        n.left = new Node(w);
        n.left.parent = n;
        size++;
        n.left.height = 0;
        heightUpdate(n);
        return;
      }
      // Otherwise, if n.left is full, run input again for n.left.
      bstInsert(n.left, w);
    }
    // If w is greater than n.word, check right node.
    else {
    // If empty, input node, update heights accordingly
      if (n.right == null) {
        n.right = new Node(w);
        n.right.parent = n;
        size++;
        n.right.height = 0;
        heightUpdate(n);
        return;
      }
      // Otherwise, if n.right is full, run input again for n.right
      bstInsert(n.right, w);
    }
    // Update, just to make sure at the end.
    heightUpdate(n);
  }
  /**
  * heightUpdate  checks all variations of nodes being full or not.
  * Then calculates height of current node. 
  */
  // Precondition: Child heights are correct
  private void heightUpdate(Node n){
    /**
     * heightUpdate  checks all variations of nodes being full or not.
     * Then calculates height of current node. 
     */
    if (n.left != null && n.right != null){
      if (n.left.height > n.right.height){
        n.height = n.left.height + 1;
      }
      else{
        n.height = n.right.height + 1;
      }
    }
    else if (n.left == null && n.right == null) {
      n.height = 0;
    }
    else if(n.left == null){
      n.height = n.right.height + 1;
    }
    else{
      n.height = n.left.height + 1;
    }

  }

  /**
   * insert w into the tree, maintaining AVL balance
   * precondition: the tree is AVL balanced and any prior insertions have been
   * performed by this method.
   */
  public void avlInsert(String w) { // Not finished
    if (root == null) {
      root = new Node(w);
      size = 1;
      return;
    }
    avlInsert(root, w);
  }

  /*
   * insert w into the tree, maintaining AVL balance
   * precondition: the tree is AVL balanced and n is not null
   */

   // Exactly the same as bst but rebalnces at the bottom
  private void avlInsert(Node n, String w) {
    if (w.equals(n.word)) {
      return;
    }

    else if (w.compareTo(n.word) < 0) {
      if (n.left == null) {
        n.left = new Node(w);
        n.left.parent = n;
        size++;
        n.left.height = 0;
        heightUpdate(n);
        return;
      }
      avlInsert(n.left, w);
    }

    else {
      if (n.right == null) {
        n.right = new Node(w);
        n.right.parent = n;
        size++;
        n.right.height = 0;
        heightUpdate(n);
        return;
      }
      avlInsert(n.right, w);
    }
    heightUpdate(n);
    rebalance(n);
  }

  /**
   * do a left rotation: rotate on the edge from x to its right child.
   * precondition: x has a non-null right child
   */
  public void leftRotate(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left != null){
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null){
      root = y;
    }
    else if (x == x.parent.left){
      x.parent.left = y;
    }
    else{
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;

    heightUpdate(x);
    heightUpdate(y);
  }

  /**
   * do a right rotation: rotate on the edge from x to its left child.
   * precondition: y has a non-null left child
   */
  public void rightRotate(Node y) {
    Node x = y.left;
    y.left = x.right;
    if (x.right != null){
      x.right.parent = y;
    }
    x.parent = y.parent;
    if (y.parent == null){
      root = x;
    }
    else if (y == y.parent.right){
      y.parent.right = x;
    }
    else{
      y.parent.left = x;
    }
    x.right = y;
    y.parent = x;

    heightUpdate(y);
    heightUpdate(x);

  }

  /**
   * rebalance a node N after a potentially AVL-violoting insertion.
   * precondition: none of n's descendants violates the AVL property
   */
  public void rebalance(Node n) {
    // Cases of rebalace
    if (n.balanceFactor() < -1){
      if ( n.left.balanceFactor() < 0){
        // Case 1
        rightRotate(n);
      }
      else{
        // Case 2
        leftRotate(n.left);
        rightRotate(n);
      }
    }
    else if (n.balanceFactor() > 1){
      if ( n.right.balanceFactor() > 0){
        // Case 3
        leftRotate(n);
      }
      else{
        // Case 4
        rightRotate(n.right);
        leftRotate(n);
      }
    }
  }

  // Helper Method for Balance Factor
  // Check for nulls


  /** remove the word w from the tree */
  public void remove(String w) {
    remove(root, w);
  }

  /* remove w from the tree rooted at n */
  private void remove(Node n, String w) {
    return; // (enhancement TODO - do the base assignment first)
  }

  /**
   * print a sideways representation of the tree - root at left,
   * right is up, left is down.
   */
  public void printTree() {
    printSubtree(root, 0);
  }

  private void printSubtree(Node n, int level) {
    if (n == null) {
      return;
    }
    printSubtree(n.right, level + 1);
    for (int i = 0; i < level; i++) {
      System.out.print("        ");
    }
    System.out.println(n);
    printSubtree(n.left, level + 1);
  }

  /** inner class representing a node in the tree. */
  public class Node {
    public String word;
    public Node parent;
    public Node left;
    public Node right;
    public int height;

    public String toString() {
      return word + "(" + height + ")";
    }

    /** constructor: gives default values to all fields */
    public Node() {
    }
    
    /** constructor: sets only word */
    public Node(String w) {
      word = w;
    }

    /** constructor: sets word and parent fields */
    public Node(String w, Node p) {
      word = w;
      parent = p;
    }

    /** constructor: sets all fields */
    public Node(String w, Node p, Node l, Node r) {
      word = w;
      parent = p;
      left = l;
      right = r;
    }

    // Helper Method: Balance Factor value
    public int balanceFactor(){
      if (left == null && right == null){
        return 0;
      }
      else if (right == null){
        return -1 - left.height;
      }
      else if(left == null){
      return right.height + 1;
      }
      return right.height - left.height;
    }
  }
}
