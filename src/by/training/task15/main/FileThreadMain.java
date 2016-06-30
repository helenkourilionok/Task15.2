package by.training.task15.main;

import by.training.task15.service.FileThreadExecutor;

public class FileThreadMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileDir = "D:\\MyFolder\\fileThread";
		String[] fileNames = {"in1.dat","in2.dat","in3.dat"};
		String writeFileName = "out.dat";
		FileThreadExecutor.initFileThreadExecutor(fileDir, fileNames, writeFileName);
		FileThreadExecutor.makeDoubleOperation();
		System.out.println(FileThreadExecutor.sumResults());
		FileThreadExecutor.writeResultInFile();
		System.out.println();
	}
}
