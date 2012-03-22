package net.julnamoo.swm.herimarque.util;

import net.julnamoo.R;

public class Constants {

	public static String[] itemFields = {
		"crltsDc", "crltsNm", "crltsNmChcrt", "crltsNo", "crltsNoNm",
		"ctrdCd", "ctrdNm", "ageCd", "imageUrl", "imageYn", "listImageUrl",
		"itemCd", "itemNm", "signguCd", "signguNm", "XCnts", "YCnts"
	};

	/**
	 * crltsNm CHAR, crltsNmChcrt CHAR, crltsNo INTEGER, crltsNoNm CHAR, crltsDc CHAR, ctrdCd CHAR, ctrdNm CHAR, imageUrl CHAR, listImageUrl CHAR, itemCd CHAR, itemNm CHAR, signguCd CHAR, signguNm CHAR, XCnts DOUBLE, YCnts DOUBLE
	 */
	public static String[] columns = {
		"_id",
		"crltsDc", "crltsNm", "crltsNmChcrt", "crltsNo", "crltsNoNm",
		"ctrdCd", "ctrdNm", "ageCd", "imageUrl", "imageYn", "listImageUrl",
		"itemCd", "itemNm", "signguCd", "signguNm", "XCnts", "YCnts"
	};

	//code 14 is the exception with 사적 & 명승
	public static String[] kindCode = {
			"11", "12", "13", "15", "16", "17", "18", "79",
			"21", "22", "23", "24", "31"
		};

	public static String[] kindImgsCD = {
		"국보", "보물", "사적", "명승", "천연기념물", "중요 무형문화재", "중요 민속자료", "등록문화재",
		"시도 유형문화재", "시도 무형문화재", "시도 기념물", "시도 민속자료", "문화재 자료"
	};

	public static int[] kindImgs = {
		R.drawable.kind_national_treasure, R.drawable.kind_treasure, R.drawable.kind_historical_site,
		R.drawable.kind_spot_place, R.drawable.kind_natural_monument, R.drawable.kind_intangible_cultural_asset,
		R.drawable.kind_folklore_asset, R.drawable.kind_registered_asset, R.drawable.kind_local_tangible_cultural_asset,
		R.drawable.kind_local_intangible_cultural_asset, R.drawable.kind_local_monument, R.drawable.kind_local_folklore_asset,
		R.drawable.kind_cultural_asset
	}; 
	
	public static String[] ctrdCode = {
			"11", "21", "22", "23", "24", "25", "26", 
			"31", "32", "33", "34", "35", "36", "37", "38", "39", "ZZ"
	};
	
	public static String[] ctrdName = {
			"서울", "부산", "대구", "인천", "광주", "대전", "울산", 
			"경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주", "기타"
	};

	public static final String DB_NAME = "heritage";
	public static final int DB_VERSION = 1;
	public static final String HERITAGE_TABLE_NAME = "heritage";
	public static final String TRACKING_TABLE_LANE = "herimarque";
}
