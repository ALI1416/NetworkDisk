package com.ck.vo;

/**
 * 文件夹持久层
 * 
 * @author ALI
 *
 */
public class FolderVo {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 文件夹id
	 */
	private Integer folderid;
	/**
	 * 文件id
	 */
	private Integer fileid;
	/**
	 * 排序
	 */
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFolderid() {
		return folderid;
	}

	public void setFolderid(Integer folderid) {
		this.folderid = folderid;
	}

	public Integer getFileid() {
		return fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}

	@Override
	public String toString() {
		return "FolderVo [id=" + id + ", folderid=" + folderid + ", fileid=" + fileid + ", orderBy=" + orderBy + "]";
	}

}
