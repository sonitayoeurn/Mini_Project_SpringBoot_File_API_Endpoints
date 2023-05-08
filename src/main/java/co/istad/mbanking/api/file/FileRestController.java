package co.istad.mbanking.api.file;

import co.istad.mbanking.base.BaseError;
import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;
    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart("file") MultipartFile file){
        log.info("File Request = {} ", file);
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    //    upload multiple
    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart List<MultipartFile> files){
        log.info("File Request = {} ", files);
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File have been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping("/find-all-files")
    public BaseRest<?> findAllFiles(){
        List<FileDto> fileDto = fileService.findAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Congratulations !! All files have been found.....")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping("/{fileName}")
    public BaseRest<?> fileDto(@PathVariable String fileName) {
        Optional<FileDto> fileDto = fileService.findFileByName(fileName);
        if (fileDto.isPresent()) {
            return BaseRest.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("File has been found")
                    .timestamp(LocalDateTime.now())
                    .data(fileDto.get())
                    .build();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "File not found"
            );
        }
    }

    @DeleteMapping("/{filename}")
    public BaseRest<?> deleteFileByName(@PathVariable String filename){
        FileDto fileDto = fileService.deleteByName(filename);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been deleted.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @DeleteMapping
    public BaseRest<?> deleteAllFile(){
        boolean deleted = fileService.deleteAllFile();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("All File have been deleted.")
                .timestamp(LocalDateTime.now())
                .data(deleted)
                .build();
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFileByName(@PathVariable String filename) {
        Resource file = fileService.downloadFileByName(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; " + "filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



}
















