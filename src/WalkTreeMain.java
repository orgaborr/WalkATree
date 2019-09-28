import java.io.File;
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
		
		System.out.println("\nWalk Tree for topDir");
		Path topDirPath = FileSystems.getDefault().getPath("topDir");
		try {
			Files.walkFileTree(topDirPath, new PrintContent());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		/*// Commented out to not try to create the directory again
		 * System.out.println("\nCopy topDir/dir1 to dir2/dir1copy");
		 * 
		 * Path copyPath = FileSystems.getDefault().getPath("topDir" + File.separator +
		 * "dir2" + File.separator + "dir1copy"); Path dir1Path =
		 * FileSystems.getDefault().getPath("topDir" + File.separator + "dir1");
		 * 
		 * try { Files.walkFileTree(dir1Path, new CopyAllFiles(dir1Path, copyPath));
		 * 
		 * } catch(IOException e) { e.printStackTrace(); }
		 */
	}

}
