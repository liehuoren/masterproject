package com.master.entity.area;

import java.math.BigDecimal;
import java.util.List;

public class Area {
	
	private Integer id;//主键
	private String pingyin;//拼音首字母
	private String areaname;//区域名称
	private Integer parentid;//上级区域ID
	private String shortname;//区域简称
	private BigDecimal lng;//区域所属经度
	private BigDecimal lat;//区域所属纬度
	private Integer level;//区域级别{省级:1,市级:2,区县级:3}
	private Integer sort;//排序
	private String wait1;//备用字段1
	private String wait2;//备用字段2
	private String wait3;//备用字段3
	private String wait4;//备用字段4
	private String target;
	private Area area;
	private List<Area> subArea;
	private boolean hasArea = false;
	private String treeurl;
	
	
	//临时字段
	private String parentname;//父级区域名称
	private Integer parent_par_id;//父级区域的父级编号
	
	public Integer getParent_par_id() {
		return parent_par_id;
	}
	public void setParent_par_id(Integer parent_par_id) {
		this.parent_par_id = parent_par_id;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	/**
	 * 主键
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 区域名称
	 * @return
	 */
	public String getAreaname() {
		return areaname;
	}
	/**
	 * 上级区域ID
	 * @return
	 */
	public Integer getParentid() {
		return parentid;
	}
	/**
	 * 区域简称
	 * @return
	 */
	public String getShortname() {
		return shortname;
	}
	/**
	 * 区域所在经度
	 * @return
	 */
	public BigDecimal getLng() {
		return lng;
	}
	/**
	 * 区域所在纬度
	 * @return
	 */
	public BigDecimal getLat() {
		return lat;
	}
	/**
	 * 区域级别{省级:1,市级:2,区县级:3}
	 * @return
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 排序字段
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 备用1 
	 * @return
	 */
	public String getWait1() {
		return wait1;
	}
	/**
	 * 备用2
	 * @return
	 */
	public String getWait2() {
		return wait2;
	}
	/**
	 * 备用3
	 * @return
	 */
	public String getWait3() {
		return wait3;
	}
	/**
	 * 备用4
	 * @return
	 */
	public String getWait4() {
		return wait4;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 区域名称
	 * @param areaname
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	/**
	 * 上级区域ID
	 * @param parentid
	 */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	/**
	 * 区域简称
	 * @param shortname
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	/**
	 * 区域经度
	 * @param lng
	 */
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	/**
	 * 区域纬度
	 * @param lat
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	/**
	 * 区域级别
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setWait1(String wait1) {
		this.wait1 = wait1;
	}
	public void setWait2(String wait2) {
		this.wait2 = wait2;
	}
	public void setWait3(String wait3) {
		this.wait3 = wait3;
	}
	public void setWait4(String wait4) {
		this.wait4 = wait4;
	}
	public String getPingyin() {
		return pingyin;
	}
	public void setPingyin(String pingyin) {
		this.pingyin = pingyin;
	}
	public String getTarget() {
		return target;
	}
	public Area getArea() {
		return area;
	}
	public List<Area> getSubArea() {
		return subArea;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public void setSubArea(List<Area> subArea) {
		this.subArea = subArea;
	}
	public boolean isHasArea() {
		return hasArea;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setHasArea(boolean hasArea) {
		this.hasArea = hasArea;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	

}
