package fremad.domain.user;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.list.AbstractListWrapper;

@XmlRootElement(name = "UserLoginListObject")
public class UserLoginLogListObject extends AbstractListWrapper<UserLoginLogObject>{
	
}
