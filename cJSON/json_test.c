/**/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include"cJSON.h"

/*遍历json,
tag==0,不必输出name;
tag==1,需要输出name;*/
//void traverseJson(cJSON* jsonObj,short tag,FILE* fp){
//    int iret = 0;
//    cJSON* obj = NULL;
//    char buff[6];
//    memset(buff,0,sizeof(buff));
//    if(jsonObj != NULL){
//        switch(jsonObj->type){
//        case cJSON_NULL:
//            strcpy(buff,"null");
//            if(tag == 1){
//                iret = fprintf(fp,"(%s,%s) ",jsonObj->string,buff);
//            }
//            else{
//                iret = fprintf(fp,"%s ",buff);
//            }
//            break;
//        case cJSON_TRUE:
//            strcpy(buff,"true");
//            if(tag == 1){
//                iret = fprintf(fp,"(%s,%s) ",jsonObj->string,buff);
//            }
//            else{
//                iret = fprintf(fp,"%s ",buff);
//            }
//            break;
//        case cJSON_FALSE:
//            strcpy(buff,"false");
//            if(tag == 1){
//                iret = fprintf(fp,"(%s,%s) ",jsonObj->string,buff);
//            }
//            else{
//                iret = fprintf(fp,"%s ",buff);
//            }
//            break;
//         case cJSON_NUMBER:
//            if(tag == 1){
//                iret = fprintf(fp,"(%s,%lf) ",jsonObj->string,jsonObj->valuedouble);
//            }
//            else{
//                iret = fprintf(fp,"%lf ",jsonObj->valuedouble);
//            }
//            break;
//         case cJSON_STRING:
//             if(tag == 1){
//                 iret = fprintf(fp,"(%s,%s) ",jsonObj->string,jsonObj->valuestring);
//             }
//             else{
//                 iret = fprintf(fp,"%s ",jsonObj->valuestring);
//             }
//             break;
//         case cJSON_ARRAY:
//             for(obj = jsonObj->child;obj != NULL; obj = obj->next){
//                traverseJson(obj,0,fp);
//             }
//             break;
//         case cJSON_OBJECT:
//             iret = fprintf(fp,"%s ",jsonObj->string);
//             obj = jsonObj->child;
//             traverseJson(obj,1,fp);
//             break;
//        }
//    }
//}
///*store a json object into buffer*/
//int JsonObjToString(cJSON* jsonObj,char buff[],int* psize){
//    int iret = 0,size = *psize;
//    cJSON* obj = NULL;
//    if(jsonObj != NULL psize != NULL && (size = *psize) >= 1){
//        switch(jsonObj->type){
//
//        }
//    }
//    return iret;
//}
/*main
gcc -g3 json_test.c cJSON.c -o json_test_exe -lm
gdb json_test_exe
*/
int main(int argc,char* argv[]){
    cJSON* jsonObj = NULL;
    int iret = 0;
    cJSON* personJsonObj = cJSON_CreateObject();
    cJSON* nameJsonObj = cJSON_CreateString("李鸿章");
    cJSON_AddItemToObject(personJsonObj,"name",nameJsonObj);
    cJSON* marriedJsonObj = cJSON_CreateTrue();
    cJSON_AddItemToObject(personJsonObj,"married",marriedJsonObj);
    cJSON* heightJsonObj = cJSON_CreateNumber(1.73);
    cJSON_AddItemToObject(personJsonObj,"height",heightJsonObj);
    cJSON* birthDateJsonObj = cJSON_CreateObject();
    cJSON* yearJsonObj = cJSON_CreateNumber(1829);
    cJSON* monthJsonObj = cJSON_CreateNumber(4);
    cJSON* dayJsonObj = cJSON_CreateNumber(25);
    cJSON_AddItemToObject(birthDateJsonObj,"year",yearJsonObj);
    cJSON_AddItemToObject(birthDateJsonObj,"month",monthJsonObj);
    cJSON_AddItemToObject(birthDateJsonObj,"day",dayJsonObj);
    cJSON_AddItemToObject(personJsonObj,"birthDate",birthDateJsonObj);
    char* jsonStr = cJSON_Print(personJsonObj);
    iret = printf("%s",jsonStr);
    free(jsonStr);
    jsonStr = cJSON_PrintBuffered(personJsonObj,1,1);
    iret = printf("%s",jsonStr);
    free(jsonStr);
    cJSON_Delete(personJsonObj);
    iret = printf("\t---------\n");
    char arr[]="\x02H你好,g啊！#!";
    iret = printf("arr=%s;size=%lu\n",arr,sizeof(arr));
    char arr2[]="\x02你好,g啊！#!";
    iret = printf("arr2=%s;size=%lu\n",arr2,sizeof(arr2));
    iret = printf("\t-----###----\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0x81;
    arr[1]=0x40;
    iret = printf("arr=%s;size=%lu\n",arr,sizeof(arr));
    iret = printf("\t-----+++----\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xe5;
    arr[1]=0x91;
    iret = printf("arr=%s;size=%lu\n",arr,sizeof(arr));
    iret = printf("\t-----啊----\n");
    memset(arr,0,sizeof(arr));
    strcpy(arr,"啊");
    iret = printf("arr=%s;size=%lu;arr[0]=%x,arr[1]=%x\n",arr,sizeof(arr),(unsigned char)arr[0],(unsigned char)arr[1]);
    iret = printf("\t-----严----\n");
    memset(arr,0,sizeof(arr));
    strcpy(arr,"严");
    iret = printf("arr=%s;size=%lu;arr[0]=%x,arr[1]=%x\n",arr,sizeof(arr),(unsigned char)arr[0],(unsigned char)arr[1]);
    iret = printf("\t-----阴----\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xE9;
    arr[1]=0x98;
    arr[2]=0xB4;
    iret = printf("arr=%s;size=%lu;arr[0]=%x,arr[1]=%x\n",arr,sizeof(arr),(unsigned char)arr[0],(unsigned char)arr[1]);
    iret = printf("\t-----表情符号----\n");
    memset(arr,0,sizeof(arr));
    arr[0]=0xEF;
    arr[1]=0x98;
    arr[2]=0x81;
    iret = printf("arr=%s;size=%lu;arr[0]=%x,arr[1]=%x\n",arr,sizeof(arr),(unsigned char)arr[0],(unsigned char)arr[1]);
    return 0;
}






