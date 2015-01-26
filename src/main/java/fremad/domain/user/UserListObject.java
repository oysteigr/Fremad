package fremad.domain.user;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.list.AbstractListWrapper;

@XmlRootElement(name = "UserListObject")
public class UserListObject extends AbstractListWrapper<UserObject>{
	
}

