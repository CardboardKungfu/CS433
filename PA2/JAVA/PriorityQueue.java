import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class PriorityQueue<T> {

	private List<PriorityQueueElement<T>> contents;
	private int currentSize = 0;
	private Hashtable<T, Integer> itemToHeapIndex;

	public PriorityQueue() {
		contents = new ArrayList<PriorityQueueElement<T>>();
		itemToHeapIndex = new Hashtable<T, Integer>();
	}

	public T getMinimumItem() {
		return contents.get(0).item;
	}

	public int getMinimumPriority() {
		return contents.get(0).priority;
	}

	public int getSize() {
		return currentSize;
	}

	public void deleteMinimum() throws IndexOutOfBoundsException {

		if (currentSize == 0)
			throw new IndexOutOfBoundsException();
		else if (currentSize == 1) {
			itemToHeapIndex.remove(contents.get(0).item);
			currentSize--;
			contents.remove(currentSize);
			return;
		}

		itemToHeapIndex.remove(contents.get(0).item);
		itemToHeapIndex.remove(contents.get(--currentSize).item);
		contents.set(0, contents.get(currentSize));
		contents.remove(currentSize);
		itemToHeapIndex.put(contents.get(0).item, 0);
		siftDown(0);
	}

	public void setPriority(T item, int priority) {
		if (!itemToHeapIndex.containsKey(item))
			insert(item, priority);
		else {
			int itemIndex = itemToHeapIndex.get(item);
			if (contents.get(itemIndex).priority == priority)
				return;
			if (contents.get(itemIndex).priority > priority) {
				contents.get(itemIndex).priority = priority;
				siftUp(itemIndex);
			} else {
				contents.get(itemIndex).priority = priority;
				siftDown(itemIndex);
			}
		}
	}

	private void insert(T item, int priority) {

		int index = currentSize++;
		contents.add(new PriorityQueueElement<T>(item, priority));
		itemToHeapIndex.put(item, index);
		siftUp(index);
	}

	private void siftDown(int index) {
		int leftIndex = index * 2 + 1, rightIndex = leftIndex + 1;
		while (leftIndex < currentSize) {
			int leftValue = contents.get(leftIndex).priority;
			if (rightIndex < currentSize) {
				int rightValue = contents.get(rightIndex).priority;
				if (leftValue < rightValue && leftValue < contents.get(index).priority) {
					swap(index, leftIndex);
					index = leftIndex;
				} else if (rightValue < contents.get(index).priority) {
					swap(index, rightIndex);
					index = rightIndex;
				} else
					break;
			} else if (leftValue < contents.get(index).priority) {
				swap(index, leftIndex);
				index = leftIndex;
			} else
				break;
			leftIndex = index * 2 + 1;
			rightIndex = leftIndex + 1;
		}
	}

	private void siftUp(int index) {
		while (index > 0 && contents.get(parent(index)).priority > contents.get(index).priority) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	private void swap(int index1, int index2) {
		itemToHeapIndex.remove(contents.get(index1).item);
		itemToHeapIndex.remove(contents.get(index2).item);

		PriorityQueueElement<T> temp = contents.get(index1);
		contents.set(index1, contents.get(index2));
		contents.set(index2, temp);

		itemToHeapIndex.put(contents.get(index1).item, index1);
		itemToHeapIndex.put(contents.get(index2).item, index2);
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	public String toString() {
		if (0 == currentSize)
			return "[]";
		String str = "[";
		for (int i = 0; i < currentSize - 1; i++) {
			str += contents.get(i) + "; ";
		}
		str += contents.get(currentSize - 1) + "]";
		return str;
	}
}
