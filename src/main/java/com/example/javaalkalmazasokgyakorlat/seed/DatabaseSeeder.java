package com.example.javaalkalmazasokgyakorlat.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final InventionsDBSeeder inventionsDBSeeder;
    private final UsersDBSeeder usersDBSeeder;

    public DatabaseSeeder(
            UsersDBSeeder usersDBSeeder,
            InventionsDBSeeder inventionsDBSeeder
    ) {
        this.usersDBSeeder = usersDBSeeder;
        this.inventionsDBSeeder = inventionsDBSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.usersDBSeeder.seed();
        this.inventionsDBSeeder.seed();
    }
}
