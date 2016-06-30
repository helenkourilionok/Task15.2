package by.training.task15.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileThread implements Runnable {

	private File file;
	private List<Double> listResult;
	
	public FileThread(File file,List<Double> listResult) {
		super();
		this.file = file;
		this.listResult = listResult;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String separator = " ";
		String operation,doubleNumbers;
		double[] arrDouble;
		ArrayOperation arrOperation = null;
		synchronized (listResult) {
			try(FileInputStream fis = new FileInputStream(file);Scanner scanner = new Scanner(fis)){
				operation = scanner.nextLine();
				doubleNumbers = scanner.nextLine(); 
				String[] Numbers = doubleNumbers.split(separator);
				arrDouble = StringToDouble(Numbers);
				/*
				System.out.println("Array double"+operation);
				for(Double a:arrDouble){
					System.out.println(a);
				}
				System.out.println("--------------------------");	
					*/
				arrOperation = makeOperation(operation);
				listResult.add(new Double(arrOperation.operation(arrDouble)));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
		
	private static double[] StringToDouble(String[] arrStrings){
		double[] arrDouble = new double[arrStrings.length];
		for(int i = 0;i<arrStrings.length;i++){
			arrDouble[i] = Double.parseDouble(arrStrings[i]);
		}
		return arrDouble;
	}
	
	private ArrayOperation makeOperation(String operation){
		ArrayOperation arrOperation = null;
		switch (operation) {
		case "1":
			arrOperation = (arr)->{
										double result = 0.0;
										for(int i = 0;i<arr.length;i++){
											result += arr[i];
										}
										return result;
									};
			break;
		case "2":{
			arrOperation = (arr)->{
									double result = arr[0];
									for(int i = 1;i<arr.length;i++){
										result -= arr[i];
									}
									return result;
								  };
		}break;
		case "3":{
			arrOperation = (arr)->{
									double result = 0.0;
									for(int i = 0;i<arr.length;i++){
										result += arr[i]*arr[i];
									}
									return result;
								  };
		}break;
		default:
			break;
		}
		return arrOperation;
	}
}
