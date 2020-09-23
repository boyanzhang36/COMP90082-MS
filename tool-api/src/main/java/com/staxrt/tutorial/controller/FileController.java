package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.File;
import com.staxrt.tutorial.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/files")
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<File> getFileById(@PathVariable("id") int id) throws ResourceNotFoundException {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found on: " + id));
        return ResponseEntity.ok().body(file);
    }

    @PostMapping("/files")
    public File createFile(@Valid @RequestBody File file) {
        return fileRepository.save(file);
    }

    @PutMapping("/files/{id}")
    public ResponseEntity<File> updateFile(@PathVariable("id") int id,
                                           @Valid @RequestBody File newFile)
            throws ResourceNotFoundException {

        File file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found on: " + id));

        file.setApptid(newFile.getApptid());
        file.setLink(newFile.getLink());
        file.setTitle(newFile.getTitle());

        final File updatedFile = fileRepository.save(file);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/files/{id}")
    public Map<String, Boolean> deleteFile(@PathVariable("id") int id) throws Exception {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found on: " + id));
        fileRepository.delete(file);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
