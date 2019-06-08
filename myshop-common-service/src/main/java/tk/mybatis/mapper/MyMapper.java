package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author yang
 * @Date 2019/5/31
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
