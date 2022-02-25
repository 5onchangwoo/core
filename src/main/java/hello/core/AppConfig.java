package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 각종 구현체들이 변경되면 이곳의 내용만 수정해주면 됨.
 * 구현체들은 Appconfig클래스에서만 만들어짐
 * 즉, AppConfig는 애플리케이션이 어떻게 동작할지 전체 구성을 책임지는 클래스
 * 나머지 구현체들은 동작에만 집중할 수 있게 된다.
 */

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    //중복되었던 memberRopository를 리팩토링 해주었다.

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
    //할인정책을 한눈에 확인할 수 있도록 하였다.
}

/*
 * AppConfig객체는 MemoryMemberRepository객체를 생성 후
 * 그 참조 값을 MemberServiceImpl을 생성하면서 생성자로 전달
 *
 * 클라이언트인 MemberServiceImpl입장에서 보면 마치 외부에서 의존관계를 주입해주는 것
 * DI(Dependency Injection, 의존관계 주입)이라고 한다.
 */