package fremad.domain.list;

import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.Lists;

public abstract class AbstractListWrapper<T> implements Iterable<T>{
	
	@JsonProperty("listObject")
	@JsonSerialize @JsonDeserialize
	protected List<T> list = Lists.newArrayList();

	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	public void add(T element){
		list.add(element);
	}
	
	public T get(int index){
		return list.get(index);
	}
	
	public List<T> getList(){
		return list;
	}
	
	public void addAll(Iterable<T> elements){
		if(elements != null){
			for (T element : elements){
				list.add(element);
			}
		}
	}
	
	public int size(){
		return list.size();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
}
