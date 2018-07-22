package top.atstudy.component.user.dao;

import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.user.dao.dto.AppUserDTO;
import top.atstudy.component.user.dao.dto.AppUserDTOExample;
import top.atstudy.component.user.vo.req.AppUserQuery;
import java.util.List;

/**
 * IAppUserDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAppUserDao {

    /**
     * 根据ID
     *
     * @return {@link AppUserDTO}
     */
    AppUserDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AppUserDTO}
     */
    Page<AppUserDTO> findByExample(AppUserDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List<AppUserDTO>}
     */
    List<AppUserDTO> listByExample(AppUserDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link AppUserDTO}
     */
    AppUserDTO getByExample(AppUserDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(AppUserDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link AppUserQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AppUserDTO}
     */
    Page<AppUserDTO> findByQuery(AppUserQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link AppUserQuery}
     * @return List {@link List<AppUserDTO>}
     */
    List<AppUserDTO> listByQuery(AppUserQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(AppUserQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AppUserDTO}
     * @return 操作flag
     */
    boolean create(AppUserDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link AppUserDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AppUserDTO}
     */
    AppUserDTO createAndGet(AppUserDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AppUserDTO}
     * @return 操作flag
     */
    boolean update(AppUserDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link AppUserDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AppUserDTO}
     */
    AppUserDTO updateAndGet(AppUserDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link AppUserDTO}
     * @return 操作flag
     */
    boolean remove(AppUserDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link AppUserDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<AppUserDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link AppUserDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<AppUserDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link AppUserDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<AppUserDTO> targets);

    /**
     * 根据 openid 获取用户信息
     * @param openid
     * @return
     */
    AppUserDTO getByOpenid(String openid);
}