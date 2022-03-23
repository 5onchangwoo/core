package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//생성자를 자동으로 만들어 줌.
public class OrderServiceImpl implements OrderService {
    //OrderService 의존
    /** 바꾸기 전 코드
     * private final MemberRepository memberRepository = new MemoryMemberRepository();
     * 할인정책을 변경하기 위해선 아래 코드를 변경해야함
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     * 이는 구현체도 의존하고 있기 때문에 DIP위반이라고 할 수 있음
     * 또한 할인 정책을 변경하려면 OrderServiceImpl의 코드를 고쳐야함
     * 즉, 할인 정책을 변경하려면 구현체 의존을 변경해야함. -->> OCP 위반
     * 해결 하기 위해서는 아래 코드처럼 구현을 해야함.
    **/
    private final MemberRepository memberRepository; //불변 특징을 갖고있음.
    private final DiscountPolicy discountPolicy; // 불변하도록 바꾸는 세터메서드를 넣지않도록 함.
    //final은 무조건 값이 있어야 함을 뜻.(불변)

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // --->> 해당 코드를 @RequiredArgsConstructor으로 대체 가능
    
    
    /** 인터페이스에만 의존하게 만들었음 (DIP위반 해결)
     * 하지만 이대로 실행하면 NullPointException 발생
     * 따라서 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입
     * 이를 관리해주는 설정 클래스를 따로 만들어야 함.
     * --> AppConfig
     **/

    /** 생성자 주입방식으로 구현체를 가져옴.
     * 따라서 할인정책, 멤버저장방법을 바꿔도 OrderServiceImpl은 변경되지 않음.
     * -->OCP위반 해결
     **/
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
