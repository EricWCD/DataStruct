/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BST;

/**
 *
 * @author nh228u27
 */
public class BST<E extends Comparable<E>> {
    
    private BTNode<E> root;  
		
    public BST(E data) {
        root = new BTNode(data, null, null);
    }
		
    public boolean findTarget (E data) {
        return findTarget(root, data);
    }
    
    private boolean findTarget(BTNode<E> current, E target) {
        if (current == null) return false;
        if (current.getData().compareTo(target) < 0)
            return findTarget(current.getRight(), target);
        if (current.getData().compareTo(target) > 0)
            return findTarget(current.getLeft(), target);
        // it's not null, it's not less, it's not more - must be it:
        return true;
    }
		
    public void insert (E data) {
        root = insert(root, data);
    }
		
    private BTNode<E> insert(BTNode<E> current, E data) {
        E d = current.getData();        
        if (data.compareTo(d)<=0) {
            if (current.getLeft() == null) 
                current.setLeft(new BTNode(data,null,null));
            else
                insert(current.getLeft(),data);
        }
        else {
            if (current.getRight() == null)
                current.setRight(new BTNode(data,null,null));
            else
                insert(current.getRight(), data);  
        }
        return current;
    } 
		
    // removal algorithm by Sedgewick & Wayne
    public void remove (E target) {
        root = remove(root, target);
    }
    public BTNode<E> remove (BTNode<E> current, E target) {
        if (current == null) return null;
        int cmp = current.getData().compareTo(target);
        if(cmp > 0) current.setLeft(remove(current.getLeft(), target));
        else if (cmp < 0)  current.setRight(remove(current.getRight(), target));
        else { // data is in current node
            if (current.getRight() == null) return current.getLeft();
            if (current.getLeft() == null) return current.getRight();
            BTNode<E> tmp = current;
            current = new BTNode(tmp.getRight().getLeftmostData(), null, null);
            BTNode<E> rc = tmp.getRight().removeLeftmost();
            current.setRight(rc);
            current.setLeft(tmp.getLeft());
        }
        return current;
    }
    public Object[] makeArray() {
        Object [] array;
        array = root.buildArray(root);
        return array;
    }
    public void print() {
        root.print(0);
    }

}
