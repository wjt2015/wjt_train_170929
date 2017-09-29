/*
file_test.c
gcc -g3 file_test.c -o file_test_exe
gdb file_test_exe
*/
#include<sys/stat.h>
#include<sys/types.h>
#include<fcntl.h>
#include<unistd.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define _BUFSIZE_ 20
int main(int argc,char* argv[]){
    off_t offset1 = 0,offset2 = 0;
    const char* pathname = "pthread_cond_test.c";
    int fd1 = open(pathname,O_RDWR|O_APPEND);
    int fd2 = open(pathname,O_RDONLY);
    offset1=lseek(fd1,0,SEEK_CUR);
    offset2=lseek(fd2,0,SEEK_CUR);
    int iret = printf("\tfd1=%d,offset1=%lu;fd2=%d,offset2=%lu\n",fd1,(unsigned long)offset1,fd2,(unsigned long)offset2);
    char buf[_BUFSIZE_];
    iret = read(fd2,buf,_BUFSIZE_);
    buf[iret >= 1?(iret - 1):0] = '\0';
    offset2 = lseek(fd2,0,SEEK_CUR);
    iret = printf("\tfd2=%d,read(),buf=%s;offset2=%lu\n",fd2,buf,(unsigned long)offset2);
    buf[4]='\0';
    iret = read(fd1,buf,5);
    offset1=lseek(fd1,0,SEEK_CUR);
    offset2=lseek(fd2,0,SEEK_CUR);
    iret = printf("\t32;fd1=%d,offset1=%lu;fd2=%d,offset2=%lu\n",fd1,(unsigned long)offset1,fd2,(unsigned long)offset2);
    close(fd1);
    close(fd2);
    iret = printf("\tmain() finish!!\n");
    return 0;
}







