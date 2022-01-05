package com.example.demo.service.impl;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.impl.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
