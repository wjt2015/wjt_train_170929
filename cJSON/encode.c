/*
gcc -g3 encode.c -o encode_exe
gdb encode_exe
*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include<unistd.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<fcntl.h>

#define _BUFSIZE_ 100

#define _STRSIZE_ 4
//-------
typedef struct xUnicode{
    char buf[_STRSIZE_];
}Unicode;

typedef struct xUTF8{
    char buf[_STRSIZE_];
}UTF8;
//--------
int inputOneUnicode(FILE* infp,Unicode* pu){
    int iret = 0,i = 0;
    unsigned short tv = 0;
    char* pLowByte = (char*)(&tv);
    char* pHighByte = (char*)(&tv) + 1;
    if(infp != NULL && pu != NULL){
        memset(pu,0,sizeof(Unicode));
        if(infp == stdin){
            iret = printf("\tPlease enter a unicode or lead surrogate(2 bytes):");
        }
        iret = fscanf(infp,"%hx",&tv);
        pu->buf[0] = *pHighByte;
        pu->buf[1] = *pLowByte;
        if(tv >= 0xD800 && tv <= 0xDBFF){
            if(infp == stdin){
                iret = printf("\tPlease enter a trail surrogate:");
            }
            iret = fscanf(infp,"%hx",&tv);
            if(tv < 0xDC00 && tv > 0xDFFF){
               iret = -1;
            }
            else{
               pu->buf[2] = *pHighByte;
               pu->buf[3] = *pLowByte;
            }
        }
    }
    return iret;
}
/*unicode->utf8*/
int unicodeToUtf8(Unicode* pu,UTF8* pUtf8){
    int iret = 0;
    unsigned short tv = 0;
    char* pLowByte = (char*)(&tv);
    char* pHighByte = (char*)(&tv) + 1;

    if(pu != NULL && pUtf8 != NULL){
        memset(pUtf8,0,sizeof(UTF8));
        *pLowByte = pu->buf[1];
        *pHighByte = pu->buf[0];
        if(tv <= 0x007F){
            pUtf8->buf[0] = *pLowByte;
        }
        else if(tv <= 0x07FF){
            pUtf8->buf[1] = 0x80 | ((pu->buf[1] >> 2) & 0x3F);
            pUtf8->buf[0] = ((pu->buf[0] & 0x07) << 2) | ((pu->buf[1] >> 6) && 0x03);
        }
        else if(tv <= 0xD7FF || tv >= 0xE000){
            pUtf8->buf[2] = 0x80 | (pu->buf[1] & 0x3F);
            pUtf8->buf[1] = 0x80 | ((pu->buf[1] >> 6) & 0x03) | ((pu->buf[0] << 2) & 0x3C);
            pUtf8->buf[0] = 0xE0 | ((pu->buf[0] >> 4) & 0x0F);
        }
        else{
            memcpy(pUtf8->buf,pu->buf,_STRSIZE_);
        }
    }
    return iret;
}
void func1(){
    char arr[_BUFSIZE_]="严";
    int iret = 0;
    ssize_t sret = 0;
    size_t size = 0;
    iret = printf("\t-------1,\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xE2;
    arr[1]=0x86;
    arr[2]=0x80;
    iret = printf("arr=%s;size=%lu;\n",arr,strlen(arr));
    iret = printf("\t-------2,\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xE2;
    arr[1]=0x86;
    arr[2]=0x81;
    iret = printf("arr=%s;size=%lu;\n",arr,strlen(arr));
    iret = printf("\t-------3,\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xE2;
    arr[1]=0x86;
    arr[2]=0x82;
    iret = printf("arr=%s;size=%lu;\n",arr,strlen(arr));
    iret = printf("\t-------4,\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xF0;
    arr[1]=0x9F;
    arr[2]=0x98;
    arr[3]=0x83;
    iret = printf("arr=%s;size=%lu;\n",arr,strlen(arr));
    iret = printf("\t-------5,\n");
    memset(arr,0,sizeof(arr));
    memcpy(arr,"联通",size = sizeof("联通"));
    iret = printf("arr=%s;size=%lu;\n",arr,strlen(arr));
    /**/
    iret = printf("\t-------6,\n");
    char fname[]="/home/linux2014/WJT_2017/projects/CXX_Projects/encode.txt";
    int fd = open(fname,O_APPEND|O_RDWR);
    sret = read(fd,arr,_BUFSIZE_);
    memset(arr,0,_BUFSIZE_);
    strcpy(arr,"\xF0\x9F\x98\x83");
    sret = write(fd,arr,_BUFSIZE_);
    iret = printf("\tarr=%s\n",arr);
    sret = read(fd,arr,_BUFSIZE_);
    close(fd);
    int code = 0;
    short cont = 1;
    while(cont == 1){
        memset(arr,0,_BUFSIZE_);
        iret = printf("\tPlease enter an int and short:");
        iret = scanf("%x %hd",&code,&cont);
        memcpy(arr,&code,sizeof(int));
        iret = printf("\tarr=%s;size=%lu\n",arr,strlen(arr));
        iret = printf("\t-----------\n");
    }
}
int main(int argc,char* argv[]){
    Unicode unicode;
    UTF8 utf8;
    ssize_t sret = 0;
    short cont = 1;
    char fname[]="/home/linux2014/WJT_2017/projects/CXX_Projects/encode.txt";
    int iret = 0,fd = open(fname,O_APPEND|O_RDWR);
    char buf[_BUFSIZE_];
    while(cont){
        memset(buf,0,_BUFSIZE_);
        iret = inputOneUnicode(stdin,&unicode);
        iret = unicodeToUtf8(&unicode,&utf8);
        memcpy(buf,utf8.buf,_STRSIZE_);
        iret = printf("\tbuf=%s\n",buf);
        iret = printf("\tPlease enter cont(short):");
        iret = scanf("%hd",&cont);
        sret = write(fd,buf,strlen(buf));
        iret = printf("\n");
    }
    close(fd);
    return 0;
}











