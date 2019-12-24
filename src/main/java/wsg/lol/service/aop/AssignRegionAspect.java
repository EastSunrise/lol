package wsg.lol.service.aop;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AssignRegion;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.config.RegionIdentifier;

import java.lang.reflect.Method;

/**
 * Aspect to assign the api to use.
 *
 * @author Kingen
 */
@Aspect
@Component
public class AssignRegionAspect {

    @Pointcut(value = "@annotation(wsg.lol.common.annotation.AssignRegion)")
    public void assignRegion() { }

    @Around(value = "assignRegion() && @annotation(source)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, AssignRegion source) throws Throwable {
        RegionIdentifier.setPlatform(getRegion(joinPoint, source));
        return joinPoint.proceed();
    }

    /**
     * Get the username by parsing spring expression language.
     *
     * @return null if not specified.
     */
    private RegionEnum getRegion(ProceedingJoinPoint joinPoint, AssignRegion source) {
        RegionEnum region = source.region();
        String expression = source.regionString();
        if (!region.equals(RegionEnum.LOL) || StringUtils.isBlank(expression)) {
            return region;
        }

        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        assert parameterNames != null;

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return EnumUtils.getEnum(RegionEnum.class, parser.parseExpression(expression).getValue(context, String.class));
    }
}
