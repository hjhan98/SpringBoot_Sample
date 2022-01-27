package syscon.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* syscon.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : "+ joinPoint.toString());   //어떤 메소드들을 호출 했는지 알 수 있음
        try {
            return  joinPoint.proceed();
        }finally {
            {
                long finish = System.currentTimeMillis();
                long TimeMs = finish - start;
                System.out.println("END : "+ joinPoint.toString()+" "+TimeMs +"Ms");
            }
        }
    }
}

