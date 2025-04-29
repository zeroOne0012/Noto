package com.project.noto.mapper;

import com.project.noto.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    Member findByMemberId(String memberId);
}