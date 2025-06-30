package com.project.noto.mapper;

import com.project.noto.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    void updatePassword(@Param("memberId") String memberId, @Param("newPassword") String newPassword);
    Member findByMemberId(String memberId);
    Member findByEmail(String email);

}