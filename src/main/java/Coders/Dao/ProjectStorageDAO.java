package Coders.Dao;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectStorageDAO  {

    List<String> storeFile(MultipartFile file);


}
