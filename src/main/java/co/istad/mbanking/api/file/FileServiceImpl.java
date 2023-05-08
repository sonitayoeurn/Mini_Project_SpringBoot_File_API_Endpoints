package co.istad.mbanking.api.file;

import co.istad.mbanking.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${file.server-path}")
    private String fileServerPath;

    @Value("${file.base-url}")
    private String fileBaseUrl;

    @Value("${file.base_download_url}")
    private String fileBaseDownloadUrl;
    private FileUtil fileUtil;
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    /**
     * uses to upload a single file
     * @param file request form data from client
     * @return FileDto
     */
    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);

    }


    /**
     * uses to upload multiple files
     * @param files request form data from client
     * @return FileDto
     */
    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();
        for(MultipartFile file : files) {
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

    @Override
    public List<FileDto> findAllFiles() {
        List<FileDto> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(fileServerPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        long size = path.toFile().length();
                        String url = String.format("%s%s", fileBaseUrl, fileName);
                        int lastDotIndex = fileName.lastIndexOf(".");
                        String extension = lastDotIndex > 0 ? fileName.substring(lastDotIndex + 1) : "";
                        String downloadUrl = String.format("%s%s", fileBaseDownloadUrl, fileName);
                        FileDto file = FileDto.builder()
                                .name(fileName)
                                .url(url)
                                .downloadUrl(downloadUrl)
                                .extension(extension)
                                .size(size)
                                .build();
                        files.add(file);
                    });
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve files ...!");
        }
        return files;
    }

    @Override
    public Optional<FileDto> findFileByName(String fileName) {
        try (Stream<Path> paths = Files.walk(Paths.get(fileServerPath))) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> file.getName().equalsIgnoreCase(fileName))
                    .findFirst()
                    .map(file -> {
                        String url = String.format("%s%s", fileBaseUrl, file.getName());
                        long size = file.length();
                        int lastDotIndex = file.getName().lastIndexOf(".");
                        String extension = lastDotIndex > 0 ? file.getName().substring(lastDotIndex + 1) : "";
                        String downloadUrl = String.format("%s%s", fileBaseDownloadUrl, fileName);
                        return FileDto.builder()
                                .name(file.getName())
                                .url(url)
                                .downloadUrl(downloadUrl)
                                .extension(extension)
                                .size(size)
                                .build();
                    });
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve files ...!");
        }
    }

    @Override
    public FileDto deleteByName(String filename) {

        FileDto fileDto = this.findAllFiles().stream()
                .filter(fileDto1 -> fileDto1.name().equalsIgnoreCase(filename))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "File not found, please try gain..."));

        File file = new File(this.fileServerPath, filename);
        boolean delete = file.delete();
        return fileDto;
    }

    @Override
    public boolean deleteAllFile() {
        if (this.findAllFiles().isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                    "File is empty.");
        this.findAllFiles().forEach(fileDto -> this.deleteByName(fileDto.name()));
        return true;
    }

    @Override
    public Resource downloadFileByName(String fileName) {
        try {
            Path file = Paths.get(fileServerPath + fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file...");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


}













