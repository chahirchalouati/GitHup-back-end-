/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Init;

import Coders.Entities.Authoritie;
import Coders.Entities.Language;
import Coders.Repository.AuthoritieRepository;
import Coders.Repository.LanguageRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
public class InitDummyData implements CommandLineRunner {
    
    String[] authoritie = {"USER", "ADMIN", "MANAGER"};    
    
    @Autowired
    LanguageRepository languageRepository;
    
    @Autowired
    AuthoritieRepository authoritieRepository;
    
    @Value("classpath:languages.txt")
    Resource resourceFile;
    
    public void addlanguages() throws IOException {
        
        if (languageRepository.count() <= 0) {
            List<String> readAllLines = Files.readAllLines(resourceFile.getFile().toPath());
            readAllLines.stream().forEach(lang -> {
                languageRepository.save(new Language(lang));
                
            });
            
        }        
        if (authoritieRepository.count() <= 0) {
            Arrays.stream(authoritie).forEach(author -> authoritieRepository.save(new Authoritie(author)));            
        }
        
    }
    
    @Override
    public void run(String... args) throws Exception {
        addlanguages(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
