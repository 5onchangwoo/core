/**
 * 테스트용 코드
 * 테스트용 코드를 main에 생성하는것은 옳지 않음
 * test 패키지에서 생성 후 검사하는 것이 옳바르나 공부를 위해 이곳에 생성함.
 * test.java.hello.core.member패키지의 MemberServiceTest와 같은 테스트
 */
package hello.core;

import hello.core.member.*;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findByMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember.getName = " + findMember.getName());
    }
}
