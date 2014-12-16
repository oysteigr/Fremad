package fremad.domain.user;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.list.AbstractListWrapper;

@XmlRootElement(name = "UserRoleRequestListObject")
public class UserRoleRequestListObject extends AbstractListWrapper<UserRoleRequestObject>{

}
