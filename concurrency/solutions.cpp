// 1114 print in order
// MUTEX SOLUTION
class Foo {
private:
    std::mutex m_;
    int n=0;
    std::condition_variable cv_;

public:

    Foo() = default;

    void first(const std::function<void()> printFirst) {
        std::unique_lock<std::mutex> lk(m_);
        cv_.wait(lk, [this]{ return n==0; });
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        n = 1;
        cv_.notify_all();
    }

    void second(const std::function<void()> printSecond) {
        std::unique_lock<std::mutex> lk(m_);
        cv_.wait(lk, [this]{ return n==1; });
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        n=2;
        cv_.notify_all();
    }

    void third(const std::function<void()> printThird) {
        std::unique_lock<std::mutex> lk(m_);
        cv_.wait(lk, [this]{ return n==2; });
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
        lk.unlock();
    }
};

// ATOMICS + SPINLOOP (slower but lighter)
class Foo {
private:
    std::atomic<int> n_{0};

public:
    Foo() = default;

    void first(const std::function<void()> printFirst) {
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        n_.store(1, std::memory_order_release);
        n_.notify_all();
    }

    void second(const std::function<void()> printSecond) {
        while(n_.load(std::memory_order_acquire) < 1) {
            n_.wait(0, std::memory_order_acquire);
        }
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        n_.store(2, std::memory_order_release);
        n_.notify_all();
    }

    void third(const std::function<void()> printThird) {
        while(n_.load(std::memory_order_acquire) < 2) {
            n_.wait(1, std::memory_order_acquire);
        }
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};

// SEMAPHORE
class Foo {
private:
    std::binary_semaphore sem2{0};
    std::binary_semaphore sem3{0};

public:
    Foo() = default;

    void first(const std::function<void()> printFirst) {
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        sem2.release();
    }

    void second(const std::function<void()> printSecond) {
        sem2.acquire();
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        sem3.release();
    }

    void third(const std::function<void()> printThird) {
        sem3.acquire();
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};