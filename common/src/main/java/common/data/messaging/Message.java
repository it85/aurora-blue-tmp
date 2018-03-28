package common.data.messaging;

public interface Message extends Serializable {

    boolean write(Message message);

}
