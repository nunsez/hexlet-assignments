package exercise;

import java.util.concurrent.CompletableFuture;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) {
        var feature1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(src1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        var feature2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(src2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        var result = feature1
            .thenCombine(feature2, (file1, file2) -> {
                var content = file1 + file2;

                try {
                    Files.writeString(Path.of(dest), content);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return content;
            }).exceptionally(e -> {
                System.out.println(e.getMessage());
                return null;
            });

        return result;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        var file1 = "src/main/resources/file1.txt";
        var file2 = "src/main/resources/file2.txt";
        var file3 = "src/main/resources/file3.txt";
        unionFiles(file1, file2, file3).get();
        // END
    }
}

