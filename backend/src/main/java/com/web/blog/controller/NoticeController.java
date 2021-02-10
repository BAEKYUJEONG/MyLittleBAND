package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Notice;
import com.web.blog.util.PageNavigator;

import io.swagger.annotations.ApiOperation;

import com.web.blog.service.NoticeService;

@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService notice;
	
	@GetMapping(value = "/list")
	@ApiOperation(value = "리스트")
	private ResponseEntity list(@RequestParam String nowPage, @RequestParam String type, @RequestParam String word) {
		ResponseEntity entity = null;
		if(type == null) {
			System.out.println("리스트가 없습니까?");
		}
		Map result = new HashMap();
		try {
			int total = notice.count();
			if(nowPage == null) {
				nowPage = "1";
			}
			//한 페이지에 출력할 게시물 수
			String cntPerPage = "5";
			
			PageNavigator pn = new PageNavigator(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			int start = pn.getStart();
			int end = pn.getEnd();
			Map map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			System.out.println("controller. type : " + type + " word : " + word);
			if(type.equals("")) {
				System.out.println("type : "+ type);
				List<Notice> list = notice.searchAll(map);
		        System.out.println(list);
		        if(list != null) {
		        	System.out.println(list.get(0).getDate());
		            result.put("list", list);
		            entity = new ResponseEntity(result, HttpStatus.OK);
		        } else {
		        	result.put("success", "fail");
		        	entity = new ResponseEntity(result, HttpStatus.OK);
		        }
			}
			if(type.equals("title")) {
				map.put("title", word);
				List<Notice> list = notice.searchTitle(map);
				System.out.println(word);
				if(list != null) {
					result.put("list", list);
					entity = new ResponseEntity(result, HttpStatus.OK);
				} else {
		        	result.put("success", "fail");
		        	entity = new ResponseEntity(result, HttpStatus.OK);
		        }
			}
			else if(type.equals("word")) {
				map.put("word", word);
				List<Notice> list = notice.searchContent(map);
				System.out.println(word);
				if(list != null) {
					result.put("list", list);
					entity = new ResponseEntity(result, HttpStatus.OK);
				} else {
		        	result.put("success", "fail");
		        	entity = new ResponseEntity(result, HttpStatus.OK);
		        }
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
	        entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping(value = "/content")
	@ApiOperation(value = "내용")
	private ResponseEntity contents(@RequestParam int noticeId) {
	    ResponseEntity entity = null;
	    Map result = new HashMap();
	    try {
	        Notice dto = notice.retrieve(noticeId);
	        if(dto == null) {            
	        	result.put("success", "fail");
	        } else {
	        	result.put("detail", dto);
	        }
	        entity = new ResponseEntity(result, HttpStatus.OK);                
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success","error");
	        entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	}

	@PostMapping(value = "/create")
	@ApiOperation(value = "공지사항 업로드")
	private ResponseEntity create(@RequestBody Notice dto) {
		ResponseEntity entity = null;
		System.out.println(dto.isManager());
		System.out.println(dto.getNoticeTitle());
		Map result = new HashMap();
		try {
			int res = notice.regist(dto);
			if(res == 0) {
				result.put("success", "fail");
			} else {
				result.put("success", "success");
			}
			System.out.println("controller. res : " + res);
			entity = new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	@PutMapping(value = "/update")
	@ApiOperation(value = "공지사항 수정")
	private ResponseEntity update(@RequestBody Notice dto) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			int res = notice.modify(dto);
			if(res == 0) {
				result.put("success", "fail");
			}else {
				result.put("success", "success");
			}
			System.out.println("controller. res : " + res);
			entity = new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	@DeleteMapping(value = "/delete")
	@ApiOperation(value = "공지사항 삭제")
	private ResponseEntity delete(@RequestHeader int noticeId) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			int res = notice.delete(noticeId);
			if(res==0) {
				result.put("success", "fail");
			} else {
				result.put("success", "success");
			}
			System.out.println("controller. res : " + res);
			entity = new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping(value= "/count")
	@ApiOperation(value = "게시글 수 카운트")
	private ResponseEntity count() {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			int res = notice.count();
			result.put("count", res);
			System.out.println("controller. res : " + res);
			entity = new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
		
}
