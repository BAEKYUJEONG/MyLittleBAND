package com.web.blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.web.blog.service.BandService;
import com.web.blog.service.FilesStorageService;
import com.web.blog.service.MemberService;

@Controller
@CrossOrigin
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @Autowired
  MemberService memberservice;

  @Autowired
  BandService bandservice;
  
  @PostMapping("/upload/member/{memberId}")
  public ResponseEntity<String> uploadFile(@PathVariable String memberId, @RequestParam("file") MultipartFile file) {
//	  System.out.println(file.getOriginalFilename()); //HBD.jpg
//	  System.out.println(file.getName()); // file
//	  System.out.println(file.getContentType()); //image/jpeg
//	  System.out.println(file.getSize()); //361059
//	  try {
//		System.out.println(file.getInputStream()); //java.io.FileInputStream@4acc70eb
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	  System.out.println(file.getResource()); //MultipartFile resource [file]
	  
    String message = "";
    try {
    	memberservice.imgupdate(memberId, "https://i4a408.p.ssafy.io/profile/"+file.getOriginalFilename());
      storageService.saveimage(file);
      message = "https://i4a408.p.ssafy.io/profile/" + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
  
  @PostMapping("/upload/video/{bandId}")
  public ResponseEntity<String> uploadFilevideo(@PathVariable String bandId, @RequestParam("file") MultipartFile file) {
	  
    String message = "";
    try {
      storageService.savevideo(file);
      message = "https://i4a408.p.ssafy.io/video/" + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
  
  @PostMapping("/upload/thumbnail/{bandId}")
  public ResponseEntity<String> uploadFilethumbnail(@PathVariable String bandId, @RequestParam("file") MultipartFile file) {
	  
    String message = "";
    try {
      storageService.savethumbnail(file);
      message = "https://i4a408.p.ssafy.io/thumbnail/" + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
  
  @PostMapping("/upload/band/{bandId}")
  public ResponseEntity<String> uploadFilebandimage(@PathVariable String bandId, @RequestParam("file") MultipartFile file) {
	  
    String message = "";
    try {
    	bandservice.imgupdate(bandId, "https://i4a408.p.ssafy.io/band/"+file.getOriginalFilename());
      storageService.savebandimage(file);
      message = "https://i4a408.p.ssafy.io/band/" + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
  
//  @PostMapping("/upload/band/{bandId}")
//  public ResponseEntity<String> uploadFileband(@PathVariable String bandId, @RequestParam("file") MultipartFile file) {
//	  
//    String message = "";
//    try {
//      storageService.savevideo(file);
//      message = "https://i4a408.p.ssafy.io/band/" + file.getOriginalFilename();
//      return ResponseEntity.status(HttpStatus.OK).body(message);
//    } catch (Exception e) {
//      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//    }
//  }

}