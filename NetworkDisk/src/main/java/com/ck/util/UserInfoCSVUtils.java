package com.ck.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ck.vo.UserInfoVo;

public class UserInfoCSVUtils {

	public static List<UserInfoVo> read(String file) {
		List<UserInfoVo> list = new ArrayList<UserInfoVo>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			String[] cell = null;
			br.readLine(); // 去除首行
			while ((line = br.readLine()) != null) {
				cell = line.split(",");
				UserInfoVo u = new UserInfoVo();
				u.setName(cell[0]);
				u.setNumber(cell[1]);
				u.setIdcard(cell[2]);
				u.setType(Integer.parseInt(cell[3]));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
