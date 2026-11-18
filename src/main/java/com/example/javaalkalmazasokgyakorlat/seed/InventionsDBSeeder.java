package com.example.javaalkalmazasokgyakorlat.seed;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;
import com.example.javaalkalmazasokgyakorlat.model.inventor.Inventor;
import com.example.javaalkalmazasokgyakorlat.repository.InventionRepository;
import com.example.javaalkalmazasokgyakorlat.repository.InventorRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public final class InventionsDBSeeder {
    private final InventorRepository inventorRepository;
    private final InventionRepository inventionRepository;

    private final ClassPathResource inventorFilePath = new ClassPathResource("seed/kutato.txt");
    private final ClassPathResource inventionFilePath = new ClassPathResource("seed/talalmany.txt");
    private final ClassPathResource inventorJoinInventionFilePath = new ClassPathResource("seed/kapcsol.txt");

    private final Map<Long, Inventor> inventors = new HashMap<>();
    private final Map<Long, Invention> inventions = new HashMap<>();

    public InventionsDBSeeder(
            InventorRepository inventorRepository,
            InventionRepository inventionRepository
    ) {
        this.inventorRepository = inventorRepository;
        this.inventionRepository = inventionRepository;
    }

    public void seed() throws IOException {
        if (inventorRepository.count() > 0 && inventionRepository.count() > 0)
            return;
        loadInventions();
        loadInventors();
        loadRelations();
        inventorRepository.saveAll(inventors.values());
    }

    private void loadInventions() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     this.inventionFilePath.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t", 2);

                Long tkod = Long.parseLong(parts[0]);

                Invention invention = new Invention();
                invention.setName(parts[1]);

                inventionRepository.save(invention);

                inventions.put(tkod, invention);
            }
        }
    }

    private void loadInventors() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     this.inventorFilePath.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                Long fkod = Long.parseLong(parts[0]);

                Inventor inventor = new Inventor();
                inventor.setName(parts[1]);
                inventor.setBornat(Integer.parseInt(parts[2]));

                if (parts.length > 3 && !parts[3].isBlank())
                    inventor.setDiedat(Integer.parseInt(parts[3]));
                else inventor.setDiedat(null);

                inventorRepository.save(inventor);

                inventors.put(fkod, inventor);
            }
        }
    }

    private void loadRelations() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     this.inventorJoinInventionFilePath.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                Long tkod = Long.parseLong(parts[0]);
                Long fkod = Long.parseLong(parts[1]);

                Invention invention = inventions.get(tkod);
                Inventor inventor = inventors.get(fkod);

                if (inventor != null && invention != null)
                    inventor.getInventions().add(invention);
            }
        }
    }
}
