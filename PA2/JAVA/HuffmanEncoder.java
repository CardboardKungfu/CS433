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
	}

	private BinaryTreeNode buildTree() throws IndexOutOfBoundsException { // complete this function
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this function
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
