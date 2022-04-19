package project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.gson.Gson;

import project.service.StorageService;

@RestController
public class StorageController {
	Logger logger = LogManager.getLogger(StorageController.class);

	@Autowired
	StorageService service;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;
	
	@PostMapping("/freeboard/image")
	public String uploadFile(@RequestParam(value="image") MultipartFile image){
		logger.info("StorageController uploadFile image : "+image);
		File fileObj = convertMultiPartFileToFile(image);
		String fileName = System.currentTimeMillis()+"_"+image.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
		String url = s3Client.getUrl(bucketName, fileName).toString();		
		fileObj.delete();
		logger.info(url);
		return url;
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
		byte[] data = service.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
	}
	
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable String fileName){
		return new ResponseEntity<>(service.deleteFile(fileName),HttpStatus.OK);
	}
	
    private File convertMultiPartFileToFile(MultipartFile image) {
        File convertedFile = new File(image.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(image.getBytes());
        } catch (IOException e) {
        	logger.info("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}


