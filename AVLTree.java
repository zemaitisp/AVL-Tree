import java.util.*;
public class AVLTree {
  private Node root;

  public AVLTree() {
    this.root = null;
  }

  public AVLTree(Node root) {
    this.root = root;
  }

  public void insertIter(int toInsert) {
      if(root == null) {
        root = new Node(toInsert, null, null);
        return;
      }
      Node curr = root;
      ArrayList<Node> ancestory = new ArrayList<Node>();
      //if the current node is not empty, broken go on
      //if the current node is equal to the new value break because assumed no duplicates
      while(curr != null && curr.getKey() != toInsert) {
        ancestory.add(curr);
        if(curr.getKey() > toInsert) {
          //if the value to insert is less than the current node and the left child is empty insert
          if(curr.getLeft() == null) {
            curr.setLeft(new Node(toInsert, null, null));
            break;
          }
          //else get the left node to compare
          curr = curr.getLeft();
        }
        else {
          //if the value to insert is greater than the current node and the right child is empty insert
          if(curr.getRight() == null) {
            curr.setRight(new Node(toInsert, null, null));
            break;
          }
          //else get the right child to compare
          curr = curr.getRight();
        }
      }
    setHeightsIter(ancestory);
    for(int i = ancestory.size()-1; i >= 0; i--) {
      if(Math.abs(getBF(ancestory.get(i))) >= 2){
        balanceIter(ancestory);
        break;
      }
    }
    }

    public void removeIter(int toRemove) {
    if(root == null) {
      return;
    }
    if(root.getKey() == toRemove) {
      root = null;
    }
    Node curr = root;
    ArrayList<Node> ancestory = new ArrayList<Node>();
    while(curr.getLeft().getKey() != toRemove && curr.getRight().getKey() != toRemove) {
      if(curr.getKey() > toRemove) {
        curr = curr.getLeft();
      }
      else {
        curr = curr.getRight();
      }
    }
    ancestory.add(curr);
    Node temp;
    Node remove;
    int toReplace;
    //could probably make this a private method to remove clutter, but java makes copies of the obj so it might make a mess 
    if(curr.getLeft().getKey() == toRemove){
      temp = curr.getLeft();
      //if both children are null just remove the element
      if(temp.getLeft() == null && temp.getRight() == null) {
        curr.setLeft(null);
      }
      //if the left child is null make the removed element the right node with its children
      else if(temp.getLeft() == null) {
            curr.setLeft(temp.getRight());
          }
      //if the right child is null make the removed element the left node with its children
            else if(temp.getRight() == null) {
              curr.setLeft(temp.getLeft());
            }
              else {
                remove = temp.getRight();
                while(remove.getLeft() != null) {
                  remove = remove.getLeft();
                }
                toReplace = remove.getLeft().getKey();
                remove.setLeft(null);
                curr.getLeft().setKey(toReplace);
            }
    }
    else{
      temp = curr.getRight();
      //if both children are null just remove the element
      if(temp.getLeft() == null && temp.getRight() == null) {
        curr.setRight(null);
      }
      //if the left child is null make the removed element the right node with its children
      else if(temp.getLeft() == null) {
            curr.setRight(temp.getRight());
          }
      //if the right child is null make the removed element the left node with its children
            else if(temp.getRight() == null) {
              curr.setRight(temp.getLeft());
            }
              else {
                remove = temp.getRight();
                while(remove.getLeft() != null) {
                  remove = remove.getLeft();
                }
                toReplace = remove.getLeft().getKey();
                remove.setLeft(null);
                curr.getRight().setKey(toReplace);
            }
    }
    setHeightsIter(ancestory);
    for(int i = ancestory.size()-1; i >= 0; i--) {
      if(Math.abs(getBF(ancestory.get(i))) >= 2){
        balanceIter(ancestory);
        break;
      }
    }
  }

  public Node getMaxIter(Node curr){
    while(curr.getRight() != null){
      curr = curr.getRight();
    }
    return curr;
  }

  public Node getMinIter(Node curr) {
    while(curr.getLeft() != null) {
      curr = curr.getLeft();
    }
    return curr;
  }


  private void balanceIter(ArrayList<Node> l) {
    if(l.size() == 0){return;}
    Node curr = null;
    int currBF = 0, prevBF = 0;
    for(int i = l.size() - 1; i >= 0; i--) {
      curr = l.get(i);
      prevBF = currBF;
      currBF = getBF(curr);
      //incase of floating point errors
      if(Math.abs(currBF) >= 2 - .001) {
        break;
      }
    }
    if(currBF == -2) {
      if(prevBF == -1) {
        rotLeft(curr);
      }
      else {
        rotRight(curr.getRight());
        rotLeft(curr.getLeft());
      }
    }
    else {
      if(prevBF == 1) {
        rotRight(curr);
      }
      else {
        rotLeft(curr.getLeft());
        rotRight(curr);
      }
    }

  }

  private void setHeightsIter(ArrayList<Node> l) {
    Node curr;
    boolean isLeft;
   for(int i = l.size() -2; i >= 0; i--) {
    curr = l.get(i);
    curr.setHeight(l.get(i+1).getHeight()+1);
    if(curr.getRight() == l.get(i+1)) {
      isLeft = false;
    }
    else {
      isLeft = true;
    }

    if(isLeft) {
      if(curr.getHeight() < curr.getLeft().getHeight()-1) {
        curr.setHeight(curr.getLeft().getHeight()+1);
      }
    }
    else {
      if(curr.getHeight() < curr.getRight().getHeight() - 1) {
        curr.setHeight(curr.getRight().getHeight() + 1);
      }
    }
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

  public int[] getRandomArray(int n) {
    Random r = new Random();
    int[] arr = new int[n];
    for(int i = 0; i < n; i++ )  {
      arr[i] = r.nextInt();
      //System.out.println(arr[i]);
      //will scan the filled array
      for(int j = n-1; j > i; j-- ) {
        //if found decrement i and make it overwrite the value it just generated with a new value
          if(arr[i] == arr[j]) {
            i--;
          }
        }
      }
    return arr;
  }
}