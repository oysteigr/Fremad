package fremad.domain.list;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.EventObject;


@XmlRootElement(name = "EventListObject")
public class EventListObject extends AbstractListWrapper<EventObject>{

}
