package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import model.Mail;

/**
 * @author hqthai01
 * Jan 1, 2015
 */
public class SortObjectUtils {
	public static SortedSet<Mail> sort(SortedSet<Mail> mails){
		List<Mail> list = new ArrayList<Mail>();
		Iterator<Mail> iter = mails.iterator();
		while(iter.hasNext()){
			list.add(iter.next());
		}
		
		Collections.sort(list);
		TreeSet<Mail> out = new TreeSet<Mail>();
		Mail temp;
		for(int i = 1 ; i <list.size()-1;i++){
			for(int j =list.size() -1 ; j>=i;j--){
				if(list.get(j).compareTo(list.get(j-1)) >0){
					temp = list.get(j);
					list.set(j,list.get(j-1));
					list.set(j-1,temp);
				}
			}
		}
		
		System.out.println();
		out.addAll(list);
		return out;
	}
}
