package me.power.speed.test.thirdparty.chronicle.map;

import org.junit.Test;

import me.power.speed.test.AbstractTest;
/*
 * 
 * // --- RUN ON ONE JAVA PROCESS ( BUT ON THE SAME SERVER )
{
    File file = new File("a-new-file-on-your-sever");   
    Map<Integer, CharSequence> map1 = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .createPersistedTo(file); // this has to be the same file as used by map 2
    map1.put(1, "hello world");
}

// --- RUN ON THE OTHER JAVA PROCESS ( BUT ON THE SAME SERVER )
{
    File file = new File("a-new-file-on-your-sever");  // this has to be the same file as used by map 1
    Map<Integer, CharSequence> map1 = ChronicleMapBuilder.of(Integer.class, CharSequence.class)
            .createPersistedTo(file);

    System.out.println(map1.get(1));
}
 * 
 */
public class TestChronicleMapShareWithMulitProcessor extends AbstractTest {
	
	@Test
	public void testChronicleMapShareWithMulitProcessor() {
		Integer key  = 121;
		String value = "this is share data.";
		ChronicleMapShareWithMulitProcessor.put(key, value);
		this.print(ChronicleMapShareWithMulitProcessor.get(key));
	}
}
