package mtgmgr.model;

import java.io.Serializable;

public abstract class DTOFactory implements Serializable {
	private int numResults;

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public int getNumResults() {
		return numResults;
	}
}