package exercise;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles (String firstPath, String secondPath, String thirdPath) throws ExecutionException, InterruptedException {

        Path fullPath1 = Paths.get(firstPath).toAbsolutePath().normalize();
        Path fullPath2 = Paths.get(secondPath).toAbsolutePath().normalize();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String content1;
            try {
                content1 = Files.readString(fullPath1);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return content1;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            String content2;
            try {
                content2 = Files.readString(fullPath2);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return content2;
        });

        CompletableFuture<String> future3 = future1.thenCombine(future2, (string1, string2) -> {
            String string3 = string1 + string2;
            System.out.println(string3);
            BufferedWriter writer = null;
            try {
                Path newFilePath = Paths.get(thirdPath);
                Files.createFile(newFilePath);
                writer = new BufferedWriter(new FileWriter(thirdPath));
                writer.write(string3);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return thirdPath;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });;

        return future3;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("file1.txt", "file2.txt", "dest.txt");
        //System.out.println(result);
        // END
    }
}

