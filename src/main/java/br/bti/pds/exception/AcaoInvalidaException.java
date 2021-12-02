package br.bti.pds.exception;

public class AcaoInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184388541971178452L;
	
	public AcaoInvalidaException(String causa) {
		this.causa = causa;
	}
	
	private String causa;

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

}
