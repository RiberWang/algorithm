//
//  Hanoi.swift
//  Algorithm-Swift
//
//  Created by Riber on 2021/5/28.
//

import Cocoa

/**
 递归100%可以转非递归
 1.自己维护一个栈，保存参数、局部变量（空间复杂度没有优化）
 2.重复使用一组相同的变量保存每个栈帧的内容（空间复杂度O(n)降到O(1)）
 
 */

class Hanoi: NSObject {
    /// 将n个碟子从p1挪动到p3
    ///   - p2: 中间柱子
    func hanoi(_ n: Int, _ p1: String, _ p2: String, _ p3: String) -> Void {
        if n <= 1 {
            move(1, p1, p3)
            
            return
        }
        hanoi(n - 1, p1, p3, p2)
        move(n, p1, p3)
        hanoi(n - 1, p2, p1, p3)
    }
    
    
    /// 将编号no号的盘子从from移动到to
    /// - Parameters:
    ///   - number: number
    ///   - from: from
    ///   - to: to
    func move(_ number: Int, _ from: String, _ to:String) -> Void {
        print("将\(number)号盘子从\(from)移动到\(to)")
    }
}
