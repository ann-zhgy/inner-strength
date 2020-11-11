package cn.ann._05queue;

/**
 * Description：
 * <p>
 * Date: 2020-10-29 13:42
 *
 * @author 88475
 * @version v1.0
 */
class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
