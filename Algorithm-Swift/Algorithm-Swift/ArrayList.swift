//
//  ArrayList.swift
//  Algorithm-Swift
//
//  Created by Riber on 2021/5/26.
//

import Cocoa



class ArrayList: NSObject {
    private var size: Int = 0;
    private var elements: [Int] = [];
    private final let DEFAULT_CAPACITY: Int = 10;
    
    public func ArrayList(_ capacity: Int) {
        if capacity < DEFAULT_CAPACITY {
            elements = [DEFAULT_CAPACITY]
        }
        else {
            elements = [capacity]
        }
    }
    
    public func ArrayList() {
        self.ArrayList(DEFAULT_CAPACITY)
    }
    
    public func getSize() -> Int {
        return size;
    }
    
    public func isEmpty() -> Bool {
        return size == 0;
    }
    
    public func getIndex(_ index: Int) -> Int {
        if index < 0 || index >= size {
            return 0
        }
        return elements[index]
    }
}
