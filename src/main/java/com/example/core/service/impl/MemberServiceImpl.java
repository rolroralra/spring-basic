package com.example.core.service.impl;

import com.example.core.model.Member;
import com.example.core.repository.MemberRepository;
import com.example.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // TODO: Deprecation. For just test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
