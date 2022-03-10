package hello.core.member;

public class MemberServiceImpl implements MemberService {
                                //인터페이스 의존
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //구현체도 의존하고 있음. >> DIP위반

    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //이렇게 구현을 해서 DIP를 지키도록 수정함. (오직 인터페이스만 의존함)
    //이처럼 생성자로 구현체를 가져오는 방법을 생성자 주입이라고 함

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findByMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
