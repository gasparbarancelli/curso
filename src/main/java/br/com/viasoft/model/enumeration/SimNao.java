package br.com.viasoft.model.enumeration;

public enum SimNao implements EnumLabel {

	S("Sim"), N("Não");
	
	private String label;

	private SimNao(String label) {
		this.label = label;
	}

    @Override
	public String getLabel() {
		return label;
	}
}
