package hello.hellospring.aop;

import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP
@Component
public class TimeTraceAop { //메소드 호출시마다 실행
    @Around("execution(* hello.hellospring..*(..))") //패키지 하위에 전부 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start= System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //inline함수, 다음 method로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END: " + joinPoint.toString() + " "+ timeMs + "ms");
        }

    }
}
