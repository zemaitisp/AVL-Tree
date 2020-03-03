import java.util.*;
public class AVLTree {
  private Node root;

  public AVLTree() {
    this.root = null;
  }

  public AVLTree(Node root) {
    this.root = root;
  }

  private void balanceIter() {

  }

  private void setHeightsRec() {
    if(root != null){
      setHeightsRec(root);
    }
  }

  //will set the nodes height according to the current tree
  private void setHeightsRec(Node curr) {
    //if there is a left side set the current height to the child +1
    if(curr.getLeft() != null) {
      setHeightsRec(curr.getLeft());
      curr.setHeight(curr.getLeft().getHeight()+1);
    }
    //if there is a right side run down the tree
    if(curr.getRight() != null) {
      setHeightsRec(curr.getRight());
      //if the right side has more height than the left replace the height with the right side +1
      if(curr.getHeight() < curr.getRight().getHeight() +1) {
        curr.setHeight(curr.getRight().getHeight()+1);
      }
    }    
  }

  //should be given the node 1 above the rotation point
  private void rotLeft(Node n) {
    Node i = n;
    Node j = i.getRight();
    Node k = j.getLeft();
    //swap the two connections
    j.setLeft(i);
    i.setRight(k);
    //reset the heights
    j.setHeight(j.getHeight() + 1);
    i.setHeight(i.getHeight() - 1);
    //if the node being rotated is the root make the new root the right child
    if(i == root) {
      root = j;
    }
  }

  //should be given the 1 above rotaion point
  private void rotRight(Node n) {
    Node i = n;
    Node j = i.getLeft();
    Node k = j.getRight();
    //swap the two connections
    j.setRight(i);
    i.setLeft(k);
    //set the heights correctly
    j.setHeight(j.getHeight() + 1);
    i.setHeight(i.getHeight() - 1);
    //if the rotation point is the root set the left child as the root
    if(i == root) {
      root = j;
    }
  }

  private void setHeightsIter(){
    ArrayList<Node> arr = new ArrayList<Node>();
    Node curr = root;
    if(root = null) {
      return;
    }
   while(curr.getLeft() != null) {
     
   }
    
  }
  
  //will calculate the BF for a given node
  private int getBF(Node curr) {
    int left, right;
    if(curr.getLeft() != null){
      left = curr.getLeft().getHeight();
    }
    else {
      left = 0;
    }
    if(curr.getRight() != null) {
      right = curr.getRight().getHeight();
    }
    else {
      right = 0;
    }
    return left - right;
  }
}