package com.spring.view.review;

import java.io.File;
import java.io.IOException;

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
		
		//������Ʈ ��� ����
		System.out.println(files.getOriginalFilename());
		String uploadDir = this.getClass().getResource("").getPath();
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource";
		
		//�����̸� ����
		String store_name = files.getOriginalFilename().substring(0, 
				files.getOriginalFilename().indexOf("_"));
	
		//���丮 ����
		File Folder = new File(uploadDir +"/" + store_name);
		if(!Folder.exists()) { Folder.mkdir(); }
		File subFolder = new File(Folder.getAbsolutePath() + "/review_img" );	
		if(!subFolder.exists()) { subFolder.mkdir(); }
		
		//������ �̸� ����
		String name = files.getOriginalFilename().substring(files.getOriginalFilename().indexOf("20")); // 20~~
		name = name.substring(0, name.indexOf(".jpg")+4); //20~~.jpg
		
		//���ε�
		files.transferTo(new File(subFolder.getAbsolutePath() + "/" + name));
	}
}
