package com.sist.jsoup;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.PetDAO;
import com.sist.vo.PetInfo2VO;

public class PetInfo2Jsoup {
	public void petInfo2DataCollection() {
		PetDAO dao=PetDAO.newInstance();
		try {
			for(int p=1;p<=20;p++) {
				String url="https://blog.fitpetmall.com/blog/category/dogs/page/"+p;
				Document doc=Jsoup.connect(url).get();
				
				Elements articles = doc.select("article.post");
				
				for(Element article : articles) {
					try {
						PetInfo2VO vo=new PetInfo2VO();
						vo.setTitle(article.selectFirst("h3.title a").text());
						vo.setPoster(article.selectFirst("span.post-featured-img img").attr("src"));
						
						Elements cates=article.select("span.meta-category a");
						if(!cates.isEmpty()) {
							List<String> catList=new ArrayList<String>();
							for(Element c : cates) {
								catList.add(c.text());
							}
							vo.setCategory(String.join(", ", catList));
						}
						
						vo.setPreview(article.selectFirst("div.excerpt").text());
						
						System.out.println(vo.getTitle()
								+" "+vo.getPoster()
								+" "+vo.getCategory()
								+" "+vo.getPreview());
						
						dao.petinfo2Insert(vo);
					} catch(Exception ex) {}
				}
			}
		} catch(Exception ex) {}
		System.out.println("데이터수집 완료!!");
	}
	public static void main(String[] args) {
		PetInfo2Jsoup pi=new PetInfo2Jsoup();
		pi.petInfo2DataCollection();
	}
}
