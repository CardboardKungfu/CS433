public class PriorityQueueElement<T> {

	int priority;
	T item;

	PriorityQueueElement(T item, int priority) {
		this.item = item;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public T getItem() {
		return item;
	}

	public String toString() {
		return "<" + item.toString() + ", " + priority + ">";
	}
}
