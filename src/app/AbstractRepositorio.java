package app;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepositorio<E, ID>  {

	private Map<ID, E> mapa = new LinkedHashMap<ID, E>();

	public E get(ID id) {
		return mapa.get(id);
	}

	public void add(ID id, E e) {
		mapa.put(id, e);
	}
	public void remove(ID id) {
		mapa.remove(id);
	}
	public void update(ID id, E e) {
		mapa.replace(id, e);
	}
	public List<E> getAll(){
		return new LinkedList<E>(mapa.values());
	}
}
