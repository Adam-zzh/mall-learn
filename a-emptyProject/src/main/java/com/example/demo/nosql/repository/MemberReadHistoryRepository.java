package com.example.demo.nosql.repository;

import com.example.demo.nosql.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-11-08 15:11
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    /*
     * @Description 会员商品浏览历史
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
