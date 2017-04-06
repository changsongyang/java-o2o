package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.pojo.KindEditorResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.KindEditorService;

@Service
public class KindEditorServiceImpl implements KindEditorService {
	
	//注入图片地址
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public KindEditorResult uploadPic(MultipartFile picFile) {
		if (null == picFile || picFile.isEmpty()) {
			return KindEditorResult.error();
		}
		try {
			// 取图片扩展名
			String originalFilename = picFile.getOriginalFilename();
			// 取扩展名不要“.”
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

			FastDFSClient upload = new FastDFSClient("properties/resource.properties");
			String uploadFile = upload.uploadFile(picFile.getBytes(), extName);
			return KindEditorResult.ok(IMAGE_BASE_URL + uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return KindEditorResult.error();
		}
	}

}
