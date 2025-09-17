package com.sist.jsoup;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.sist.dao.*;
import com.sist.vo.*;

public class PetInfoJsoup {
	public void petInfoDataCollection() {
		PetInfoDAO dao=PetInfoDAO.newInstance();
		try {
			String url="";
			for(int p=1;p<=30;p++) {
				url="https://mypetlife.co.kr/popular-posts/page/"+p+"/?filter=dog";
					
				Document doc=Jsoup.connect(url).get();
				Elements title=doc.select("li.bl-post div.post-title");
				Elements poster=doc.select("li.bl-post img");
			
				for(int j=0;j<title.size();j++) {
					try {
						System.out.println(title.get(j).text()
								+" "+poster.get(j).attr("src"));
							
						PetInfoVO vo=new PetInfoVO();
						vo.setTitle(title.get(j).text());
						vo.setPoster(poster.get(j).attr("src"));
							
						dao.petinfoInsert(vo);
					} catch(Exception ex) {}
				}
			}
		} catch(Exception ex) {}	
		System.out.println("데이터수집 완료!!");
	}
	public static void main(String[] args) {
		PetInfoJsoup pi=new PetInfoJsoup();
		pi.petInfoDataCollection();
	}
}
