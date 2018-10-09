package com.example.wutai.wutaimoutain.Utils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	
	/**
	 * 关闭输入流
	 * @param is
	 */
	public static void closeInputStream(InputStream is) {
		if(is != null ) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
