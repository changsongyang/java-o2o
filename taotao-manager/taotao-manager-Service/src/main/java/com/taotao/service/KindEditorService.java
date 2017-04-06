package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.pojo.KindEditorResult;

public interface KindEditorService {
	
	KindEditorResult uploadPic(MultipartFile picFile);
}
