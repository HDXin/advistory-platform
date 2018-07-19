package top.atstudy.component.article.vo.req;

import top.atstudy.component.article.vo.ArticleContentEntity;
import top.atstudy.component.article.vo.ContentItem;
import top.atstudy.component.base.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleReq extends ArticleInfoReq {

    private List<ContentItem> contentItemList = new ArrayList<>();

    public List<ContentItem> getContentItemList() {
        return contentItemList;
    }

    public void setContentItemList(List<ContentItem> contentItemList) {
        this.contentItemList = contentItemList;
    }

    public ArticleInfoReq toArticleInfoReq(){

        ArticleContentEntity contentEntity = new ArticleContentEntity();
        contentEntity.setContentItemList(this.getContentItemList());

        ArticleInfoReq articleInfoReq = BeanUtils.copyProperties(this, ArticleInfoReq.class);
        articleInfoReq.setContent(contentEntity.toJsonStr());

        return articleInfoReq;
    }
}
