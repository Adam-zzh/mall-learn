package com.example.demo.service.Iml;

import com.example.demo.nosql.document.MemberReadHistory;
import com.example.demo.nosql.repository.MemberReadHistoryRepository;
import com.example.demo.service.MemberReadHistoryService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-11-08 16:08
 */
@Service
@Transactional
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setCreateTime(new Date());
        memberReadHistory.setId(null);
        MemberReadHistory save = memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        int index = 0;
        List<MemberReadHistory> list = new ArrayList<>();
        if (Collections.isEmpty(ids)){
            for (String id : ids) {
                MemberReadHistory memberReadHistory = new MemberReadHistory();
                memberReadHistory.setId(id);
                list.add(memberReadHistory);
                index++;
            }
        }
        memberReadHistoryRepository.deleteAll(list);
        return index;
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        List<MemberReadHistory> datas = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
        return datas;
    }
}
