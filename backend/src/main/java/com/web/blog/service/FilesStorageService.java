package com.web.blog.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public void saveimage(MultipartFile file);

  public void savevideo(MultipartFile file);

  public void savethumbnail(MultipartFile file);
  
  public void savebandimage(MultipartFile file);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
}