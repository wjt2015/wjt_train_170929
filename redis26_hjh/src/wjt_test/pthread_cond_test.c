/*
pthread_cond_test.c
gcc -g3 pthread_cond_test.c -o pthread_cond_test_exe -lpthread
./pthread_cond_test_exe
wjt;
*/
#include<pthread.h>
#include<stdlib.h>
#include<string.h>
#include<stdio.h>
#include<unistd.h>
typedef struct xMsgNode{
    struct xMsgNode* next;
    int id;
}MsgNode;
int id = 1;
MsgNode* head = NULL;
pthread_mutex_t qlock=PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t qready=PTHREAD_COND_INITIALIZER;

void* produceRoutine(void* arg){
    int iret = 0,n = 6;
    long tid =(long)arg;
    while(n-- >= 0){
        MsgNode* node = (MsgNode*)malloc(sizeof(MsgNode));
        memset(node,0,sizeof(MsgNode));
        node->id = id++;
        iret = pthread_mutex_lock(&qlock);
        node->next = head;
        head = node;
        iret = printf("\tproduceRoutine();tid=%ld produce id=%d\n",tid,node->id);
        iret = pthread_mutex_unlock(&qlock);
        iret = pthread_cond_signal(&qready);
    }
    return NULL;
}

void* consumeRoutine(void* arg){
    int iret = 0,n = 3;
    long tid = (long)arg;
    MsgNode* node = NULL;
    while(n-- >= 0){
        iret = pthread_mutex_lock(&qlock);
        while(head == NULL){
            iret = printf("\tconsumeRoutine();tid=%ld,wait()\n",tid);
            iret = pthread_cond_wait(&qready,&qlock);
        }
        node = head;
        head = head->next;
        iret = printf("\tconsumeRoutine();tid=%ld consume id=%d\n",tid,node->id);
        iret = pthread_mutex_unlock(&qlock);
    }
    return NULL;
}

int main(int argc,char* argv[]){
    pthread_t tID;
    int iret = 0;
    long i = 0,j = 0,nc = 2,np = 1;
    void* arg = NULL;
    unsigned int uret = 0;
    iret = printf("\tmain(),start to create threads!!\n");
    for(i = 0;i < nc;i++){
        iret = pthread_create(&tID,NULL,consumeRoutine,(void*)i);
        iret = printf("\tmain();consume_tid=%ld created!\n",i);
    }
    for(i = 0,j = nc + i;i < np;i++,j++){
        iret = pthread_create(&tID,NULL,produceRoutine,(void*)j);
        iret = printf("\tmain();produce_tid=%ld created!\n",j);
    }
    uret = sleep(10);
    iret = printf("\tmain() finish!!\n");
    return 0;
}


/*
pthread_cond_tes/*
p