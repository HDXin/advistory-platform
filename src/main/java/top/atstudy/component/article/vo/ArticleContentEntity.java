package top.atstudy.component.article.vo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleContentEntity {

    private List<ContentItem> contentItemList = new ArrayList<>();

    public List<ContentItem> getContentItemList() {
        return contentItemList;
    }

    public void setContentItemList(List<ContentItem> contentItemList) {
        this.contentItemList = contentItemList;
    }

    public static ArticleContentEntity toJavaObj(String str){

        if(StringUtils.isBlank(str)){
            return null;
        }

        return JSONObject.parseObject(str).toJavaObject(ArticleContentEntity.class);
    }

    public String toJsonStr(){
        return JSONObject.toJSONString(this);
    }
}
