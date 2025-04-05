package ConfigFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

		public String dataFromProperties(String key) throws IOException {
			FileInputStream fis = new FileInputStream("E:\\Selenium web driver-utlities\\AppiumPractice\\src\\main\\resources\\CommonData");
			Properties p = new Properties();
			p.load(fis);
			String value = p.getProperty(key);
			return value;
		}
	}
