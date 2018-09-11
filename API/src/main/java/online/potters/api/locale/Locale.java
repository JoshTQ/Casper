package online.potters.api.locale;

import online.potters.api.storage.YAMLStorage;

public interface Locale extends YAMLStorage {

	boolean loadFile();

	boolean saveFile();

}
