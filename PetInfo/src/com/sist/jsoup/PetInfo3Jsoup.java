package com.sist.jsoup;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.PetDAO;
import com.sist.vo.PetInfo3VO;

public class PetInfo3Jsoup {
    public void petInfo3DataCollection() {
        PetDAO dao=PetDAO.newInstance();
        try {
            for(int p=1;p<=5;p++) {
                String url="https://www.biteme.co.kr/blog/category/pet-story/page/"+p;
                Document doc=Jsoup.connect(url).get();

                Elements title=doc.select("div.row h2.entry-title");
                Elements poster=doc.select("div.row a.post-thumbnail img");
                Elements preview=doc.select("div.row div.entry-summary");

                for(int j=0;j<title.size();j++) {
                    try {
                        String titleText = title.get(j).text();
                        String posterUrl = poster.get(j).attr("src");
                        String previewText = preview.get(j).text();

                        List<String> catList = new ArrayList<>();
                        if (titleText.contains("강아지")) catList.add("강아지");
                        if (titleText.contains("고양이")) catList.add("고양이");
                        String category = String.join(", ", catList);

                        System.out.println(titleText
                                +" "+posterUrl
                                +" "+category
                                +" "+previewText);

                        PetInfo3VO vo=new PetInfo3VO();
                        vo.setTitle(titleText);
                        vo.setPoster(posterUrl);
                        vo.setCategory(category);
                        vo.setPreview(previewText);

                        dao.petinfo3Insert(vo);
                    } catch(Exception ex) {}
                }
            }
        } catch(Exception ex) {}
        System.out.println("데이터수집 완료!!");
    }
    public static void main(String[] args) {
        PetInfo3Jsoup pi=new PetInfo3Jsoup();
        pi.petInfo3DataCollection();
    }
}
