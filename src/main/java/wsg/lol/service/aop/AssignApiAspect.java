package wsg.lol.service.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AssignApi;
import wsg.lol.config.ApiIdentifier;
import wsg.lol.dao.api.client.ApiClient;

import java.lang.reflect.Method;

/**
 * Aspect to assign the api to use.
 *
 * @author Kingen
 */
@Aspect
@Component
public class AssignApiAspect {

    private static final Logger logger = LoggerFactory.getLogger(AssignApiAspect.class);

    private ApiClient apiClient;

    @Pointcut(value = "@annotation(wsg.lol.common.annotation.AssignApi)")
    public void assignApi() { }

    @Around(value = "assignApi() && @annotation(source)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, AssignApi source) {
        String username = getUsername(joinPoint, source);
        String from = ApiIdentifier.getApi();
        logger.info("Using the api under {} instead of {}...", username, from);
        ApiIdentifier.setApi(username);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        ApiIdentifier.setApi(from);
        logger.info("Reuse the api under {} back from {}.", from, username);
        return result;
    }

    /**
     * Get the username by parsing spring expression language.
     *
     * @return choose from {@link ApiClient#peekUsername()} if not specified.
     */
    private String getUsername(ProceedingJoinPoint joinPoint, AssignApi source) {
        String username = source.encryptUsername();
        if (StringUtils.isBlank(username)) {
            return apiClient.peekUsername();
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
        return parser.parseExpression(username).getValue(context, String.class);
    }

    @Autowired
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
