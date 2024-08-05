package encrypter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ListEncrypter {
    private final List<String> input;

    public ListEncrypter(List<String> input) {
        this.input = input;
    }

    public List<Future<List<String>>> encrypt() {
        List<Future<List<String>>> futures = new ArrayList<>();
        futures.add(processing(input));
        return futures;
    }

    private Future<List<String>> processing(List<String> input) {
         return Executors.newVirtualThreadPerTaskExecutor().submit(() -> {
                     return input.stream()
                             .map(Encrypter::encrypt)
                             .collect(Collectors.toList());
                 });
    }
}
