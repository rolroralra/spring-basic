package com.example.demo.repository;

import com.example.demo.model.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
