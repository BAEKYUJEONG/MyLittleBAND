package com.web.blog.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("/home/ubuntu/backend/");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void saveimage(MultipartFile file) {
    try {
//    	System.out.println(this.root.resolve(file.getOriginalFilename())); //.\HBD.jpg
      Files.copy(file.getInputStream(), this.root.resolve("profile/"+file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }
  
  @Override
  public void savevideo(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve("video/"+file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public void savethumbnail(MultipartFile file) {
	  try {
		  Files.copy(file.getInputStream(), this.root.resolve("thumbnail/"+file.getOriginalFilename()));
	  } catch (Exception e) {
		  throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	  }
  }
  
  @Override
  public void savebandimage(MultipartFile file) {
	  try {
		  Files.copy(file.getInputStream(), this.root.resolve("band/"+file.getOriginalFilename()));
	  } catch (Exception e) {
		  throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	  }
  }
  
  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
}