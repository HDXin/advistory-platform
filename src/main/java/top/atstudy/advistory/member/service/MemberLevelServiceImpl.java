package top.atstudy.advistory.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.member.dao.IMemberLevelDao;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTOExample;
import top.atstudy.advistory.member.vo.req.MemberLevelQuery;
import top.atstudy.advistory.member.vo.req.MemberLevelReq;
import top.atstudy.advistory.member.vo.resp.MemberLevelResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.List;

/**
 * IMemberLevelService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class MemberLevelServiceImpl implements IMemberLevelService {
    /******* Fields Area *******/

    private IMemberLevelDao memberLevelDao;

    /******* Construction Area *******/
    public MemberLevelServiceImpl(@Autowired IMemberLevelDao memberLevelDao) {
        this.memberLevelDao = memberLevelDao;
    }

    @Override
    public MemberLevelResp getById(Long id) {
        MemberLevelResp target = null;
        MemberLevelDTOExample example = new MemberLevelDTOExample();
        MemberLevelDTOExample.Criteria criteria = example.createCriteria();
        criteria.andMemberLevelIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<MemberLevelDTO> targets = this.memberLevelDao.listByExample(example);
        MemberLevelDTO targetDto = this.memberLevelDao.getByExample(example);
        if (targetDto != null) {
            target = MemberLevelResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<MemberLevelResp> findByQuery(MemberLevelQuery query) {
        Page<MemberLevelDTO> targetPage = this.memberLevelDao.findByQuery(query);
        Page<MemberLevelResp> page = new Page<>(targetPage);
        page.setItems(MemberLevelResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<MemberLevelResp> listByQuery(MemberLevelQuery query) {
        List<MemberLevelDTO> targets = this.memberLevelDao.listByQuery(query);
        return MemberLevelResp.parseList(targets);
    }

    @Override
    public Long countByQuery(MemberLevelQuery query) {
        return this.memberLevelDao.countByQuery(query);
    }

    @Override
    public MemberLevelResp createAndGet(MemberLevelReq req, IOperatorAware operator) {
        MemberLevelDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.memberLevelDao.createAndGet(target);
        return MemberLevelResp.parseSinglet(target);
    }

    @Override
    public MemberLevelResp update(MemberLevelReq req, IOperatorAware operator) {
        MemberLevelDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.memberLevelDao.updateAndGet(target);
        return MemberLevelResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        MemberLevelDTO target = this.memberLevelDao.getById(id);
        target.setOperator(operator, false);
        return this.memberLevelDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
