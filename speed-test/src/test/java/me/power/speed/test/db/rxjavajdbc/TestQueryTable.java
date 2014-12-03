package me.power.speed.test.db.rxjavajdbc;

import java.util.List;

import org.junit.Test;

import com.github.davidmoten.rx.jdbc.Database;

import me.power.speed.test.AbstractSpringTest;

public class TestQueryTable extends AbstractSpringTest {
	private String url = "jdbc:mysql://10.10.0.126:3307/gametenddata";
	private String username = "gametenddata";
	private String password = "gamepasswd";
	private Database db = new Database(url, username, password);
	
	@Test
	public void queryTable() {
		List<String> result = db
				.select("select productname from product where currency = ?")
				.parameter("CNY")
				.getAs(String.class)
				.toList()
				.toBlocking()
				.single();
		this.print(result);
	}
}
