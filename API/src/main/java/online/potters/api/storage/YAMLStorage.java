package online.potters.api.storage;

import java.util.List;

public interface YAMLStorage {

	boolean loadFile();

	boolean saveFile();

	String getString(String address);

	List getList(String address);

	boolean getBoolean(String address);

	void set(String address, Object toSet);

}
