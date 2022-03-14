package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 각종 구현체들이 변경되면 이곳의 내용만 수정해주면 됨.
 * 구현체들은 Appconfig클래스에서만 만들어짐
 * 즉, AppConfig는 애플리케이션이 어떻게 동작할지 전체 구성을 책임지는 클래스
 * 나머지 구현체들은 동작에만 집중할 수 있게 된다.
 */

//이러한 방식으로 만드는 것을 팩토리 메서드라고 함.
@Configuration //구성정보를 담당하는 것을 알림.
public class AppConfig {

    //만약 @Configuration을 사용안하면
    //@Autowired MemberRepository memberRepository; 입력시 DI주입해줄 수 있음.



    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean OrderService -> new MemoryMemberRepository()

    @Bean //각 메서드에 Bean을 붙이면 스프링 컨테이너에 등록됨.
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    //중복되었던 memberRopository를 리팩토링 해주었다.

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

//    public DiscountPolicy discountPolicy(){ return new FixDiscountPolicy(); }
    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy(); }
    //할인정책을 한눈에 확인할 수 있도록 하였다.
}

/*
 * AppConfig객체는 MemoryMemberRepository객체를 생성 후
 * 그 참조 값을 MemberServiceImpl을 생성하면서 생성자로 전달
 *
 * 클라이언트인 MemberServiceImpl입장에서 보면 마치 외부에서 의존관계를 주입해주는 것
 * DI(Dependency Injection, 의존관계 주입)이라고 한다.
 */