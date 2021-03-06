package top.atstudy.component.article.vo.req;

import top.atstudy.component.article.dao.dto.ArticleInfoDTO;
import top.atstudy.component.base.util.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * ArticleInfo 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class ArticleInfoReq implements Serializable {


    private Long articleId;

    private String code;

    private String title;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public static ArticleInfoDTO convertToDTO(ArticleInfoReq req) {
        return req.convertToDTO();
    }

    public static List<ArticleInfoDTO> convertToDTO(List<ArticleInfoReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public ArticleInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, ArticleInfoDTO.class);
    }
}
