import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkTreeMain {

	public static void main(String[] args) {
		
		// getting attributes of a file
		try {
			Path filePath = FileSystems.getDefault().getPath("topDir", "dir1/file11.txt");
			// we can get some attributes individually
			System.out.println("Size: " + Files.size(filePath));
			
			// or we get all attributes of the file
			BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
			
			// we can get the individual attributes from attrs now
			System.out.println("Creation time: " + attrs.creationTime());
			System.out.println("Last modified: " + attrs.lastModifiedTime());
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		

	}

}
