package fremad.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//@Aspect
public class RoleCheckAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleCheckAspect.class);
	
/*	@Before("@annotation(fremad.annotation.UseRoleAnnotation)")
	public void doAccessCheck() throws Exception {
		// Do validation stuff
		if (true){
			LOG.debug("In RoleCheckAspect");
			throw new IllegalAccessException("foo");
		}
	}*/
}
