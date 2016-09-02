package com.logquerier.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileOperations {
	private String path;
	
	public FileOperations(String path){
		this.path = path;
	}
	
	
	public String readFile() throws IOException {

	    File file = new File(path);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	public void writeFile(StringBuilder sb){
		File file = new File(path);
		String content = sb.toString();
		try (FileOutputStream fop = new FileOutputStream(file)) {

			if (!file.exists()) {
				file.createNewFile();
			}

			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	
}
