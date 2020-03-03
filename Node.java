public class Node {
  private Node left, right;
  private int key, height;

  public Node(int value, Node left, Node right) {
    this.key = value;
    this.left = left;
    this.right = right;
    this.height = 0;
  }
  
  public void setLeft(Node left){
    this.left = left;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public int getKey() {
    return this.key;
  }

  public int getHeight() {
    return this.height;
  }
}