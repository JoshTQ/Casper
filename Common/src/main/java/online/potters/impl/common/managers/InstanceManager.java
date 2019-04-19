package online.potters.impl.common.managers;

import online.potters.api.utils.Callback;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

public class InstanceManager<T>  {

	private HashSet<T> CACHED_INSTANCES;

	public InstanceManager() {
		CACHED_INSTANCES = new HashSet<>();
	}

	public void cacheObject(T object) {
		this.CACHED_INSTANCES.add(object);
	}

	public void cacheAllObjects(Collection<T> objects) {
		this.CACHED_INSTANCES.addAll(objects);
	}

	public boolean containsCachedObject(T object) {
		return this.CACHED_INSTANCES.contains(object);
	}

	public void uncacheObject(T object) {
		this.CACHED_INSTANCES.remove(object);
	}

	public void uncacheObjects(Predicate<T> filter) {
		this.CACHED_INSTANCES.removeIf(filter);
	}

	public Optional<T> getCachedObject(Predicate<T> filter) {
		return this.CACHED_INSTANCES.stream().filter(filter).findFirst();
	}

	public void shutdown() {
		this.CACHED_INSTANCES.clear();
		this.CACHED_INSTANCES = null;
	}

	public void initObjects(Callback<T> callable) {
		this.CACHED_INSTANCES.forEach(callable::run);
	}
}