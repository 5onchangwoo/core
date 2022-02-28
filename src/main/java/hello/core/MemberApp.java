/**
 * 테스트용 코드
 * 테스트용 코드를 main에 생성하는것은 옳지 않음
 * test 패키지에서 생성 후 검사하는 것이 옳바르나 공부를 위해 이곳에 생성함.
 * test.java.hello.core.member패키지의 MemberServiceTest와 같은 테스트
 */
package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //스프링컨테이너 이전에 사용했던 코드

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig에 있는 설정정보를 토대로 스프링컨테이너에 등록하겠다.
        //appConfig또한 자동으로 등록됨.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //빈에 등록되어있는 memberSerice을 꺼내기.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findByMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember.getName = " + findMember.getName());
    }
}
