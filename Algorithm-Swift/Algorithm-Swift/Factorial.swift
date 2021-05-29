//
//  Factorial.swift
//  Algorithm-Swift
//
//  Created by Riber on 2021/5/28.
//

import Cocoa

class Factorial: NSObject {
    // 递归
    func factorial(_ n: Int) -> Int {
        if n <= 1  {
            return n
        }
        
        return n * factorial(n - 1)
    }
    
    // 尾递归
    func factorial1(_ n: Int) -> Int {
        return factorial1(n, 1)
    }
    
    func factorial1(_ n: Int, _ result: Int) -> Int {
        if n <= 1  {
            return result
        }
        return factorial1(n - 1, result * n)
    }
}


