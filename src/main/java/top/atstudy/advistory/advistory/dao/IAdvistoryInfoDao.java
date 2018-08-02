package top.atstudy.advistory.advistory.dao;


import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTOExample;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;

import java.util.List;

/**
 * IAdvistoryInfoDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAdvistoryInfoDao {

    /**
     * 根据ID
     *
     * @return {@link AdvistoryInfoDTO}
     */
    AdvistoryInfoDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AdvistoryInfoDTO}
     */
    Page<AdvistoryInfoDTO> findByExample(AdvistoryInfoDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List< AdvistoryInfoDTO >}
     */
    List<AdvistoryInfoDTO> listByExample(AdvistoryInfoDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link AdvistoryInfoDTO}
     */
    AdvistoryInfoDTO getByExample(AdvistoryInfoDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(AdvistoryInfoDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link AdvistoryInfoQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AdvistoryInfoDTO}
     */
    Page<AdvistoryInfoDTO> findByQuery(AdvistoryInfoQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link AdvistoryInfoQuery}
     * @return List {@link List< AdvistoryInfoDTO >}
     */
    List<AdvistoryInfoDTO> listByQuery(AdvistoryInfoQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(AdvistoryInfoQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AdvistoryInfoDTO}
     * @return 操作flag
     */
    boolean create(AdvistoryInfoDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link AdvistoryInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AdvistoryInfoDTO}
     */
    AdvistoryInfoDTO createAndGet(AdvistoryInfoDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AdvistoryInfoDTO}
     * @return 操作flag
     */
    boolean update(AdvistoryInfoDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link AdvistoryInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AdvistoryInfoDTO}
     */
    AdvistoryInfoDTO updateAndGet(AdvistoryInfoDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link AdvistoryInfoDTO}
     * @return 操作flag
     */
    boolean remove(AdvistoryInfoDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link AdvistoryInfoDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<AdvistoryInfoDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link AdvistoryInfoDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<AdvistoryInfoDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link AdvistoryInfoDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<AdvistoryInfoDTO> targets);

    /**
     * 阅读量加1
     * @param advistoryId
     * @return
     */
    boolean addReadNumber(Long advistoryId);

    /**
     * 收藏量加1
     * @param advistoryId
     * @return
     */
    boolean addFavoriteNumber(Long advistoryId);

    /**
     * 收藏量减1
     * @param advistoryId
     * @return
     */
    boolean subFavoriteNumber(Long advistoryId);
}