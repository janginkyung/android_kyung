package org.androidtown.mission13;

/**
 * 일자 정보를 담기 위한 클래스 정의
 * 
 * @author Mike
 *
 */
public class MonthItem {

	private int dayValue;
	private String weathervalue=null ;
	public MonthItem() {
		
	}
	public MonthItem(int day,String weather) {
		dayValue = day;
		weathervalue = weather;
	}
	public MonthItem(int day) {
		dayValue = day;
	}
	
	public int getDay() {
		return dayValue;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}
	public String getWeather() {//������������
		return weathervalue;
	}

	public void setWeather(String weather) {	//���� �־��ֱ�
		this.weathervalue = weather;
	}
	
}
