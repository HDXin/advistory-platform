package top.atstudy.component.setting.dao;

import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import top.atstudy.component.setting.dao.dto.SettingDTOExample;
import top.atstudy.component.setting.vo.req.SettingQuery;
import java.util.List;

/**
 * ISettingDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface ISettingDao {

    /**
     * 根据ID
     *
     * @return {@link SettingDTO}
     */
    SettingDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link SettingDTO}
     */
    Page<SettingDTO> findByExample(SettingDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List<SettingDTO>}
     */
    List<SettingDTO> listByExample(SettingDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link SettingDTO}
     */
    SettingDTO getByExample(SettingDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(SettingDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link SettingQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link SettingDTO}
     */
    Page<SettingDTO> findByQuery(SettingQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link SettingQuery}
     * @return List {@link List<SettingDTO>}
     */
    List<SettingDTO> listByQuery(SettingQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(SettingQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link SettingDTO}
     * @return 操作flag
     */
    boolean create(SettingDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link SettingDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link SettingDTO}
     */
    SettingDTO createAndGet(SettingDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link SettingDTO}
     * @return 操作flag
     */
    boolean update(SettingDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link SettingDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link SettingDTO}
     */
    SettingDTO updateAndGet(SettingDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link SettingDTO}
     * @return 操作flag
     */
    boolean remove(SettingDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link SettingDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<SettingDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link SettingDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<SettingDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link SettingDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<SettingDTO> targets);
}