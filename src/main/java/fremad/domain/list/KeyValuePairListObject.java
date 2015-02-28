package fremad.domain.list;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.KeyValuePairObject;


@XmlRootElement(name = "KeyValuePairListObject")
public class KeyValuePairListObject<R,S> extends AbstractListWrapper<KeyValuePairObject<R,S>>{

}
