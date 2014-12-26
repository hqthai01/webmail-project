package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Attachment;

public class AttachmentDAOMock {
	public static List<Attachment> getAttachmentsOf(int mailId){
		Random rd = new Random();
		try {
			List<Attachment> list = new ArrayList<Attachment>();
			for(int i =0; i<rd.nextInt(10); i++){
				Attachment a = new Attachment();
				a.setFileName("Attachment sample");
				a.setFilePath("demo.abc");
				list.add(a);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
