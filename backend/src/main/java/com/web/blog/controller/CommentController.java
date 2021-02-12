package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.web.blog.dto.Comment;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.CommentService;

import io.swagger.annotations.ApiOperation;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@GetMapping(value = "/{commentId}")
	@ApiOperation(value = "댓글 작성", notes = "댓글 업로드를 요청을 한다.")
	public JsonObject insert(@RequestBody Comment comment, @PathVariable String commentId) {
		JsonObject jsonObj = new JsonObject();
		try {
			boolean isRegistered = service.insert(comment);
			jsonObj.addProperty("result", isRegistered);
			
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}
	
	@PutMapping(value = "/{commentId}")
	@ApiOperation(value = "댓글 수정", notes = "댓글 수정을 요청을 한다.")
	public JsonObject update(@RequestBody Comment comment, @PathVariable String commentId) {
		JsonObject jsonObj = new JsonObject();
		try {
			boolean isRegistered = service.update(comment);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}
	
	@DeleteMapping(value = "{/commentId}")
	@ApiOperation(value = "댓글 삭제", notes = "댓글 삭제를 요청한다.")
	public Object delete(@PathVariable String commentId) {
		service.delete(commentId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/list/{boardId}")
	@ApiOperation(value = "댓글 목록")
	public Object selectList(@RequestBody Comment comment, @PathVariable String boardId) {
		JsonObject jsonObj = new JsonObject();
		List<Comment> commentList = service.selectList(comment);
		if (CollectionUtils.isEmpty(commentList) == false) {
			JsonArray jsonArr = new Gson().toJsonTree(commentList).getAsJsonArray();
			jsonObj.add("commentList", jsonArr);
		}

		return jsonObj;	
	}
	
	@GetMapping(value= "/count")
	@ApiOperation(value = "댓글 수 카운트")
	private Object count() {
		int comment = service.count();
		if(comment!=0) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object = comment;
	        return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
