package com.ssp.closet.dao.mybatis;

import org.springframework.dao.DataAccessException;
import com.ssp.closet.dto.Sequence;

public class OracleSequenceDao extends MybatisSequenceDao {

  /**
   * Get the next sequence using an Oracle thread-safe sequence
   * @param name Name is the name of the oracle sequence.
   * @return the next sequence
   */
	public int getNextId() throws DataAccessException {
		Sequence sequence = new Sequence();
		sequence = sequenceMapper.getOracleSequence(sequence);
		return sequence.getNextId();
	 }
}
