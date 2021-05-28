//
//  ArrayList.c
//  Algorithm-C
//
//  Created by Riber on 2021/5/26.
//

#include "ArrayList.h"

#define DEFAULT_CAPACITY 10

typedef struct _ArrayList {
    int size;
    int elements[DEFAULT_CAPACITY];
    
}ArrayList;


ArrayList *arrayList(void) {
    ArrayList *list = NULL;
    
    return list;
}

