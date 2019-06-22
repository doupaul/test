package com.mapper;

import com.entities.BasePage;
import com.entities.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaperMapper {

    @Insert("INSERT INTO paper(paper_id,name,number,detail) VALUE (#{paperId},#{paperName}, #{paperNum}, #{paperDetail})")
    int addPaper(Paper paper);

    @Delete("DELETE FROM paper WHERE paper_id IN (${ids})")
    int deletePush(@Param(value = "ids") String ids);

    @Delete("DELETE FROM paper WHERE paper_id=#{paperID}")
    int deletePaperById(long id);

    @Update("UPDATE paper SET NAME = #{paperName},NUMBER = #{paperNum},detail = #{paperDetail} WHERE  paper_id = #{paperId}")
    int updatePaper(Paper paper);

    @Select({"SELECT paper_id,name,number,detail FROM paper WHERE paper_id=#{paperId}"})
    @Results(id="paperResultMap", value={
            @Result(column="paper_id", property="paperId", id=true),
            @Result(column="name", property="paperName"),
            @Result(column="number", property="paperNum"),
            @Result(column="detail", property="paperDetail"),
    })
    Paper queryById(long id);

    @Select({"SELECT paper_id,name,number,detail FROM paper"})
    @ResultMap("paperResultMap")
    List<Paper> queryAllPaper();

    @Select({"SELECT paper_id,name,number,detail FROM paper LIMIT #{pageBegin},#{pageSize}"})
    @ResultMap("paperResultMap")
    List<Paper> queryPaperByPage(BasePage basePage);

    @Select("SELECT COUNT(*) FROM paper")
    int queryRecordTotal();

    @Select({"SELECT paper_id,name,number,detail FROM paper WHERE name LIKE concat('%',#{paperName},'%') LIMIT #{pageBegin},#{pageSize}"})
    @ResultMap("paperResultMap")
    List<Paper> queryPaperByName(@Param("paperName") String paperName,@Param("pageBegin") int pageBegin,@Param("pageSize")int pageSize);

    @Select({"SELECT COUNT(*) FROM paper WHERE name LIKE concat('%',#{paperName},'%')"})
    int queryRecordTotalByName(Paper paper);

}
