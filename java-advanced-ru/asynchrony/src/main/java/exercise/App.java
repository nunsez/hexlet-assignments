package exercise;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.io.File;

class App {

    // BEGIN
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) {
        var content1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(getFullPath(src1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        var content2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(getFullPath(src2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return content1.thenCombine(content2, (cont1, cont2) -> {
            var union = cont1 + cont2;
            try {
                Files.writeString(getFullPath(dest), union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return union;

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {
        var directory = new File(path);

        if (!directory.isDirectory()) {
            return CompletableFuture.completedFuture(0L);
        }

        var files = directory.listFiles();

        var fileSizes = Arrays.stream(files)
            .filter(File::isFile)
            .map(file -> CompletableFuture.supplyAsync(() -> file.length()))
            .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(fileSizes)
            .thenApply(v -> Arrays
                .stream(fileSizes)
                .mapToLong(x -> (long) x.join())
                .sum()
            );
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        var result = unionFiles(
            "src/main/resources/file1.txt",
            "src/main/resources/file2.txt",
            "src/main/resources/file3.txt"
        );
        CompletableFuture<Long> size = getDirectorySize("src/main/resources");
        result.get();
        System.out.println("done!");
        System.out.println(size.get());
        // END
    }
}

