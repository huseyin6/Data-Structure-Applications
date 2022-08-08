package CENG112_HW4_G32;

public class BinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTreeInterface<T> {
	
	private BinaryNode<T> root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public BinarySearchTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}
	
	public BinarySearchTree(T rootData, BinarySearchTree<T> leftTree, BinarySearchTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	
	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}
	
	public void setTree(T rootData, BinarySearchTree<T> leftTree, BinarySearchTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	
	private void privateSetTree(T rootData, BinarySearchTree<T> leftChild, BinarySearchTree<T> rightChild) {
		root = new BinaryNode<>(rootData);
		if(leftChild != null)
			root.setLeftChild(leftChild.root);
		if(rightChild != null)
			root.setLeftChild(rightChild.root);
	}
	
	@Override
	public T getRootData() {
		T dataOfRoot = null;
		if(root != null)
			dataOfRoot = root.getData();
		return dataOfRoot;
	}
	
	@Override
	public BinaryNode<T> getRoot(){
		return root;
	}
	
	public void setRoot(BinaryNode<T> root) {
		this.root = root;
	}
	
	@Override
	public T getEntry(T entry) {
		return findEntry(root, entry);
	}
	
	private T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if(rootNode != null) {
			T rootData = rootNode.getData();
			if(entry.equals(rootData))
				result = rootData;
			else if(entry.compareTo(rootData) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		}
		return result;
	}
	
	@Override
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}

	@Override
	public T add(T entry) {
		T result = null;
		if (isEmpty())
			setRoot(new BinaryNode<>(entry));
		else
			result = addEntry(getRoot(),entry);
		return result;
	}
	
	private T addEntry(BinaryNode<T> node,T entry) {
		assert node != null;
		T result = null;
		int comparison = entry.compareTo(node.getData());
		
		if (comparison == 0) {
			result = node.getData();
			node.setData(entry);
		}else if (comparison < 0) {
			if(node.hasLeftChild())
				result = addEntry(node.getLeftChild(), entry);
			else
				node.setLeftChild(new BinaryNode<>(entry));
		}else {
			assert comparison > 0;
			if(node.hasRightChild())
				result = addEntry(node.getRightChild(), entry);
			else
				node.setRightChild(new BinaryNode<>(entry));
		}
		return result;
	}
	
	public BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if (comparison == 0) {
				rootNode = removeFromRoot(rootNode);
			}else if (comparison < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry);
				rootNode.setLeftChild(subtreeRoot);
			}else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry));
			}
		}
		return rootNode;
	}
	
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		}else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();
		}else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}
	
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			rootNode = findLargest(rootNode.getRightChild());
		}
		return rootNode;
	}
	
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		}else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public void inorderTraverse() {
		inorderTraverse(root);
	}
	
	private void inorderTraverse(BinaryNode<T> node) {
		if(node != null) {
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}

}
