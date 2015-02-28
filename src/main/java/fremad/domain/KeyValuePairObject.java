package fremad.domain;

public class KeyValuePairObject<T,S> {
	private T key;
	private S value;
	
	public KeyValuePairObject() {
		super();
	}

	public KeyValuePairObject(T key, S value) {
		super();
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public S getValue() {
		return value;
	}

	public void setValue(S value) {
		this.value = value;
	}
	
	
}
