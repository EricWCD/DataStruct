package song;

import java.util.*;

/**
 *
 * @author Michael Main 
 */
public class BTNode<E> {
     
   private E data;
   private BTNode<E> left, right;   

   public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight) {
      data = initialData;
      left = initialLeft;
      right = initialRight;
   }       

   public E getData( ) {
      return data;
   }

   public BTNode<E> getLeft( ) {
      return left;                                               
   } 
   
 
   public E getLeftmostData( ) {
      if (left == null)
         return data;
      else
         return left.getLeftmostData( );
   }
      
   public BTNode<E> getRight( ) {
      return right;                                               
   } 

   public E getRightmostData( ) {
      if (left == null)
         return data;
      else
         return left.getRightmostData( );
   }

	 
	 
   public void inorderPrint( ) {
      if (left != null)
         left.inorderPrint( );
      System.out.println(data);
      if (right != null)
         right.inorderPrint( );
   }  

   public boolean isLeaf( ) {
      return (left == null) && (right == null);
   } 

   public void preorderPrint( ) {
      System.out.println(data);
      if (left != null)
         left.preorderPrint( );
      if (right != null)
         right.preorderPrint( );
   } 
   
   public void postorderPrint( ) {
      if (left != null)
         left.postorderPrint( );
      if (right != null)
         right.postorderPrint( );
      System.out.println(data);
   }   

   public void print(int depth) {
      int i;
      // Print left subtree (or LL if there is a right child and no left)   
      if (left != null)
         left.print(depth+1);
      else if (right != null){
         for (i = 1; i <= depth+1; i++)
            System.out.print("    ");
         System.out.println("LL");
      }
     // Print the indentation and the data from the current node:
      for (i = 1; i <= depth; i++)
         System.out.print("    ");
      System.out.println(data);
      // Print right subtree (or RR if there is a left child and no right)  
      if (right != null)
         right.print(depth+1);
      else if (left != null) {
         for (i = 1; i <= depth+1; i++)
            System.out.print("    ");
         System.out.println("RR");
      }
   }
   
   public BTNode<E> removeLeftmost( ) {
      if (left == null) {
          return right;
      }
      else {
         left = left.removeLeftmost( );
         return this;
      }
   }

   public BTNode<E> removeRightmost( ) {
      if (right == null) {
          return left;
      }
      else {
         right = right.removeRightmost( );
         return this;
      }
   }

   public void setData(E newData) {
      data = newData;
   }                                                               

   public void setLeft(BTNode<E> newLeft) {                    
      left = newLeft;
   }
 
   public void setRight(BTNode<E> newRight) {                    
      right = newRight;
   }  

   public static <E> BTNode<E> treeCopy(BTNode<E> source) {
        BTNode<E> leftCopy;
        BTNode<E> rightCopy;
      if (source == null)
         return null;
      else {
         leftCopy = treeCopy(source.left);
         rightCopy = treeCopy(source.right);
         return new BTNode<E>(source.data, leftCopy, rightCopy);
      }
   }

   public static <E> long treeSize(BTNode<E> root) {
      if (root == null)
         return 0;
      else
         return 1 + treeSize(root.left) + treeSize(root.right);
   }
   
	 
	 
	 // code added by Cate: builds array from data found in tree
    public Object [] buildArray(BTNode<E> root) {
        Stack<BTNode<E>> parents = new Stack<BTNode<E>>();
        int size = (int)treeSize(root);
        int ct=0;
        Object []array =  new Object[size];
        BTNode<E> current = root;
        Object data;
        // perform (non-recursive) preorder traversal, place elements in array;
				// code is based on solution found at 
				// http://www.codeguru.com/forum/showthread.php?t=401922, 
				// attributed there to "nitiraj"
       while (true) {
            while (current != null) {
                data = current.getData();
                array[ct] = data;
                ct++;   
                parents.push(current);
                current = current.getLeft();
            }
            if(!parents.empty()) {
                current = parents.pop();
                current = current.getRight();
            }
            else
                break;
        } // ends loop              
        return array;
    } // ends method 
}
