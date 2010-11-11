/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package syntax.config;

import syntax.Transition;
import java.io.File;
import lex.Token;
import lex.TokenType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import syntax.TransitionType;
import utils.ArraysUtils;
import static org.junit.Assert.*;

/**
 *
 * @author helionagamachi
 */
public class ParserTest {

    private static Parser parser = new Parser(new File(ParserTest.class.getResource("/syntax/config/desvio").getFile()));

    public ParserTest() {
      
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
   
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIntConversion(){
        assertEquals(90, parser.getInteger("90"));
        assertEquals(12, parser.getInteger("12"));
        assertEquals(45, parser.getInteger("45"));
        assertEquals(7817212, parser.getInteger("7817212"));
        assertEquals(666, parser.getInteger("666"));
        assertEquals(38, parser.getInteger("38"));
        assertEquals(67, parser.getInteger("67"));
        assertEquals(21, parser.getInteger("21"));
    }

    @Test
    public void testSecondLineParse(){
        int[] result = parser.parseSecondLine("final: 1, 2 , 50");
        assertEquals(3, result.length);
        assertEquals(1 , result[0]);
        assertEquals(2  , result[1]);
        assertEquals(50 , result[2]);

    }

    @Test
    public void TransitionTest(){
        Transition transition;
        transition = parser.parseTransition("(3, identificador) -> 2");
        assertEquals(0,transition.getAutomataNumber());
        assertEquals(2,transition.getNextState());
        assertEquals(1,transition.getNextAutomataNumber());
        assertEquals(3,transition.getStateNumber());
        assertEquals(TransitionType.CALL_TO_ANOTHER_AUTOMATA,transition.getType());

        Token token;
        transition = parser.parseTransition("(5, \"+\") -> 7");
        token = new Token(TokenType.RESERVED_WORD, ArraysUtils.getReservedWordIndex("+"));
        assertEquals(0, transition.getAutomataNumber());
        assertEquals(7, transition.getNextState());
        assertEquals(5, transition.getStateNumber());
        assertEquals(TransitionType.NORMAL, transition.getType());
        assertEquals(token, transition.getToken());


        transition = parser.parseTransition("(8, \"-\") -> 10\t\n");
        token = new Token(TokenType.RESERVED_WORD, ArraysUtils.getReservedWordIndex("-"));
        assertEquals(0, transition.getAutomataNumber());
        assertEquals(10, transition.getNextState());
        assertEquals(8, transition.getStateNumber());
        assertEquals(TransitionType.NORMAL, transition.getType());
        assertEquals(token, transition.getToken());

    }

    @Test
    public void parseTest(){
        parser.parse();
    }


    
}