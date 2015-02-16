/**
 * A test class for Stack, that tests that everything work according to description
 * 
 * @author Felix De Silva
 * @date 2014-02-16
 */
public class TestStack extends junit.framework.TestCase{

	public TestStack(){
	}
	
	/**
	 * Test if push and top method works
	 */
	public void testPushTop1() {
		Stack<String> stack = new Stack<String>();
		stack.push("hej");
		assertEquals("hej", stack.top());
		assertEquals(1, stack.size());
	}
	
	/**
	 * Test if push and top method works
	 */
	public void testPushTop2() {
		Stack<String> stack = new Stack<String>();
		stack.push("hej");
		stack.push("tja");
		assertEquals("tja", stack.top());
		assertEquals(2, stack.size());
	}

	/**
	 * Test if pop method works
	 */
	public void testPop(){
		Stack<String> stack = new Stack<String>();
		stack.push("hej");
		stack.push("tja");
		assertEquals("tja", stack.pop());
		assertEquals(1, stack.size());
		assertEquals("hej", stack.top());
	}
	
	/**
	 * Test if the stack with size 0 is true  (empty Stack)
	 */
	public void testSize0(){
		Stack<String> stack = new Stack<String>();
		assertEquals(0, stack.size());
	}
	
	/**
	 * Test if the stack with size 1 is false  (non-empty Stack)
	 */
	public void testSize1(){
		Stack<String> stack = new Stack<String>();
		stack.push("hej");
		assertEquals(1, stack.size());
	}
	
	/**
	 * Test if a empty stack is empty
	 */
	public void testEmptyStack() {
		Stack<String> stack = new Stack<String>();
		assertEquals(true, stack.isEmpty());
	}
	
	/**
	 * Test if a non-empty stack is empty
	 */
	public void testNonEmptyStack() {
		Stack<String> stack = new Stack<String>();
		stack.push("hej");
		assertEquals(false, stack.isEmpty());
	}
	
	/**
	 * Test error for top method
	 */
	public void testTopError(){
		Stack<String> stack = new Stack<String>();
		try{
			stack.top();
			fail();
		}catch(NullPointerException e){
		}
	}
	
	/**
	 * Test error for pop method
	 */
	public void testPushError(){
		Stack<String> stack = new Stack<String>();
		try{
			stack.pop();
			fail();
		}catch(NullPointerException e){
		}
	}
}
