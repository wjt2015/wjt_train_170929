/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.dao;

import javaweb.model.PicBlob;
import org.apache.ibatis.annotations.Param;

import java.sql.Blob;
import java.util.List;

/**
 * @author jintao.wang  Date: 17-9-19 Time: 下午4:51
 */
public interface PicBlobDao {

    PicBlob selectBlobById(@Param("id")Integer id);

    List<PicBlob> selectAllBlob(@Param("offset")Integer offset,@Param("limit")Integer limit);

    int insertBlob(@Param("fileName")String fileName);

    int updateBlobById(@Param("id")Integer id,@Param("fileName")String fileName);
}




    