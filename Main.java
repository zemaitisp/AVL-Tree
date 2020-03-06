import java.util.*;
class Main {
  public static void main(String[] args) {
    AVLTree a = new AVLTree();
    int[] nums = a.getRandomArray(1000);
    for(int i = 0; i < 10; i++) {
      a.insertIter(nums[i]);
    }
  }
}