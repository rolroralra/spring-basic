package com.example.core.service;

import com.example.core.model.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
