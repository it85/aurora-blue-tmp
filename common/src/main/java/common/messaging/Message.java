package common.messaging;

import common.data.type.Serializable;

public interface Message extends Serializable {

    boolean write(Message message);

}