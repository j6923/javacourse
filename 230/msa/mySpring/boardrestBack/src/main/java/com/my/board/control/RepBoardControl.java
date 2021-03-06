package com.my.board.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.board.service.RepBoardService;
import com.my.board.vo.RepBoard;
import com.my.customer.vo.Customer;
import com.my.dto.PageDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

import net.coobird.thumbnailator.Thumbnailator;

//@Controller
@RestController
@RequestMapping("board/*")
public class RepBoardControl {
	@Autowired
	private RepBoardService service;

	private Logger logger = Logger.getLogger(this.getClass());

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestPart List<MultipartFile> letterFiles// 여러개 전달될 파일이라면 list로 받아줘야 함.
			, @RequestPart MultipartFile imageFile// 1개 첨부하면 MultipartFile의 타입이면 된다.
			, RepBoard repBoard) {
		logger.info("요청전달데이터 title=" + repBoard.getBoardTitle() + ", content=" + repBoard.getBoardContent());
		logger.info("letterFiles.size()=" + letterFiles.size());
		logger.info("imageFile.getSize()=" + imageFile.getSize());
		// ResponseEntity 응답헤더 설정, 응답내용 설정
		// 게시글내용 DB에 저장
		try {
			Customer c = new Customer();
			c.setId("id1");
			repBoard.setBoardC(c);
			service.write(repBoard);
		} catch (AddException e1) {

			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

		// 파일 경로생성
		String saveDirectory = "c:\\files";
		if (!new File(saveDirectory).exists()) {
			logger.info("업로드 실제경로생성");
			new File(saveDirectory).mkdirs();// c드라이브에 찾고 없으면 c드라이브에 files라고 만들어라. 거기가 그림 저장할 파일
		}
		// 0
		int wroteBoardNo = repBoard.getBoardNo(); // 저장된 글의 글번호
		// DB저장하는 작업 안 해서 일단 0번이라고 한다.
		// 이미지파일 저장하기
		String imageOrignFileName = imageFile.getOriginalFilename(); // 이미지파일원본이름얻기
		// 이미지 파일 원본 파일 명을 getOriginalFilename으로 가져올 수 있다.
		logger.info("이미지 파일이름:" + imageOrignFileName + " 파일크기: " + imageFile.getSize());
		// 저장할 파일이름을 지정한다 ex) 글번호_image_XXXX_원본이름
		String imageFileName = wroteBoardNo + "_image_" + UUID.randomUUID() + "_" + imageOrignFileName;
		// uuid는 universally unique identifier를 줄여서 UUID라고 부름,
		// 파일 이름으로 save를 해주면 되고 라이브러리로 다 제공해줌.

		// 파일생성
		File savedImageFile = new File(saveDirectory,imageFileName);// 이것만 하면 크기가 0인 파일만 만들뿐이다.

		File thumbnailFile = null;
		try {
			FileCopyUtils.copy(imageFile.getBytes(),savedImageFile);
			// 파일형식 확인
			logger.info("이미지 파일저장:" + imageFile.getContentType());
			String contentType = imageFile.getContentType();
			if (!contentType.contains("image/")) { // 이미지파일형식이 아닌 경우
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// 이미지파일인 경우 섬네일파일을 만듦
			String thumbnailName = "s_" + imageFileName; // 섬네일 파일명은 s_글번호_XXXX_원본이름
			thumbnailFile = new File(saveDirectory, thumbnailName);
			FileOutputStream thumbnailOS;
			thumbnailOS = new FileOutputStream(thumbnailFile);
			InputStream imageFileIS = imageFile.getInputStream();
			int width = 100;
			int height = 100;
			Thumbnailator.createThumbnail(imageFileIS, thumbnailOS, width, height);
			logger.info("섬네일파일 저장:" + thumbnailFile.getAbsolutePath() + ", 섬네일파일 크기:" + thumbnailFile.length());

			// file로 save하게 된다.
		} catch (IOException e2) {
			e2.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// letterFiles도 저장

	
		 if(letterFiles != null) {
		  
			 for(MultipartFile letterFile : letterFiles) { 
				 if(letterFile.getSize()==0) { 
					 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
					}	
				 long letterFileSize = letterFile.getSize();
				
					 
//				 if(letterFileSize==0) { 
//					 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
//					}else if
					 
				if(letterFileSize>0) {
				 String letterOriginFileName = letterFile.getOriginalFilename(); //자소서 파일 원본
				 logger.info("지원서 파일이름:"+ letterOriginFileName + "파일크기:" + letterFile.getSize());
				
				 
				 String letterfileName = wroteBoardNo + "_letter_" + UUID.randomUUID() + "_" + letterOriginFileName; 
				 
				 File savevdLetterFile = new File(saveDirectory, letterfileName); //파일생성 
				 try {
					 FileCopyUtils.copy(letterFile.getBytes(),savevdLetterFile); 
					 logger.info("지원서 파일저장:" + savevdLetterFile.getAbsolutePath()); 
				}catch(IOException e) { 
					e.printStackTrace(); }
				}

				 	if(letterFileSize > 0) { 
				 	String letterOriginFileName =letterFile.getOriginalFilename(); 
				 	logger.info("이미지 파일저장:" + imageFile.getContentType() ); 
				 	} } }
		 
		// 저장할 파일이름을 지정한다. ex)글번호 letter_XXXX_원본 이름

		if (thumbnailFile != null) {
			// 이미지 썸네일다운로드하기
			try {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set(HttpHeaders.CONTENT_LENGTH, thumbnailFile.length() + "");
				responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(thumbnailFile.toPath()));
				responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
						"inline; filename=" + URLEncoder.encode("a", "UTF-8"));
				logger.info("섬네일파일 다운로드");
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbnailFile), responseHeaders,
						HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		return new ResponseEntity(HttpStatus.OK);// 응답정보를
	}

	//@GetMapping("/list")
	//@GetMapping(value= {"/list",    "/list/{currentPage}"})
	//list받는 
	//@ResponseBody
	//public Object list(@PathVariable int currentPage) {
//	@GetMapping(value= {"/list", "/list/{currentPage}"})
//	public Object  list(@PathVariable Optional<Integer> currentPage) {
//		try {
//			List<RepBoard> list;
//			if(currentPage.isPresent()) { //currentPage값이 있는 경우
//				int cp = currentPage.get();
//				list = service.findAll(cp);
//			}else { //값이 없는 경우(null인 경우)
//				list = service.findAll();
//			}
//			return list;
//		}catch(FindException e) {
//			Map<String, Object> returnMap = new HashMap<>();
//			returnMap.put("msg", e.getMessage());
//			returnMap.put("status", 0);
//			return returnMap;
//		}
//	}
	@GetMapping(value= {"/list", "/list/{currentPage}"})
	public Object  list(@PathVariable Optional<Integer> currentPage) {
		try {
			/*List<RepBoard> list;
			if(currentPage.isPresent()) { //currentPage값이 있는 경우
				int cp = currentPage.get();
				list = service.findAll(cp);
			}else { //값이 없는 경우(null인 경우)
				list = service.findAll();
			}
			return list;
			*/
			PageDTO<RepBoard> pageDTO;
			if(currentPage.isPresent()) { //currentPage값이 있는 경우
				int cp = currentPage.get();
				pageDTO = service.findAll(cp);
			}else { //값이 없는 경우(null인 경우)
				pageDTO = service.findAll();
			}
			return pageDTO;
		}catch(FindException e) {
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
			return returnMap;
		}
	}




	//@GetMapping("board/info")
	//@ResponseBody
//	public Object info(int no) {
	@GetMapping("/{boardNo}")
	public Object info(@PathVariable(name="boardNo") int no) {
		try {

			RepBoard rb = service.findByNo(no);
			return rb;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
			return returnMap;
		}

	}

	@PostMapping("/reply")
	//@ResponseBody//@RequestBody 
	public Object reply(@RequestBody RepBoard repBoard) {
		Map<String, Object> returnMap = new HashMap<>();
		Customer c = new Customer();
		c.setId("id1");
		repBoard.setBoardC(c);
		if(c== null) {
			returnMap.put("status",0);
			returnMap.put("msg", "로그인되지 않아서 수정할 수 없습니다.");
			return returnMap;
		}
		try {
			logger.info(repBoard.getBoardTitle());
			service.reply(repBoard);
			returnMap.put("status",1);
			//c.setId(repBoard.getBoardC().getId());
			
			//reply.add(repBoard);
			
			
			//service.reply(repBoard);
			//returnMap.put("status", 1);
		} catch (AddException e) {
			e.printStackTrace();
			returnMap.put("status", 0);
			returnMap.put("msg", e.getMessage());

		}
		return returnMap;
	}

//	@RequestMapping("board/modify")
//	@ResponseBody
//	public Object modify(RepBoard repBoard, HttpSession session) {
	@PutMapping("/{boardNo}")
	public Object modify(@PathVariable int boardNo, @RequestBody RepBoard repBoard, HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		// 로그인 대신할 샘플 데이터
		Customer c = new Customer();
		c.setId("id1");
		//private Logger logger = Logger.getLogger(this.getClass());
		if (c == null) {
			returnMap.put("status", 0);
			returnMap.put("msg", "로그인되지 않아서 수정할 수 없습니다.");
			return returnMap;
		} else

			try {
				repBoard.setBoardNo(boardNo);
				repBoard.setBoardC(c);
				logger.error("repBoard.getBoardContent()=" + repBoard.getBoardContent());
				
				service.modify(repBoard);
				returnMap.put("status", 1);

			} catch (ModifyException e) {
				returnMap.put("status", 0);
				returnMap.put("msg", e.getMessage());

			}
		return returnMap;
	}

//	@GetMapping("board/remove")
//	@ResponseBody
//	public Object remove(int boardNo) {
	@DeleteMapping("/{boardNo}")  //삭제니까 delete로 매핑한다. 
	public Object remove(@PathVariable int boardNo) {
		Map<String, Object> returnMap = new HashMap<>();
		// 로그인 대신할 샘플 데이터
		//System.out.println("삭제가 될까?");
		Customer c = new Customer();
		c.setId("id1");
		
		if (c == null) {

			
			
			returnMap.put("status", 0);
			returnMap.put("msg", "로그인되지 않아서 수정할 수 없습니다.");
			return returnMap;
		} else {
			System.out.println("ㄱremove-1");
			
		

			try {
				RepBoard repBoard = new RepBoard();
				repBoard.setBoardNo(boardNo);
				repBoard.setBoardC(c);

				service.remove(repBoard);
				returnMap.put("status", 1);

			} catch (RemoveException e) {
				returnMap.put("status", 0);
				returnMap.put("msg", e.getMessage());

			}
		}
		return returnMap;
	}

	@GetMapping("board/download")
	public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
		logger.info("첨부파일 다운로드");
		// 파일 경로생성
		String saveDirectory = "c:\\files";

		// HttpHeaders : 요청/응답헤더용 API
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream;charset=UTF-8");
		// 다운로드시 파일이름 결정
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// Resource : 자원(파일, URL)용 API
		// 다운로드할 파일의 실제 경로 얻기
		File f = new File(saveDirectory, fileName);
		Resource resource = new FileSystemResource(f);
		try {
			logger.info("첨부파일이름: " + resource.getFilename());
			logger.info("첨부파일resource.contentLength()=" + resource.contentLength());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<Resource> responseEntity = new ResponseEntity<>(resource, headers, HttpStatus.OK);
		return responseEntity;
	}

}
