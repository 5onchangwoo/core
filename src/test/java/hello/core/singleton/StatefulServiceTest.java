package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {


    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        //ThreadA: A사용자 10,000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20,000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        //stateful은 같은 객체를 사용함. 즉, 사용자마다 다 같은 객체를 사용함.(싱글톤)
        //때문에 price필드가 공유 됨.
        Assertions.assertThat(statefulService1).isSameAs(statefulService2);
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
        
    //무상태로 만들기 
    @Test
    void statelessServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig2.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA: A사용자 10,000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        //ThreadB: B사용자 20,000원 주문
        int userBPrice = statelessService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        System.out.println("price = " + userAPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }
        
    static class TestConfig2{
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }
}