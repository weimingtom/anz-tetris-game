package com.huynt.samsung;

import java.util.HashMap;
import java.util.Map.Entry;



public class PConfig {

	public static final String AREA[] = {"T.P Hồ Chí Minh", "Hà Nội", "An Giang",
	           "Bình Định", "Bắc Giang", "Bắc Ninh", "Bắc cạn",
	           "Bình Thuận", "Bình Dương", "Bến Tre", "Bạc Liêu",
	           "Bình Phước", "Cần Thơ", "Cà Mau", "Cao Bằng", "Đà nẵng",
	           "Điện Biên", "Đắk Nông", "Đồng Nai", "Đắk Lắk", "Đà Lạt",
	           "Đồng Tháp", "Hải Dương", "Hòa Bình", "Hà Tĩnh", "Hưng Yên",
	           "Hậu Giang", "Hà Tây", "Hà Giang", "Hải Phòng", "Kiên Giang",
	           "Kon Tum", "Lào Cai", "Lạng Sơn", "Long An", "Lai Châu",
	           "Ninh Thuận", "Nam Định", "Nha Trang", "Ninh Bình",
	           "Phủ Lý", "Pleiku", "Phú Yên", "Quảng Nam", "Quảng Ninh",
	           "Quảng Ngãi", "Quảng Bình", "Quảng Trị", "Sóc Trăng",
	           "Sơn La", "Tuyên Quang", "Thái Nguyên", "Thanh Hóa",
	           "Tây Ninh", "Thừa Thiên Huế", "Tiền Giang", "Thái Bình",
	           "Việt Trì", "Vĩnh Phúc", "Vũng Tàu", "Vinh", "Vĩnh Long", "Yên Bái"};

	public static final String ID_AREA[] = {"44", "58", "96", "94", "80", "81", "86",
	           "39", "45", "53", "59", "60", "56", "74", "83", "92",
	           "70", "71", "62", "42", "43", "51", "48", "4", "28",
	           "63", "54", "65", "68", "88", "73", "40", "69", "82",
	           "50", "127", "100", "64", "37", "46", "49", "41", "99",
	           "119", "120", "34", "29", "30", "57", "78", "84", "85",
	           "106", "66", "31", "52", "47", "76", "77", "61", "111", "95", "87"};
	
	public static final String API_WEATHER = "http://pdaviet.net/kawa/projects/samsung/thoitietvn/thoitiet1.2.php?MaVung=";
	
	public static final String SHARE_PREFERENCE_NAME = "Thoitiet";
	
	public static final HashMap<String, Integer> mapIcon = new HashMap<String, Integer>();
	static {
		mapIcon.put("40", new Integer(R.drawable.rainwithbolt));
		mapIcon.put("90", new Integer(R.drawable.rain));
		mapIcon.put("110", new Integer(R.drawable.rain2));
		mapIcon.put("260", new Integer(R.drawable.cloud));
		mapIcon.put("270", new Integer(R.drawable.cloudinnight));
		mapIcon.put("300", new Integer(R.drawable.cloudinday));
		mapIcon.put("310", new Integer(R.drawable.star));
		mapIcon.put("320", new Integer(R.drawable.sun));
		mapIcon.put("330", new Integer(R.drawable.cloudinnight2));
		mapIcon.put("340", new Integer(R.drawable.cloudinday2));
		mapIcon.put("370", new Integer(R.drawable.cloudinnight3));
		mapIcon.put("390", new Integer(R.drawable.rainwithsun));
		mapIcon.put("450", new Integer(R.drawable.rainwithmoon));
	}
	
	public static int getIdImageForWeather(String strId){
		Integer integerId = mapIcon.get(strId);
		if(integerId != null)
			return integerId.intValue();
		return R.drawable.sun;
	}
}
