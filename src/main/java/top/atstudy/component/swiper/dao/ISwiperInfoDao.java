package top.atstudy.component.swiper.dao;


import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTO;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTOExample;
import top.atstudy.component.swiper.vo.req.SwiperInfoQuery;

import java.util.List;

/**
 * ISwiperInfoDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface ISwiperInfoDao {

    /**
     * 根据ID
     *
     * @return {@link SwiperInfoDTO}
     */
    SwiperInfoDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link SwiperInfoDTO}
     */
    Page<SwiperInfoDTO> findByExample(SwiperInfoDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List< SwiperInfoDTO >}
     */
    List<SwiperInfoDTO> listByExample(SwiperInfoDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link SwiperInfoDTO}
     */
    SwiperInfoDTO getByExample(SwiperInfoDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(SwiperInfoDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link SwiperInfoQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link SwiperInfoDTO}
     */
    Page<SwiperInfoDTO> findByQuery(SwiperInfoQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link SwiperInfoQuery}
     * @return List {@link List< SwiperInfoDTO >}
     */
    List<SwiperInfoDTO> listByQuery(SwiperInfoQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(SwiperInfoQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link SwiperInfoDTO}
     * @return 操作flag
     */
    boolean create(SwiperInfoDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link SwiperInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link SwiperInfoDTO}
     */
    SwiperInfoDTO createAndGet(SwiperInfoDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link SwiperInfoDTO}
     * @return 操作flag
     */
    boolean update(SwiperInfoDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link SwiperInfoDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link SwiperInfoDTO}
     */
    SwiperInfoDTO updateAndGet(SwiperInfoDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link SwiperInfoDTO}
     * @return 操作flag
     */
    boolean remove(SwiperInfoDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link SwiperInfoDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<SwiperInfoDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link SwiperInfoDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<SwiperInfoDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link SwiperInfoDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<SwiperInfoDTO> targets);
}