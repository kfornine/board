package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.AttachFileVo;

public interface AttachMapper {
	
	public int getSeq();
	public int insert(AttachFileVo vo);
	public List<AttachFileVo> getList(int attachNo);

	public int delete(@Param("uuid") String uuid, 
					  @Param("attachNo") int attachNo);

	public AttachFileVo get(@Param("uuid") String uuid, 
			  				@Param("attachNo") int attachNo);
}