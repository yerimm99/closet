package com.ssp.closet.dao.mybatis.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Sequence;

@Mapper
public interface SequenceMapper {

	  Sequence getSequence(Sequence sequence);
	  Sequence getOracleSequence(Sequence sequence);
	  void updateSequence(Sequence sequence);
}
