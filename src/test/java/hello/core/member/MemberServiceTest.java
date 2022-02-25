package hello.core.member;
import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test //테스트를 위해서는 어노테이션을 넣어줘야함.
    void join(){
        //given 주어진환경
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 언제
        memberService.join(member);
        Member findMember = memberService.findByMember(1L);

        //then 동작한다
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}