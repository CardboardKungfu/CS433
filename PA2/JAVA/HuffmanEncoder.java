import java.util.Hashtable;

public class HuffmanEncoder {

	private char alphabet[];
	private int frequencies[];
	private int sigma;

	private int encodingLength;
	private int tableSize;
	private Hashtable<Character, String> charToEncodingMapping;

	public HuffmanEncoder(char alphabet[], int frequencies[], int sigma) {
		this.alphabet = alphabet;
		this.sigma = sigma;
		this.frequencies = frequencies;
		encodingLength = tableSize = 0;
		charToEncodingMapping = new Hashtable<Character, String>();
		encode();
	}

	private void encode() throws IndexOutOfBoundsException { // complete this function
	BinaryTreeNode root = buildTree();
	createTable(root, "");
	for (int i = 0; i<sigma; i++) {
		char c = alphabet[i];
		String str = getEncoding(c);
		encodingLength = encodingLength + frequencies[i] * str.length();
		tableSize = tableSize + str.length() + 8;
	}
	}

	private BinaryTreeNode buildTree() throws IndexOutOfBoundsException { // complete this function
	PriorityQueue<BinaryTreeNode>pq = new PriorityQueue<BinaryTreeNode>();
	
	for (int i = 0; i<sigma; i++) {
	
		BinaryTreeNode x = new BinaryTreeNode(alphabet[i], frequencies[i]);
		pq.setPriority(x,frequencies[i]);
		
	}
	while (pq.getSize()>1) {
		BinaryTreeNode min = pq.getMinimumItem();
		pq.deleteMinimum();
		BinaryTreeNode secondMin = pq.getMinimumItem();
		pq.deleteMinimum();
		BinaryTreeNode y = new BinaryTreeNode('\0', min.value+secondMin.value);
		y.left = min;
		y.right = secondMin;
		pq.setPriority(y, y.value);
	}
	return pq.getMinimumItem();
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this function
	if (node.right == null && node.left == null) {
		charToEncodingMapping.put(node.character,  encoding);
	}
	else {
		if (node.left != null) {
			createTable(node.left, encoding+"0");
		}
		if (node.right != null) {
			createTable(node.right, encoding+"1");
		}
	}
	}

	public String getEncoding(char c) {
		return charToEncodingMapping.get(c);
	}

	public int getSigma() {
		return sigma;
	}

	public int[] getFrequencies() {
		return frequencies;
	}

	public char[] getAlphabet() {
		return alphabet;
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getEncodingLength() {
		return encodingLength;
	}
}
