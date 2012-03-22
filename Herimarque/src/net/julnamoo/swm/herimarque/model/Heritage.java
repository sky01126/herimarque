package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

/**
 * Pojo class for each heritage item
 * 
 * @author Julie_air
 *
 */
public class Heritage implements Serializable{

	//17 fields
	String crltsDc;
	String crltsNm;
	String crltsNmChcrt;
	String crltsNo;
	String crltsNoNm;
	String ctrdCd;
	String ctrdNm;
	String ageCd;
	String imageUrl;
	String imageYn;
	String listImageUrl;
	String itemCd;
	String itemNm;
	String signguCd;
	String signguNm;
	String XCnts;
	String YCnts;
	String sin_x_rad;
	String cos_x_rad;
	String sin_y_rad;
	String cos_y_rad;
	
	public String getCrltsDc() {
		return crltsDc;
	}
	public void setCrltsDc(String crltsDc) {
		this.crltsDc = crltsDc;
	}
	public String getCrltsNm() {
		return crltsNm;
	}
	public void setCrltsNm(String crltsNm) {
		this.crltsNm = crltsNm;
	}
	public String getCrltsNmChcrt() {
		return crltsNmChcrt;
	}
	public void setCrltsNmChcrt(String crltsNmChcrt) {
		this.crltsNmChcrt = crltsNmChcrt;
	}
	public String getCrltsNo() {
		return crltsNo;
	}
	public void setCrltsNo(String crltsNo) {
		this.crltsNo = crltsNo;
	}
	public String getCrltsNoNm() {
		return crltsNoNm;
	}
	public void setCrltsNoNm(String crltsNoNm) {
		this.crltsNoNm = crltsNoNm;
	}
	public String getCtrdCd() {
		return ctrdCd;
	}
	public void setCtrdCd(String ctrdCd) {
		this.ctrdCd = ctrdCd;
	}
	public String getCtrdNm() {
		return ctrdNm;
	}
	public void setCtrdNm(String ctrdNm) {
		this.ctrdNm = ctrdNm;
	}
	public String getAgeCd() {
		return ageCd;
	}
	public void setAgeCd(String ageCd) {
		this.ageCd = ageCd;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageYn() {
		return imageYn;
	}
	public void setImageYn(String imageYn) {
		this.imageYn = imageYn;
	}
	public String getListImageUrl() {
		return listImageUrl;
	}
	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getSignguCd() {
		return signguCd;
	}
	public void setSignguCd(String signguCd) {
		this.signguCd = signguCd;
	}
	public String getSignguNm() {
		return signguNm;
	}
	public void setSignguNm(String signguNm) {
		this.signguNm = signguNm;
	}
	public String getXCnts() {
		return XCnts;
	}
	public void setXCnts(String xCnts) {
		XCnts = xCnts;
	}
	public String getYCnts() {
		return YCnts;
	}
	public void setYCnts(String yCnts) {
		YCnts = yCnts;
	}
	
	public String getSin_x_rad() {
		return sin_x_rad;
	}
	public void setSin_x_rad(String sin_x_rad) {
		this.sin_x_rad = sin_x_rad;
	}
	public String getCos_x_rad() {
		return cos_x_rad;
	}
	public void setCos_x_rad(String cos_x_rad) {
		this.cos_x_rad = cos_x_rad;
	}
	public String getSin_y_rad() {
		return sin_y_rad;
	}
	public void setSin_y_rad(String sin_y_rad) {
		this.sin_y_rad = sin_y_rad;
	}
	public String getCos_y_rad() {
		return cos_y_rad;
	}
	public void setCos_y_rad(String cos_y_rad) {
		this.cos_y_rad = cos_y_rad;
	}
	@Override
	public String toString() {
		return crltsNm;
	}
}
