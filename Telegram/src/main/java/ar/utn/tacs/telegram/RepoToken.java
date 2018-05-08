package ar.utn.tacs.telegram;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class RepoToken {

	private static RepoToken instance = null;

	@Getter	private Map<Integer, String> tokens = new HashMap<Integer, String>();

	protected RepoToken() {

	}

	public static RepoToken getInstance() {
		if (instance == null) {
			instance = new RepoToken();
		}
		return instance;
	}

}