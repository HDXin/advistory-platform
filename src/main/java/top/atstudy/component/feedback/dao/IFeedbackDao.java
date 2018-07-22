package top.atstudy.component.feedback.dao;

import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.feedback.dao.dto.FeedbackDTO;
import top.atstudy.component.feedback.dao.dto.FeedbackDTOExample;
import top.atstudy.component.feedback.vo.req.FeedbackQuery;
import java.util.List;

/**
 * IFeedbackDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IFeedbackDao {

    /**
     * 根据ID
     *
     * @return {@link FeedbackDTO}
     */
    FeedbackDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link FeedbackDTO}
     */
    Page<FeedbackDTO> findByExample(FeedbackDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List< FeedbackDTO >}
     */
    List<FeedbackDTO> listByExample(FeedbackDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link FeedbackDTO}
     */
    FeedbackDTO getByExample(FeedbackDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(FeedbackDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link FeedbackQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link FeedbackDTO}
     */
    Page<FeedbackDTO> findByQuery(FeedbackQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link FeedbackQuery}
     * @return List {@link List< FeedbackDTO >}
     */
    List<FeedbackDTO> listByQuery(FeedbackQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(FeedbackQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link FeedbackDTO}
     * @return 操作flag
     */
    boolean create(FeedbackDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link FeedbackDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link FeedbackDTO}
     */
    FeedbackDTO createAndGet(FeedbackDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link FeedbackDTO}
     * @return 操作flag
     */
    boolean update(FeedbackDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link FeedbackDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link FeedbackDTO}
     */
    FeedbackDTO updateAndGet(FeedbackDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link FeedbackDTO}
     * @return 操作flag
     */
    boolean remove(FeedbackDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link FeedbackDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<FeedbackDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link FeedbackDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<FeedbackDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link FeedbackDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<FeedbackDTO> targets);
}