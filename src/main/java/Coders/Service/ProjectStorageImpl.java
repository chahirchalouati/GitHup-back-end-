package Coders.Service;

import Coders.Entities.Directory;
import Coders.Entities.File;
import Coders.Entities.Repository;
import Coders.Exceptions.StorageException;
import Coders.Repository.FileRepository;
import Coders.Repository.RepositoryOfRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource("classpath:file.properties")
@Slf4j
public class ProjectStorageImpl {

    @Value("${files.base.dir}")
    private String uploadDir;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private RepositoryOfRepository repositoryOfRepository;

    public Repository storeFile(MultipartFile file) {

        try {

            return unzipFolder(file);

        } catch (IOException e) {
            log.error("STORAGE-SERVICE {} ", "Unable to create directory ");
            throw new StorageException(e.getLocalizedMessage());
        }

    }

    public Repository unzipFolder(MultipartFile file) throws IOException {

        List<String> files = new ArrayList();
        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {

            // list of files in zip
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {

                String concat = uploadDir.concat("/").concat(zipEntry.getName()).trim();
                Path newPath = Paths.get(concat).normalize().toAbsolutePath();
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {
                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent());
                        }
                    }

                    zipSlipProtect(zipEntry, newPath);

                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);

                }
                files.add(zipEntry.getName().trim());
                zipEntry = zis.getNextEntry();

            }
            zis.closeEntry();

        }
        return directoryBuilder(files);
    }

    public Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {

        Path targetDirResolved = targetDir.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            log.info("BAD ZIP ENTRY {} " + zipEntry.getName());
            throw new IOException("BAD ZIP ENTRY {} " + zipEntry.getName());
        }

        return normalizePath;
    }

    @Transactional
    public Repository directoryBuilder(List<String> files) {
        List<Directory> dirs = new ArrayList<>();
        int i = 0;
        for (String file : files) {
            Directory dir = new Directory();
            if (file.endsWith("/")) {
                if (dirs.size() > 0) {
                    dir.setName(nameGenerator(file.trim()));
                } else {
                    dir.setName(nameGenerator(file.trim()));
                }

                dirs.add(dir);
            } else {

                dirs.get(dirs.size() - 1).getFiles().add(new File(nameGenerator(file.trim()), "/files/" + Instant.now().getEpochSecond() + i, Paths.get("./uploads/".concat(file)).toAbsolutePath().toString()));
            }
            i++;
        }
        Repository repository = new Repository();
        repository.setName(dirs.get(0).getName().replace("/", ""));
        repository.setDirectorie(dirs.stream().collect(Collectors.toSet()));

        Repository save = repositoryOfRepository.save(repository);
        // List<Directory> saveAll = directoryRepository.saveAll(dirs);
        return save;
    }

    public String nameGenerator(String filename) {
        String[] split = filename.split("/");
        String currentName = split[split.length - 1];
        if (split.length > 1) {
            return currentName;
        } else {
            return filename;
        }
    }

}
