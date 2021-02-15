package com.web.blog.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public void saveimage(MultipartFile file, String timeurl);

  public void savevideo(MultipartFile file, String timeurl);

  public void savethumbnail(MultipartFile file, String timeurl);
  
  public void savebandimage(MultipartFile file, String timeurl);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
}