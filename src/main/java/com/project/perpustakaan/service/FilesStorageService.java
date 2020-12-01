package com.project.perpustakaan.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public String save(MultipartFile file, String rename);

    public Resource load(String filename);

    public void deleteAll();

    public void delete(String nameFile);

    public Stream<Path> loadAll();
}
