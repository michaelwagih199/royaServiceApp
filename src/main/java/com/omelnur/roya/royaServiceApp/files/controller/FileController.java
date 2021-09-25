package com.omelnur.roya.royaServiceApp.files.controller;

import com.omelnur.roya.royaServiceApp.files.models.Docs;
import com.omelnur.roya.royaServiceApp.files.payload.UploadFileResponse;
import com.omelnur.roya.royaServiceApp.files.service.FileStorageService;
import com.omelnur.roya.royaServiceApp.files.service.docs.DocsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("api/files")
public class FileController {

    @Autowired
    DocsService docsService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadCycleFile")
    public UploadFileResponse uploadPvFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("cycleId") Long pvId,
                                           @RequestParam("docTitle") String docTitle) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(fileName)
                .toUriString();

        createDocPatientCycle(fileName, "uploads/".concat(fileName), fileDownloadUri,
                file.getContentType(),
                file.getSize(),
                pvId,
                docTitle);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    public void createDocPatientCycle(String fileName, String path, String fileDownloadUri, String contentType, long size, Long pvId, String docTitle) {
        Docs docs = new Docs();
        docs.setFileType(contentType);
        docs.setFileName(fileName);
        docs.setFileDownloadUri(fileDownloadUri);
        docs.setDocTitle(docTitle);
        docs.setFileBath(path);
        docs.setSize(size);
        docsService.createDocPatientCycle(docs, pvId);
    }

    @GetMapping("cycleDocuments")
    public ResponseEntity getCycleDocuments(@RequestParam Long cycleId) {
        return new ResponseEntity<>(docsService.getCycleDocuments(cycleId), HttpStatus.OK);
    }

    @GetMapping("patientDocuments")
    public ResponseEntity getPatientDoctoment(@RequestParam Long patientId) {
        return new ResponseEntity<>(docsService.getPatientDocuments(patientId), HttpStatus.OK);
    }

/*
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadPvFile(file))
                .collect(Collectors.toList());
    }

 */

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
