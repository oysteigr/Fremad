package fremad.annotation;

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
