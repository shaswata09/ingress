package com.features;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.PMOProperties.*;

public class GenerateQuarterList {
	public static void main(String args[]) {		
		System.out.println(getCurrentQuarter());
		System.out.println(getAllQuarterList());	
		System.out.println(filteredQuarterList());		
		System.out.println(isQuarterDirectoryPresent("Q2'19"));
		System.out.println(isQuarterDirectoryPresent("Q5'19"));
		System.out.println(isQuarterDirectoryPresent("Q2'99"));
	}
	public static ArrayList<String> getAllQuarterList(){
		File currentDir = new File(ExcelFileDetails.LEAVES_FOLDER_PATH); // current directory
		ArrayList<String> quarterList = new ArrayList<String>();
		try {
			
			for (File file : currentDir.listFiles()) {
				if (file.isDirectory()) {
					quarterList.add(file.getName());					
				}
			}			
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		return quarterList;
	}
	
	public static ArrayList<String> filteredQuarterList(){
		ArrayList<String> quarterList = getAllQuarterList();
		ArrayList<String> sortedQuarterList = new ArrayList<String>();		
		for(int i=0; i<5; i++)			//dummy return arrayList
			sortedQuarterList.add("Q-'--");
		
		for(int i=0; i<quarterList.size(); i++) {
			int quarterDiff = compareQuarter(getCurrentQuarter() , quarterList.get(i));				
			if((quarterDiff>2) || (quarterDiff< -2)) {				
				quarterList.remove(quarterList.get(i));					
				i--;
			} else {				
				sortedQuarterList.set(Math.abs(quarterDiff-2),quarterList.get(i));
			}			
		}		
		quarterList.clear();
		
		return sortedQuarterList;
	}
	
	public static int compareQuarter(String quarterComparing, String quarterComparingWith) {
		int quarterDifference = 0;
		
		int quarterComparingYear = Integer.parseInt(quarterComparing.substring(quarterComparing.length()-2));
		int quarterComparingWithYear = Integer.parseInt(quarterComparingWith.substring(quarterComparingWith.length()-2));
		
		int quarterComparingNumber = Integer.parseInt(quarterComparing.substring(quarterComparing.length()-4, quarterComparing.length()-3));
		int quarterComparingWithNumber = Integer.parseInt(quarterComparingWith.substring(quarterComparingWith.length()-4, quarterComparingWith.length()-3));
		
		quarterComparingNumber = (quarterComparingNumber%4)+1;
		quarterComparingWithNumber = (quarterComparingWithNumber%4)+1;
		
		int yearDifference = quarterComparingYear-quarterComparingWithYear;
		int quarterDifferenceWithinYear = quarterComparingNumber-quarterComparingWithNumber;
		
		quarterDifference = quarterDifferenceWithinYear + (4*yearDifference);
		
		/*
		 * System.out.println("quarterComparingYear:  "+quarterComparingYear);
		 * System.out.println("quarterComparingWithYear:  "+quarterComparingWithYear);
		 * 
		 * System.out.println("quarterComparingNumber:  "+quarterComparingNumber);
		 * System.out.println("quarterComparingWithNumber:  "+quarterComparingWithNumber
		 * );
		 * 
		 * System.out.println("yearDifference:  "+yearDifference);
		 * System.out.println("quarterDifferenceWithinYear:  "
		 * +quarterDifferenceWithinYear);
		 * 
		 * System.out.println("quarterDifference : "+quarterDifference);
		 */
		
		return quarterDifference;
	}	
	
	public static String getCurrentQuarter() {
		DateFormat simpleDateFormatYear = new SimpleDateFormat("yy");
		DateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
		Date date = new Date();
		int currentMonth = Integer.parseInt(simpleDateFormatMonth.format(date));
		int currentYear = Integer.parseInt(simpleDateFormatYear.format(date));
		
		int currentQuarter = (currentMonth%3) > 0 ? (currentMonth/3 +1) : (currentMonth/3);
		currentQuarter = (currentQuarter+2)%4 + 1;
		
		return ("Q"+currentQuarter+"'"+currentYear);
	}
	
	public static String findQuarterByDate(String argDate) {	//yyyy/MM/dd
		int month = Integer.parseInt(argDate.split("/")[1]);
		int year = Integer.parseInt(argDate.split("/")[0]);
		year= year%100;
		
		int resultQuarter = (month%3) > 0 ? (month/3 +1) : (month/3);
		resultQuarter = (resultQuarter+2)%4 + 1;
		
		return ("Q"+resultQuarter+"'"+year);
	}
	
	public static Boolean isQuarterDirectoryPresent (String quarterName) {
		return getAllQuarterList().contains(quarterName);		
	}	
}
