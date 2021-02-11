package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.VideoBoard;
import com.web.blog.service.VideoBoardService;

@CrossOrigin
@RestController
//@RequestMapping("/video")
public class VideoBoardController {
	
	@Autowired
	private VideoBoardService videoboard;
	
	//비디오 업로드
	@PostMapping(value = "/video")
	private ResponseEntity create(@RequestBody VideoBoard video) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			int res = videoboard.update(video);
			System.out.println(res);
			if(res == 1) {
				result.put("succes", "success");
				entity = new ResponseEntity(result, HttpStatus.OK);
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//비디오 수정
	@PutMapping(value = "/video/{boardId}")
	private ResponseEntity update(@RequestBody VideoBoard video, @PathVariable String boardId) {
		ResponseEntity entity = null;
        Map result = new HashMap();
        try {
        	int res = videoboard.update(video);
            if(res == 1) {
                result.put("success", "success");
                entity = new ResponseEntity(result, HttpStatus.OK);
                
            }
            else {
                result.put("success", "fail");
                entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
        return entity;
	}
	
	//비디오 삭제
    @DeleteMapping(value = "/video/{boardId}")
    private ResponseEntity delete(@PathVariable String boardId) {
        ResponseEntity entity = null;
        Map result = new HashMap();
        try {
            int res = videoboard.delete(boardId);
            if(res == 1) {
                result.put("success", "success");
                entity = new ResponseEntity(result, HttpStatus.OK);

            }
            else {
                result.put("success", "fail");
                entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", "error");
            entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    
}
