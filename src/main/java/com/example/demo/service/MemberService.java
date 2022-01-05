package com.example.demo.service;

import com.example.demo.model.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
