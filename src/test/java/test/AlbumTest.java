package test;

import org.junit.Test;

import vcd.db.action.AlbumDataPool;

/**
* @Author: PQ  
* @Data: 2019年1月6日上午9:38:14 
*/
public class AlbumTest {
	@Test
	public void AlbumGetData() {
		AlbumDataPool ad = AlbumDataPool.getPool();
		ad.show();
	}
}
