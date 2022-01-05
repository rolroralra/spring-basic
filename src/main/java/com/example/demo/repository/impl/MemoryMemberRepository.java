package com.example.demo.repository.impl;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MemoryMemberRepository implements MemberRepository {
    private static final ConcurrentMap<Long, Member> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        storage.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return storage.get(memberId);
    }
}
