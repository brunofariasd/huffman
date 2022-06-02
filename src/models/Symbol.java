package models;

import java.io.Serializable;

import json.JSONObject;

public class Symbol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String symbol;
	private String code;
	
	public Symbol(String symbol, String code){	
		this.setSymbol(symbol);
		this.setCode(code);
	}
	
	public Symbol(JSONObject j) {
		this.symbol = j.getString("symbol");
		this.code = j.getString("code");
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("symbol", this.symbol);
		json.put("code", this.code);
		return json;
	}	
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String name) {
		this.symbol = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
