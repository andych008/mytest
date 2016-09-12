package hello.mapper;

import hello.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM UserInfo")
    List<UserInfo> findAll();

    @Select("SELECT * FROM UserInfo WHERE name = #{name}")
    UserInfo findByName(@Param("name") String name);

    @Select("SELECT * FROM UserInfo WHERE id = #{id}")
    UserInfo findById(@Param("id") Long id);

    @Select("DELETE FROM UserInfo WHERE id = #{id}")
//    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=int.class)
    void deleteById(@Param("id") Long id);

    @Insert("UPDATE UserInfo SET name = #{name}, age = #{age} WHERE id = #{id}")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age);

    @Insert("INSERT INTO UserInfo(name, age) VALUES(#{name}, #{age})")
    void insert(@Param("name") String name, @Param("age") Integer age);
}