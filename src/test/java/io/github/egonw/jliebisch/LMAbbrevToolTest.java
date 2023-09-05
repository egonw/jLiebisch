package io.github.egonw.jliebisch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LMAbbrevToolTest {

    @Test
    public void testFAs() {
        String cxsmiles = LMAbbrevTool.cxsmiles("FA 14:1");
        assertEquals("OC(=O)CC=CC[H] |Sg:n:3:x:ht,Sg:n:6:y:ht| x+y=11", cxsmiles);
    }

}
