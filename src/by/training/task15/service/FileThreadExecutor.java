package by.training.task15.service;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileThreadExecutor {
	private static File[] readFiles;
	private static File writeFile;
	private static List<Double> results;	
	
	public static void initFileThreadExecutor(String fileDir,String[] fileNames,String writeFileName){
		results = new ArrayList<Double>();
		writeFile = new File(fileDir,writeFileName);
		readFiles = new File[fileNames.length];
		for(int i = 0;i<fileNames.length;i++){
			readFiles[i] = new File(fileDir, fileNames[i]);
		}
	}

	public static void makeDoubleOperation(){
		Thread[] arrThreads = new Thread[readFiles.length];
		for(int i = 0;i<readFiles.length;i++){
			arrThreads[i] = new Thread(new FileThread(readFiles[i], results));
			arrThreads[i].start();
		}
		for(Thread thread:arrThreads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static double sumResults(){
		double result = 0.0;
		for(Double d:results){
			result += d;
		}
		return result;
	}
	
	public static void writeResultInFile(){
		try(FileOutputStream fos = new FileOutputStream(writeFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos)){
			dos.writeDouble(sumResults());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
