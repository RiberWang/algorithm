//
//  ViewController.m
//  Algorithm
//
//  Created by Riber on 2021/5/25.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    int n = 45;
    
    [self countTime:^{
        NSLog(@"%d", [self fib1:n]);
    }];

    [self countTime:^{
            NSLog(@"%d", [self fib2:n]);
    }];
}

// 递归
// 重复调用 重复计算
/**
 1 + 2 + 4 + 8 = 2^0 + 2^1 + 2^2 + 2^3 = 2^n-1
 O(2^n)
 */
- (int)fib1:(int)n {
    if (n <= 1) {
        return n;
    }
    
    return [self fib1:n - 1] + [self fib1:n - 2];
}

// for循环
// O(n)
- (int)fib2:(int)n {
    if (n < 1) {
        return n;
    }
    
    int first = 0;
    int second = 1;
    for (int i = 0; i < n - 1; i++) {
        int sum = first + second;
        first = second;
        second = sum;
    }
    
    return second;
}

#pragma mark - 时间复杂度和空间复杂度
/**
 时间复杂度：估算程序指令的执行次数（执行时间）
 大O表示法(粗略估算)：描述复杂度，表示数据规模n对应的复杂度
 忽略常数、系数、低阶
 
 对数阶的细节：
 对数阶一般省略底数
 log2(n) = log2(9)*log9(n)
 log2(n)、log9(n)统称为logn
 
 时间复杂度比较
 O(1) < O(logn) < O(n) < O(nlogn) < O(n^2) < O(n^3) < O(2^n) < O(n!) < O(n^n)
 
 汇编指令 1句代码执行多条汇编指令
 
 算法的优化方向
 1.用尽量少的存储空间
 2.用尽量少的执行步骤（执行时间）
 3.根据情况可以
 以空间换时间
 以时间换空间
 */
- (void)test1:(int)n {
    // 1
    if (n > 10) {
        NSLog(@"n > 10");
    }
    else if (n > 5) {
        NSLog(@"n > 5");
    }
    else {
        NSLog(@"n <= 5");
    }
    
    // 1 + 4 + 4 + 4
    for (int i = 0; i < 4; i++) {
        NSLog(@"test");
    }
    
    // 总14
    // 时间复杂度O(1)
    // 空间复杂度O(1)
}

- (void)test2:(int)n {
    // 1 + n + n + n = 3n + 1
    // O(n)
    // O(1)
    for (int i = 0; i < n; i++) {
        NSLog(@"test");
    }
}

- (void)test3:(int)n {
    // 1 + 2n + n*(1+3n) = 1 + 2n + n + 3n^2 = 1 + 3n + 3n^2
    // O(n^2)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            NSLog(@"test");
        }
    }
}

- (void)test4:(int)n {
    // 1 + 2n + n*(1 + 45) = 1 + 2n + n + 45n = 48n + 1
    // O(n)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 15; j++) {
            NSLog(@"test");
        }
    }
}

- (void)test5:(int)n {
    // 8 = 2^3
    // 16 = 2^4
    // 3 = log2(8)
    // 4 = log2(16)
    // 4 2 1 0
    // 执行次数 = log2(n)
    // O(logn)
    while ((n = n / 2) > 0) {
        NSLog(@"test");
    }
}

- (void)test6:(int)n {
    // 执行次数 = log5(n)
    // O(logn)
    while ((n = n / 5) > 0) {
        NSLog(@"test");
    }
}

- (void)test7:(int)n {
    // n = 1 * 2 * 2 * 2
    // 1 + 2*log2(n) + log2(n) * (1 + 3n)
    // = 1 + 3*log2(n) + 3n*log2(n)
    // O(logn + nlogn)
    // O(nlogn)
    for (int i = 1; i < n; i += i) { // i = i*2
        // 1 + 3n
        for (int j = 0; j < n; j++) {
            NSLog(@"test");
        }
    }
}

- (void)test10:(int)n {
    int a = 10;
    int b = 20;
    int c = a + b;
    
    // 空间复杂度O(n)
    NSMutableArray<NSNumber *> *array = [NSMutableArray arrayWithCapacity:n];
    for (int i = 0; i < array.count; i++) {
        NSLog(@"%d", array[i].intValue + c);
    }
}
#pragma mark -


- (void)countTime:(void(^)(void))methodBlock {
    dispatch_sync(dispatch_get_global_queue(0, 0), ^{
        CFAbsoluteTime startTime = CFAbsoluteTimeGetCurrent();
        NSLog(@"\n--------------------------------\n--------------------------------");
        NSLog(@"计算中...");

        // 执行代码函数
        methodBlock();
        
        CFAbsoluteTime intervalTime = CFAbsoluteTimeGetCurrent() - startTime;
        NSLog(@"总耗时：%f秒", intervalTime);
        NSLog(@"\n--------------------------------\n--------------------------------");
//        return intervalTime;
    });
}


@end
