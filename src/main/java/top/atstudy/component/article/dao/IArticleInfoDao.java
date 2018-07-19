package top.atstudy.component.article.dao;


import top.atstudy.component.article.dao.dto.ArticleInfoDTO;
import top.atstudy.component.article.dao.dto.ArticleInfoDTOExample;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;

import java.util.List;

/**
 * IArticleInfoDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IArticleInfoDao {

    /**
     * 根据ID
     *
     * @return {@link ArticleInfoDTO}
     */
    ArticleInfoDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link ArticleInfoDTO}
     */
    Page<ArticleInfoDTO> findByExample(ArticleInfoDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List<ArticleInfoDTO>}
     */
    List<ArticleInfoDTO> listByExample(ArticleInfoDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link ArticleInfoDTO}
     */
    ArticleInfoDTO getByExample(ArticleInfoDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(ArticleInfoDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link ArticleInfoQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link ArticleInfoDTO}
     */
    Page<ArticleInfoDTO> findByQuery(ArticleInfoQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link ArticleInfoQuery}
     * @return List {@link List<ArticleInfoDTO>}
     */
    List<ArticleInfoDTO> listByQuery(ArticleInfoQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(ArticleInfoQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link ArticleInfoDTO}
     * @return 操作flag
     */
    boolean create(ArticleInfoDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link ArticleInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link ArticleInfoDTO}
     */
    ArticleInfoDTO createAndGet(ArticleInfoDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link ArticleInfoDTO}
     * @return 操作flag
     */
    boolean update(ArticleInfoDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link ArticleInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link ArticleInfoDTO}
     */
    ArticleInfoDTO updateAndGet(ArticleInfoDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link ArticleInfoDTO}
     * @return 操作flag
     */
    boolean remove(ArticleInfoDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link ArticleInfoDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<ArticleInfoDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link ArticleInfoDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<ArticleInfoDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link ArticleInfoDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<ArticleInfoDTO> targets);
}