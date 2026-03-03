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

// 1115 print foobar alternatively
class FooBar {
private:
    const int n_;
    int curr_ = 1;
    std::mutex m_;
    std::condition_variable cv_;
    bool foo_turn = true;

public:
    explicit FooBar(int n) : n_(n) {}

    void foo(function<void()> printFoo) {
        while(true) {
            std::unique_lock<std::mutex> lk(m_);
            cv_.wait(lk, [this]{ return curr_ > n_ || foo_turn; });
            if(curr_ > n_) {
                cv_.notify_one();
                break;
            }
            printFoo();
            foo_turn = false;
            cv_.notify_one();
        }
    }

    void bar(function<void()> printBar) {
        while(true) {
            std::unique_lock<std::mutex> lk(m_);
            cv_.wait(lk, [this]{ return curr_ > n_ || !foo_turn; });
            if(curr_ > n_) {
                cv_.notify_one();
                break;
            }
            printBar();
            ++curr_;
            foo_turn = true;
            cv_.notify_one();
        }
    }
};
// semaphore solution
class FooBar {
    const int n_;
    std::binary_semaphore foo_sem_{1};  // foo goes first
    std::binary_semaphore bar_sem_{0};

public:
    explicit FooBar(int n) : n_(n) {}

    void foo(std::function<void()> printFoo) {
        for (int i = 0; i < n_; ++i) {
            foo_sem_.acquire();
            printFoo();
            bar_sem_.release();
        }
    }

    void bar(std::function<void()> printBar) {
        for (int i = 0; i < n_; ++i) {
            bar_sem_.acquire();
            printBar();
            foo_sem_.release();
        }
    }
};
// atomics solution
class FooBar {
    const int n_;
    std::atomic<bool> foo_turn_{true};

public:
    explicit FooBar(int n) : n_(n) {}

    void foo(std::function<void()> printFoo) {
        for (int i = 0; i < n_; ++i) {
            foo_turn_.wait(false, std::memory_order_acquire);  // block while false
            printFoo();
            foo_turn_.store(false, std::memory_order_release);
            foo_turn_.notify_one();
        }
    }

    void bar(std::function<void()> printBar) {
        for (int i = 0; i < n_; ++i) {
            foo_turn_.wait(true, std::memory_order_acquire);  // block while true
            printBar();
            foo_turn_.store(true, std::memory_order_release);
            foo_turn_.notify_one();
        }
    }
};