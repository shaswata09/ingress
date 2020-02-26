package com.features;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.PMOProperties.*;

public class QuarterServiceHelper {

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
		int month = Integer.parseInt(argDate.split("-")[1]);
		int year = Integer.parseInt(argDate.split("-")[0]);
		year= year%100;
		
		int resultQuarter = (month%3) > 0 ? (month/3 +1) : (month/3);
		resultQuarter = (resultQuarter+2)%4 + 1;
		
		return ("Q"+resultQuarter+"'"+year);
	}
	
	public static String findLastDateOfQuarter(String quarterInfo) {	//yyyy/MM/dd	//Q_'YY
		if(isQuarterValid(quarterInfo)) {
			switch(Integer.parseInt(Character.toString(quarterInfo.charAt(1)))) {
				case 1:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"06-30";
				}
				case 2:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"09-30";
				}
				case 3:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"12-31";
				}
				case 4:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"03-31";
				}
				default:{
					return null;
				}
			}
		}
		else {
			return null;
		}		
	}
	
	public static String findFirstDateOfQuarter(String quarterInfo) {	//yyyy/MM/dd	//Q_'YY
		if(isQuarterValid(quarterInfo)) {
			switch(Integer.parseInt(Character.toString(quarterInfo.charAt(1)))) {
				case 1:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"04-01";
				}
				case 2:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"07-01";
				}
				case 3:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"10-01";
				}
				case 4:{
					return "20"+Integer.parseInt(quarterInfo.substring(3))+"-"+"01-01";
				}
				default:{
					return null;
				}
			}
		}
		else {
			return null;
		}		
	}
	
	public static boolean isQuarterValid(String quarterInfo) {
		boolean validityFlag = false;
		try {
			if(quarterInfo.length()==5) {
				if(Character.toString(quarterInfo.charAt(0)).equalsIgnoreCase("Q")) {
					if(Character.toString(quarterInfo.charAt(2)).equals("'")) {
						if(Integer.parseInt(quarterInfo.substring(3))>=0) {
							validityFlag = (Integer.parseInt(Character.toString(quarterInfo.charAt(1)))>4 || 
									Integer.parseInt(Character.toString(quarterInfo.charAt(1)))<1) ? false : true;
						}
					}
				}
			}			
			return validityFlag;
		}
		catch(Exception exception) {
			return false;
		}		
	}
	
	public static String findNextQuarter(String quarterInfo) {
		if(isQuarterValid(quarterInfo)){
			if(Integer.parseInt(Character.toString(quarterInfo.charAt(1)))!=3) {
				return "Q"+(((Integer.parseInt(Character.toString(quarterInfo.charAt(1))))%4)+1)
						+"'"+(Integer.parseInt(quarterInfo.substring(3)));
			}
			else {
				return "Q4'"+(Integer.parseInt(quarterInfo.substring(3))+1);
			}
		}
		else {
			return null;
		}
	}
	
	public static Boolean isQuarterDirectoryPresent (String quarterName) {
		return getAllQuarterList().contains(quarterName);		
	}
	
	public static String findYearByQuarter(String quarterInfo) {
		if(isQuarterValid(quarterInfo))
			return ("20"+quarterInfo.substring(3));
		else
			return null; 
	}
	
	public static List<String> findQuartersByYear(String year){
		String minYear = year.substring(2);
		List<String> yearlyQuartersList = new ArrayList<String>();
		yearlyQuartersList.add("Q4'"+minYear);
		yearlyQuartersList.add("Q1'"+minYear);
		yearlyQuartersList.add("Q2'"+minYear);
		yearlyQuartersList.add("Q3'"+minYear);
		return yearlyQuartersList;				
	}
	
	public static void main (String args[]) {
		System.out.println(findQuartersByYear("2019"));
	}
}
