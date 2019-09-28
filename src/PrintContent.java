import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class PrintContent extends SimpleFileVisitor<Path> {

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println(file.toAbsolutePath());
		if(Files.isRegularFile(file)) {
			try {
				List<String> lines = Files.readAllLines(file);
				for(String line : lines) {
					System.out.println(line);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		System.out.println(dir.toAbsolutePath());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
		return FileVisitResult.CONTINUE;
	}
	
	
	
	
}
