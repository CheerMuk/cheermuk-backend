package cheermuk.cheermukbackend.domain.member.service;

import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.domain.member.repository.MemberRepository;
import cheermuk.cheermukbackend.global.exception.MemberException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));
    }

    public Member getMember(String name) {
        return memberRepository.findByNickname(name).orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }
}
