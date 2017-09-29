/*
rbtree.c
*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define _RED_ 1
#define _BLACK_ 2

typedef struct xRBNode{
    int m_color;
    int m_key;
    struct xRBNode* m_parent;
    struct xRBNode* m_left;
    struct xRBNode* m_right;
}RBNode;


typedef struct xRBTree{
    RBNode* m_root;
    RBNode* m_nil;
}RBTree;

void InitializeRBTree(RBTree* pTree){
    if(pTree != NULL){
        pTree->m_root = pTree->m_nil = (RBNode*)malloc(sizeof(RBNode));
        memset(pTree->m_nil,0,sizeof(RBNode));
        pTree->m_nil->m_color = _BLACK_;
    }
}

void DestroyRBTreeCore(RBNode* pn,RBNode* nil){
    if(pn != nil){
        DestroyRBTreeCore(pn->m_left);
        DestroyRBTreeCore(pn->m_right);
        free(pn);
    }
}
void DestroyRBTree(RBTree* pTree){
    if(pTree != NULL){
        DestroyRBTreeCore(pTree->m_nil);
        DestroyRBTreeCore(pTree->m_root);
    }
}

void LeftRotate(RBTree* pTree,RBNode* x){
    RBNode* y = NULL;
    if(pTree != NULL && x != NULL && (y = x->m_right) != NULL){
        RBNode* alpha = x->m_left;
        RBNode* beta = y->m_left;
        RBNode* gama = y->m_right;
        RBNode* parent = x->m_parent;

        if(parent == pTree->m_nil){
            pTree->m_root = y;
        }
        else if(x == pTree->m_left){
            pTree->m_left = y;
        }
        else{
            pTree->m_right = y;
        }
        y->m_parent = parent;

        y->m_left = x;
        x->m_parent = y;

        x->m_right = beta;
        beta->m_parent = x;
    }
}
void RBTREE_FIXUP(RBTree* pTree,RBNode* z){
    if(pTree != NULL && pTree->m_nil != NULL && pTree->m_root != NULL && z != NULL){
        RBNode* y = NULL;
        while(z->m_parent->m_color == _RED_){
           if(z->m_parent == z->m_parent->m_left){
             y = z->m_parent->m_parent->m_right;
             if(y->m_color == _RED_){
                y->m_color = z->m_parent->m_color = _BLACK_;
                z->m_parent->m_parent->m_color = _RED_;
                z = z->m_parent->m_parent;
             }
             else{
                if(z == z->m_parent->m_right){

                }
             }
           }
           else{}
        }
    }
}
void InsertRBTreeCore(RBTree* pTree,RBNode* z){
    if(pTree != NULL && pTree->m_nil != NULL && pTree->m_root != NULL && z != NULL){
        RBNode* y = pTree->m_nil;
        RBNode* x = pTree->m_root;
        while(x != NULL){
            y = x;
            if(z->key <= x->key){
                x = x->m_left;
            }
            else{
                x = x->m_right;
            }
        }
        if(y == pTree->m_nil){
            pTree->m_root = z;
        }
        else if(z->key <= y->m_key){
            y->m_left = z;
        }
        else{
            y->m_right = z;
        }
        z->m_parent = y;
        z->m_left = z->m_right = pTree->m_nil;
        z->m_color = _RED_;
        RBTREE_FIXUP(pTRee,z);
    }
}









