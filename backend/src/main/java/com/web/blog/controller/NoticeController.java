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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Notice;
import com.web.blog.dto.NoticeReq;
import com.web.blog.dto.VideoBoardReq;
import com.web.blog.dto.noticeCriteria;
import com.web.blog.model.BasicResponse;
import com.web.blog.util.PageNavigator;

import io.swagger.annotations.ApiOperation;

import com.web.blog.service.NoticeService;

@CrossOrigin
@RestController
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
//	@GetMapping(value = "/notice/list")
//	@ApiOperation(value = "리스트")
//	private ResponseEntity list(@RequestParam String nowPage, @RequestParam String type, @RequestParam String word) {
//		ResponseEntity entity = null;
//		if(type == null) {
//			System.out.println("리스트가 없습니까?");
//		}
//		Map result = new HashMap();
//		try {
//			int total = notice.count();
//			if(nowPage == null) {
//				nowPage = "1";
//			}
//			//한 페이지에 출력할 게시물 수
//			String cntPerPage = "5";
//			
//			PageNavigator pn = new PageNavigator(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
//			int start = pn.getStart();
//			int end = pn.getEnd();
//			Map map = new HashMap<>();
//			map.put("start", start);
//			map.put("end", end);
//			System.out.println("controller. type : " + type + " word : " + word);
//			if(type.equals("")) {
//				System.out.println("type : "+ type);
//				List<Notice> list = notice.searchAll(map);
//		        System.out.println(list);
//		        if(list != null) {
//		        	System.out.println(list.get(0).getDate());
//		            result.put("list", list);
//		            entity = new ResponseEntity(result, HttpStatus.OK);
//		        } else {
//		        	result.put("success", "fail");
//		        	entity = new ResponseEntity(result, HttpStatus.OK);
//		        }
//			}
//			if(type.equals("title")) {
//				map.put("title", word);
//				List<Notice> list = notice.searchTitle(map);
//				System.out.println(word);
//				if(list != null) {
//					result.put("list", list);
//					entity = new ResponseEntity(result, HttpStatus.OK);
//				} else {
//		        	result.put("success", "fail");
//		        	entity = new ResponseEntity(result, HttpStatus.OK);
//		        }
//			}
//			else if(type.equals("word")) {
//				map.put("word", word);
//				List<Notice> list = notice.searchContent(map);
//				System.out.println(word);
//				if(list != null) {
//					result.put("list", list);
//					entity = new ResponseEntity(result, HttpStatus.OK);
//				} else {
//		        	result.put("success", "fail");
//		        	entity = new ResponseEntity(result, HttpStatus.OK);
//		        }
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("success", "error");
//	        entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}
	
	@PostMapping(value = "/notice")
	@ApiOperation(value = "공지사항 작성", notes = "공지사항 작성 요청을 보낸다.")
	public Object writenotice(@RequestBody NoticeReq req) {
		service.writenotice(req);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/notice/{noticeId}")
	@ApiOperation(value = "공지사항 읽기", notes = "공지사항 내용을 보여준다.")
	public Object readnotice(@PathVariable String noticeId) {
		Notice map= service.readnotice(noticeId);
		if(map!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=map;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value = "/notice/{noticeId}")
	@ApiOperation(value = "공지사항 수정", notes = "공지사항 내용을 수정한다.")
	public Object updatenotice(@RequestBody NoticeReq req, @PathVariable String noticeId) {
		service.updatenotice(req,noticeId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/notice/{noticeId}")
	@ApiOperation(value = "공지사항 삭제", notes = "공지사항 내용을 삭제한다.")
	public Object deletenotice(@PathVariable String noticeId) {
		service.deletenotice(noticeId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/notice/list")
	@ApiOperation(value = "공지사항 리스트", notes = "공지사항 리스트를 보여준다.")
	public Object noticelist() {
		int count= service.noticelist();
		
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object=count;
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/notice/listpage/{page}")
	@ApiOperation(value = "공지사항 리스트", notes = "공지사항 리스트를 보여준다.")
	public Object noticelistpage(@PathVariable int page) {
		List<Notice> map= service.noticelistpage((page-1)*5);
		
		if(map!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=map;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
