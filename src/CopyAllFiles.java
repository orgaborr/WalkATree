import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyAllFiles extends SimpleFileVisitor<Path> {
	
	private Path sourceRoot;
	private Path targetRoot;
	
	public CopyAllFiles(Path sourceRoot, Path targetRoot) {
		this.sourceRoot = sourceRoot;
		this.targetRoot = targetRoot;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		Path relativizedPath = sourceRoot.relativize(file);
		System.out.println("Relativized Path = " + relativizedPath);
		Path resolvedPath = targetRoot.resolve(relativizedPath);
		System.out.println("Resolved Path = " + resolvedPath);
		
		try {
			Files.copy(file, resolvedPath);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		Path relativizedPath = sourceRoot.relativize(dir);
		System.out.println("Relativized Path = " + relativizedPath);
		Path resolvedPath = targetRoot.resolve(relativizedPath);
		System.out.println("Resolved Path = " + resolvedPath);
		
		try {
			Files.copy(dir, resolvedPath);
		} catch(IOException e) {
			e.printStackTrace();
			return FileVisitResult.SKIP_SUBTREE;
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Error accessing file" + file.toAbsolutePath() + " " + exc.getMessage());
		return FileVisitResult.CONTINUE;
	}
	
	
}
