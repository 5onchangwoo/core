package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //모든 bean 꺼내기
        for (String beanDefinitionName : beanDefinitionNames) {
            //iter 탭 하면 enum 모양이 저절로 나옴
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object =" + bean);

        }
    }


    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //모든 bean 꺼내기
        for (String beanDefinitionName : beanDefinitionNames) {
            //iter 탭 하면 enum 모양이 저절로 나옴
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //빈 이름으로 저장된 객체 꺼내오기.

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //ROLE_APPLICATION : 사용자가 등록한 애플리케이션 빈
                //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object =" + bean);
            }

        }
    }
}
