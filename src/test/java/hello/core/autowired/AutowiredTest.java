package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        //주입할 대상이 없을시

        //1.아예 호출이 안됨.
        @Autowired(required = false)
        public void setNobean1(Member nobean1) {
            System.out.println("nobean1 = " + nobean1);
        }

        //2. Null을 넘겨줌
        @Autowired()
        public void setNobean2(@Nullable Member nobean2) {
            System.out.println("nobean2 = " + nobean2);
        }

        //3. Optinal로 감싸서 전달.
        //Java8에서 지원하는 옵션
        @Autowired()
        public void setNobean3(Optional<Member> nobean3) {
            System.out.println("nobean3 = " + nobean3);
        }
    }
}
