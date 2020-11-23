package com.spring.view.review;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUpLoadController {

	@RequestMapping(value = "/upload.do" ,method = RequestMethod.POST)
	@ResponseBody
	public void fileUpload(MultipartFile files) throws IllegalStateException, IOException {
		
		//프로젝트 경로 추출
		System.out.println(files.getOriginalFilename());
		String uploadDir = this.getClass().getResource("").getPath();
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource";
		
		//가게이름 추출
		String store_name = files.getOriginalFilename().substring(0, 
				files.getOriginalFilename().indexOf("_"));
	
		//디렉토리 생성
		File Folder = new File(uploadDir +"/" + store_name);
		if(!Folder.exists()) { Folder.mkdir(); }
		File subFolder = new File(Folder.getAbsolutePath() + "/review_img" );	
		if(!subFolder.exists()) { subFolder.mkdir(); }
		
		//저장할 이름 가공
		String name = files.getOriginalFilename().substring(files.getOriginalFilename().indexOf("20")); // 20~~
		name = name.substring(0, name.indexOf(".jpg")+4); //20~~.jpg
		
		//리사이즈
		BufferedImage inputImage = ImageIO.read(files.getInputStream());
		BufferedImage outputImage =
                new BufferedImage(800, 800, inputImage.getType());
		
		Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImage, 0, 0, 800, 800, null);
        graphics2D.dispose();

        ImageIO.write(outputImage, "jpg", new File(subFolder.getAbsolutePath() + "/" + name));
	}
}
