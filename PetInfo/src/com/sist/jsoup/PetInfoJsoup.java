package com.sist.jsoup;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.PetDAO;
import com.sist.vo.PetInfoVO;

public class PetInfoJsoup {
	public void petInfoDataCollection() {
		PetDAO dao=PetDAO.newInstance();
		try {
			for(int p=1;p<=30;p++) {
				String url="https://mypetlife.co.kr/popular-posts/page/"+p+"/?filter=dog";
				Document doc=Jsoup.connect(url).get();
				
				Elements posts=doc.select("li.bl-post");
				for(Element post : posts) {
					try {
						PetInfoVO vo=new PetInfoVO();
						vo.setTitle(post.selectFirst("div.post-title").text());
						vo.setPoster(post.selectFirst("img").attr("src"));
						
						Elements cates=post.select("div#product-post-category a");
						if(!cates.isEmpty()) {
							List<String> catList=new ArrayList<String>();
							for(Element c : cates) {
								catList.add(c.text());
							}
							vo.setCategory(String.join(", ", catList));
						} else {
							vo.setCategory("");
						}
						
						Elements tagEls=post.select("div#product-post-tags a");
						List<String> tagList=new ArrayList<String>();
						for(Element t : tagEls) {
							String txt=t.text().replaceAll("^#+", "")
											.replaceAll(",\\s*$", "")
											.trim();
							if(!txt.isEmpty()) {
								tagList.add(txt);
							}
						}
						if(!tagList.isEmpty()) {
							vo.setTags(String.join(", ", tagList));
						} else {
							vo.setTags("");
						}
						
						System.out.println(vo.getTitle()
								+" "+vo.getPoster()
								+" "+vo.getCategory()
								+" "+vo.getTags());
						
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
