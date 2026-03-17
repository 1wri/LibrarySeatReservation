package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.ClassroomQueryDto;
import cn.kmbeast.pojo.entity.Classroom;
import cn.kmbeast.pojo.vo.ChartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自习室持久化接口
 */
@Mapper
public interface ClassroomMapper {

    void save(Classroom classroom);

    void update(Classroom classroom);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<Classroom> query(ClassroomQueryDto classroomQueryDto);

    Integer queryCount(ClassroomQueryDto classroomQueryDto);

    List<ChartVO> seats();


}
