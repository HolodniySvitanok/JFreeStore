package com.holodniysvitanok.service.impl;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



import org.springframework.stereotype.Service;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Service
public class ImageBox {

	public boolean saveImage(byte[] fileByte, String fileName, String url) {

		BufferedOutputStream bos = null;

		try {

			File file = createPath(fileName, url);
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(fileByte);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
		}

		
		return true;
	}

	public boolean deleteImage(String fileName, String url) {
		try {
			File file = createPath(fileName, url);
			file.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private File createPath(String fileName, String url) throws IOException {
		File file = new File(url+fileName);

		return file;
	}

}
