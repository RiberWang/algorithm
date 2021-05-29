//
//  main.swift
//  Algorithm-Swift
//
//  Created by Riber on 2021/5/26.
//

import Foundation

print("Hello, World!")

let n: Int = 45


//countTime() {
//    print(fib1(n))
//}

//countTime() {
//    print(fib2(n))
//}
//
let fib: Fib = Fib.init()
countTime() {
    print(fib.fibTail(n))
}
//countTime() {
//    print(fib.fib2(n))
//}
//
//countTime() {
//    print(fib.fib5(n))
//}

//let hanoi: Hanoi = Hanoi.init()
//hanoi.hanoi(7, "A", "B", "C")

let factorial: Factorial = Factorial.init()
print(factorial.factorial1(4))

func fib1(_ n: Int) -> Int {
    if n <= 1 {
        return n
    }
    
    return fib1(n - 1) + fib1(n - 2)
}

func fib2(_ n: Int) -> Int {
    if n <= 1 {
        return n
    }
    
    var first: Int = 0
    var second: Int = 1
    for _ in 0 ..< n - 1 {
        let sum: Int = first + second
        first = second
        second = sum
    }
    
    return second
}


func countTime(_ methodBlock: (() -> Void)) -> Void {
    let startTime = CFAbsoluteTimeGetCurrent()
    
    print("\n--------------------------------\n--------------------------------\n计算中...")

    // 执行代码函数
    methodBlock();
    
    let intervalTime = CFAbsoluteTimeGetCurrent() - startTime;
    print("总耗时：\(intervalTime)秒");
    print("\n--------------------------------\n--------------------------------");
}

