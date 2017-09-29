/*
 * Copyright (c) 2009-2012, Pieter Noordhuis <pcnoordhuis at gmail dot com>
 * Copyright (c) 2009-2012, Salvatore Sanfilippo <antirez at gmail dot com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of Redis nor the names of its contributors may be used
 *     to endorse or promote products derived from this software without
 *     specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */


#ifndef __REDIS_RIO_H
#define __REDIS_RIO_H

#include <stdio.h>
#include <stdint.h>
#include "sds.h"

struct _rio {
    /* Backend functions.
     * Since this functions do not tolerate short writes or reads the return
     * value is simplified to: zero on error, non zero on complete success. */
    /* �������Ķ����� */
    size_t (*read)(struct _rio *, void *buf, size_t len);
    /* ��������д���� */
    size_t (*write)(struct _rio *, const void *buf, size_t len);
    /* ��ȡ��ǰ�Ķ�дƫ���� */
    off_t (*tell)(struct _rio *);
    /* The update_cksum method if not NULL is used to compute the checksum of
     * all the data that was read or written so far. The method should be
     * designed so that can be called with the current checksum, and the buf
     * and len fields pointing to the new block of data to add to the checksum
     * computation. */
    /* �������µ����ݿ��ʱ�򣬻���µ�ǰ��У��� */
    void (*update_cksum)(struct _rio *, const void *buf, size_t len);

    /* The current checksum */
    /* ��ǰ��У��� */
    uint64_t cksum;

    /* number of bytes read or written */
    /* ��ǰ��ȡ�Ļ�д����ֽڴ�С */
    size_t processed_bytes;

    /* maximum single read or write chunk size */
    /* ���ĵ��ζ�д�Ĵ�С */
    size_t max_processing_chunk;

    /* Backend-specific vars. */
    /* rio��I/O���� */
    union {
    	//buffer�ṹ��
        struct {
        	//buffer��������
            sds ptr;
            //ƫ����
            off_t pos;
        } buffer;
        //�ļ��ṹ��
        struct {
            FILE *fp;
            off_t buffered; /* Bytes written since last fsync. */
            //ͬ������С��С
            off_t autosync; /* fsync after 'autosync' bytes written. */
        } file;
    } io;
};

typedef struct _rio rio;

/* The following functions are our interface with the stream. They'll call the
 * actual implementation of read / write / tell, and will update the checksum
 * if needed. */
/* rio��д���� */
static inline size_t rioWrite(rio *r, const void *buf, size_t len) {
    while (len) {
    	//�жϵ�ǰ�����ֽڳ����Ƿ񳬹���󳤶�
        size_t bytes_to_write = (r->max_processing_chunk && r->max_processing_chunk < len) ? r->max_processing_chunk : len;
        //д���µ�����ʱ������У���
        if (r->update_cksum) r->update_cksum(r,buf,bytes_to_write);
        //ִ��д����
        if (r->write(r,buf,bytes_to_write) == 0)
            return 0;
        buf = (char*)buf + bytes_to_write;
        len -= bytes_to_write;
        //�����ֽ�������
        r->processed_bytes += bytes_to_write;
    }
    return 1;
}

/* rio�Ķ����� */
static inline size_t rioRead(rio *r, void *buf, size_t len) {
    while (len) {
    	//�жϵ�ǰ�����ֽڳ����Ƿ񳬹���󳤶�
        size_t bytes_to_read = (r->max_processing_chunk && r->max_processing_chunk < len) ? r->max_processing_chunk : len;
        //�����ݷ���
        if (r->read(r,buf,bytes_to_read) == 0)
            return 0;
        //������ʱ������У���
        if (r->update_cksum) r->update_cksum(r,buf,bytes_to_read);
        buf = (char*)buf + bytes_to_read;
        len -= bytes_to_read;
        r->processed_bytes += bytes_to_read;
    }
    return 1;
}

/* ��ȡ��ǰƫ���� */
static inline off_t rioTell(rio *r) {
    return r->tell(r);
}

void rioInitWithFile(rio *r, FILE *fp); /* ��ʼ��rio�е�file���� */
void rioInitWithBuffer(rio *r, sds s); /* ��ʼ��rio�е�buffer���� */

/* rioд�벻ͬ�������ݷ��������յ��õ���riowrite���� */
size_t rioWriteBulkCount(rio *r, char prefix, int count);
size_t rioWriteBulkString(rio *r, const char *buf, size_t len);
size_t rioWriteBulkLongLong(rio *r, long long l);
size_t rioWriteBulkDouble(rio *r, double d);

void rioGenericUpdateChecksum(rio *r, const void *buf, size_t len); /* ����У����õ���ѭ������У���㷨 */
void rioSetAutoSync(rio *r, off_t bytes); /* ���ö��ٴ�Сֵʱ���У��Զ�ͬ�� */

#endif