package generator;

import entity.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.io.FileUtils;

public class AccountGenerator {

    public static final int NUMBER_OF_ACCOUNTS = 10;
    private static final String LOCATION = "accounts";
    private static final long MIN_BALANCE = 5000;
    private static final long MAX_BALANCE = 60000;


    public void createRandomAccounts() {

        deleteOldAccounts();
        IntStream stream = IntStream.range(0, NUMBER_OF_ACCOUNTS);
        List<Account> listAccounts = new ArrayList<>();
        stream.forEach(i -> listAccounts.add(new Account(i, generateNames(),
                                                                    generateRandomBalance(MIN_BALANCE, MAX_BALANCE))));
        listAccounts.forEach(i -> {
            File file = new File(LOCATION + "\\" + i.getId() + ".bin");
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(i);
                    oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void deleteOldAccounts() {
        try {
            FileUtils.cleanDirectory(new File(LOCATION));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateNames() {
        List<String> names = Arrays.asList("Petr", "Diana", "Alice", "Ronya", "Viacheslav", "Anton", "Kirill",
                "Dimitry", "Alex", "Roman", "Sofia");
        List<String> surNames = Arrays.asList("Armstrong", "Bell", "Jackson", "Park", "Hunter", "Mitchell", "Houston",
                "Barton", "Garrison", "Goodman");
        Collections.shuffle(names);
        Collections.shuffle(surNames);
        return names.get(0) + " " + surNames.get(0);
    }

    private long generateRandomBalance(long start, long end) {
        return start + Math.round(Math.random() * (end - start));
    }



}
