package com.ck.po;

import java.util.List;

/**
 * 文件夹持久层
 * 
 * @author ALI
 *
 */
public class Folder {
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
	 * 一对多。文件
	 */
	private List<File> file;

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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", folderid=" + folderid + ", fileid=" + fileid + ", file=" + file + "]";
	}

}
