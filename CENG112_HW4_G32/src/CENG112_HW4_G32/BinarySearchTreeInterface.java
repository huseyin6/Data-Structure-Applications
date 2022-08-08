package CENG112_HW4_G32;

public interface BinarySearchTreeInterface<T extends Comparable<? super T>> {
	public void setTree(T rootData);
	public void setTree(T rootData, BinarySearchTree<T> leftTree,BinarySearchTree<T> rightTree);
	public T getRootData();
	public boolean contains(T entry);
	public T getEntry(T entry);
	public T add(T entry);
	public BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry);
	public boolean isEmpty();
	public void clear();
	public void inorderTraverse();
	public BinaryNode<T> getRoot();
}
