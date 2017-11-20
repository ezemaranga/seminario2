import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.org.apache.xml.internal.security.utils.Base64;


public class TestVideo {

	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("/Users/EzeMaranga/Downloads/El pichichi Daniel Scioli.mp4");
		byte[] data = Files.readAllBytes(path);
		
		String base64 = Base64.encode(data);
		
		System.out.println(base64);
	}
	
	
}
