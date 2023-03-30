import java.util.ArrayList;

public class DataFlowWebAppFixture {
	
	private String edgeList, initial_n, final_n, defs, uses;
	private DataFlowWebApp testInstance;
	
	public DataFlowWebAppFixture() {
		testInstance = new DataFlowWebApp();
	}
	
	public void setEdges(String edges) {
		this.edgeList = edges.replaceAll(", ", "\n");
	}
	
	public void setInitialNode(String initialNode) {
		this.initial_n = initialNode;
	}
	
	public void setFinalNode(String finalNode) {
		this.final_n = finalNode;
	}

    	public void setDefs(String defs) {
		this.defs = defs;
	}

    	public void setUses(String uses) {
		this.uses = uses;
	}

    public String actualDUPaths() {
    try {
        testInstance.inputAndSubmitData(edgeList, initial_n, final_n, uses, defs);
        ArrayList<Integer> actualDUPaths = testInstance.getDUPaths();
        String output = "" + actualDUPaths.get(0);
        output = (actualDUPaths.get(0)).toString();
        for(int n=1; n<actualDUPaths.size(); n++) {
            output = output + " " + actualDUPaths.get(n).toString();
        }
        return output;
    } catch (InterruptedException e) {
        e.printStackTrace();
        return null;
    }
    
}

}
