package co.istad.mbanking.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {
    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);
    List<FileDto> findAllFiles();

    Optional<FileDto> findFileByName(String fileName);

    FileDto deleteByName(String filename);

    boolean deleteAllFile();

    Resource downloadFileByName(String fileName);
}
