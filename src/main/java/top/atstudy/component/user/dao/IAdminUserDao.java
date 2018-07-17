package top.atstudy.component.user.dao;


import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.dao.dto.AdminUserDTOExample;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import java.util.List;

/**
 * IAdminUserDao 接口类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAdminUserDao {

    /**
     * 根据ID
     *
     * @return {@link AdminUserDTO}
     */
    AdminUserDTO getById(Long id);

    /**
     * 根据条件获得分页 Item 信息
     *
     * @param example mybatis criteria example
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AdminUserDTO}
     */
    Page<AdminUserDTO> findByExample(AdminUserDTOExample example, Pagination... pagination);


    /**
     * 根据条件获得 Item 信息列表
     *
     * @param example mybatis criteria example
     * @return List {@link List< AdminUserDTO >}
     */
    List<AdminUserDTO> listByExample(AdminUserDTOExample example);

    /**
     * 根据条件获得 singlet item 信息
     *
     * @param example mybatis criteria example
     * @return Item {@link AdminUserDTO}
     */
    AdminUserDTO getByExample(AdminUserDTOExample example);

    /**
     * 根据条件获得 统计数值 信息
     *
     * @param example mybatis criteria example
     * @return 统计数值
     */
    Long countByExample(AdminUserDTOExample example);


    /**
     * 根据自定义Query获得具备分页的 Item 列表
     *
     * @param query 自定义Query {@link AdminUserQuery}
     * @return 具有分页功能的Page包装集合 {@link Page}, 其中囊括的Item类型 {@link AdminUserDTO}
     */
    Page<AdminUserDTO> findByQuery(AdminUserQuery query);

    /**
     * 根据自定义Query获得 Item 列表
     *
     * @param query 自定义Query {@link AdminUserQuery}
     * @return List {@link List< AdminUserDTO >}
     */
    List<AdminUserDTO> listByQuery(AdminUserQuery query);

    /**
     * 根据自定义Query获得 统计数值
     *
     * @param query 自定义Query
     * @return 统计数值
     */
    Long countByQuery(AdminUserQuery query);

    /**
     * 创建 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AdminUserDTO}
     * @return 操作flag
     */
    boolean create(AdminUserDTO target);

    /**
     * 创建并获得最新Item
     *
     * @param target Item {@link AdminUserDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AdminUserDTO}
     */
    AdminUserDTO createAndGet(AdminUserDTO target);

    /**
     * 更新 Item
     * *注意* 参数作为瞬时数据, 严密逻辑下, 推荐不要在之后逻辑中使用
     *
     * @param target Item {@link AdminUserDTO}
     * @return 操作flag
     */
    boolean update(AdminUserDTO target);

    /**
     * 更新 Item
     *
     * @param target Item {@link AdminUserDTO}
     * @return 持久数据, 可在之后的逻辑中使用. {@link AdminUserDTO}
     */
    AdminUserDTO updateAndGet(AdminUserDTO target);


    /**
     * 根据 Item 删除
     *
     * @param target Item {@link AdminUserDTO}
     * @return 操作flag
     */
    boolean remove(AdminUserDTO target);

    /**
     * 批量创建 Item
     *
     * @param targets List Item 组 {@link AdminUserDTO}
     * @return 
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchCreate(List<AdminUserDTO> targets);

    /**
     * 批量创建 更新
     *
     * @param targets List Item 组 {@link AdminUserDTO}
     * @return
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchUpdate(List<AdminUserDTO> targets);

    /**
     * 批量删除 Item
     * *注意* 逻辑删除, 非物理删除
     *
     * @param targets List Item 组 {@link AdminUserDTO}
     * @return 操作flag
     * 操作flag
     * 全部成功为 true
     * 至少存在一条失败则为 false
     * 这里需要业务逻辑层做回滚, 生成的代码没有做回滚操作
     */
    boolean batchRemove(List<AdminUserDTO> targets);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    AdminUserDTO getByName(String userName);
}