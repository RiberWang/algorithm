//
//  Fib.swift
//  Algorithm-Swift
//
//  Created by Riber on 2021/5/28.
//

import Cocoa

class Fib: NSObject {
    func fib1(_ n: Int) -> Int {
        if n <= 2 {
            return 1
        }
        
        return fib1(n - 1) + fib1(n - 2)
    }
    
    // 尾递归
    func fibTail(_ n: Int) -> Int {

        return fibTail(n, 1, 1)
    }
    
    func fibTail(_ n: Int, _ first: Int, _ second: Int) -> Int {
        if n <= 2 {
            return 1
        }
        
        return fibTail(n - 1, second, first + second)
    }
    
    
    // 0 1 1 2 3
    func fib2(_ n: Int) -> Int {
        if n <= 2 {
            return 1
        }
        
        let mArray: NSMutableArray = NSMutableArray.init(capacity: n + 1)
        for _ in 0 ... n {
            mArray.add(0)
        }
        mArray[1] = 1
        mArray[2] = 1
        return fib2(n, mArray)
        
    }
    
    func fib2(_ n: Int, _ mArray: NSMutableArray) -> Int {
        if mArray[n] as! Int == 0 {
            mArray[n] = fib2(n - 1, mArray) + fib2(n - 2, mArray)
        }
        return mArray[n] as! Int
    }
    
    // 特征方程 3.3020973205566406*10^-5秒
    func fib5(_ n: Int) -> Int {
        let c: Double = sqrt(5)
        return (Int)((pow((1 + c) / 2, Double(n)) - pow((1 - c) / 2, Double(n))) / c)
    }

}
