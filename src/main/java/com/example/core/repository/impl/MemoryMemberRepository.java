package com.example.core.repository.impl;

import com.example.core.model.Member;
import com.example.core.repository.MemberRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Component;

@Component
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
