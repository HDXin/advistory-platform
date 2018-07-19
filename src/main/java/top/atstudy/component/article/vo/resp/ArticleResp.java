package top.atstudy.component.article.vo.resp;


import top.atstudy.component.article.vo.ArticleContentEntity;
import top.atstudy.component.article.vo.ContentItem;
import top.atstudy.component.base.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleResp extends ArticleInfoResp {

    protected List<ContentItem> contentItemList = new ArrayList<>();

    public List<ContentItem> getContentItemList() {
        return contentItemList;
    }

    public void setContentItemList(List<ContentItem> contentItemList) {
        this.contentItemList = contentItemList;
    }

    public static ArticleResp toArticleResp(ArticleInfoResp articleInfoResp){

        ArticleResp articleResp = BeanUtils.copyProperties(articleInfoResp, ArticleResp.class);
        ArticleContentEntity contentEntity = ArticleContentEntity.toJavaObj(articleInfoResp.getContent());
        if(contentEntity != null){
            articleResp.setContent(null);
            articleResp.setContentItemList(contentEntity.getContentItemList());
        }

        return articleResp;
    }

}
