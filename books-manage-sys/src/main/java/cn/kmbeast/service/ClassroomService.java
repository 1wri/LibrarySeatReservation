package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.dto.query.extend.ClassroomQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.Classroom;
import cn.kmbeast.pojo.vo.BlacklistVO;
import cn.kmbeast.pojo.vo.ChartVO;

import java.util.List;

/**
 * 自习室业务逻辑接口
 */
public interface ClassroomService {

    Result<Void> save(Classroom classroom);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(Classroom classroom);

    Result<List<Classroom>> query(ClassroomQueryDto classroomQueryDto);

    Result<List<ChartVO>> seats();


}
