package net.julnamoo.swm.herimarque;

import net.julnamoo.R;

public class Constants {

	public static int[] kindImgs = {
		R.drawable.kind_national_treasure, R.drawable.kind_treasure, R.drawable.kind_historical_site,
		R.drawable.kind_spot_place, R.drawable.kind_natural_monument, R.drawable.kind_intangible_cultural_asset,
		R.drawable.kind_folklore_asset, R.drawable.kind_registered_asset, R.drawable.kind_local_tangible_cultural_asset,
		R.drawable.kind_local_intangible_cultural_asset, R.drawable.kind_local_monument, R.drawable.kind_local_folklore_asset,
		R.drawable.kind_cultural_asset
	}; 

	public static String[] kindImgsCD = {
		"국보", "보물", "사적", "명승", "천연기념물", "무형문화재", "민속문화재", "등록문화재",
		"시도 유형문화재", "시도 무형문화재", "시도 기념물", "시도 민속문화재", "문화재 자료"
	};

	public static String[] itemFields = {
		"crltsDc", "crltsNm", "crltsNmChcrt", "crltsNo", "crltsNoNm",
		"ctrdCd", "ctrdNm", "ageCd", "imageUrl", "imageYn", "listImageUrl",
		"itemCd", "itemNm", "signguCd", "signguNm", "XCnts", "YCnts"
	};
}
