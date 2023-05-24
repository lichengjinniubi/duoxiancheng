package test.semaphore;

import java.util.concurrent.Semaphore;

public class LoginQueueUsingSemaphore {

    private Semaphore semaphore;

    /**
     *
     * @param slotLimit
     */
    public LoginQueueUsingSemaphore(int slotLimit){
        semaphore=new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        //获取一个凭证
        return semaphore.tryAcquire();
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }
}
